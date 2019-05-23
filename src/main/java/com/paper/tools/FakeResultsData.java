package com.paper.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.paper.entity.Result;
import com.paper.entity.ResultGroup;
import com.paper.repository.ResultGroupRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

/*
* 模拟结果数据
* */

@Component
public class FakeResultsData {

    @Resource
    private ResultGroupRepository resultGroupRepository;

    private Faker faker;

    public FakeResultsData() {
         this.faker = new Faker(new Locale("zh-CN"));
    }

    private FaceInterface fakeSingle(Result.Type type) {
        switch (type) {
            case MOST_VISITED:
            case BOY_MOST_VISITED:
            case GIRL_MOST_VISITED: return () -> faker.internet().url().replace("-", "");

            case MOST_VISITED_BETWEEN:
            case MAN_MOST_VISITED_BETWEEN:
            case GIRL_MOST_VISITED_BETWEEN: return () -> faker.name().fullName();

            case MOST_VISITED_INTERVAL_10: return () -> faker.date().birthday().toString() + "±10 min";

            case MOST_VISITED_STUNO_BETWEEN:
            case MAN_VISITED_SAME_WEBSITE_AT_THE_SAME_TIME:
            case GIRL_VISITED_SAME_WEBSITE_AT_THE_SAME_TIME: return () -> String.valueOf(faker.idNumber().valid().replace("-", ""));
            default:
                return () -> "";
        }
    }

    public String fake(int length, Result.Type type) throws JsonProcessingException {
        List<Map<String, String>> results = new ArrayList<>();
        //生成6 或7条数据，加入到result中
        for (int index = 1; index <= length; index++) {
            Map<String, String> result = new HashMap<>();
            result.put("x", fakeSingle(type).getResult());//x的值是通过type生成的结果
            result.put("index", String.valueOf(index));
            //y值为一个1000~19999的随机值
            result.put("y", String.valueOf(faker.number().numberBetween(1000, 20000)));
            results.add(result);
        }
        return new ObjectMapper().writeValueAsString(results);
    }

    public int fakeGroup(){
        List<Result> results = new ArrayList<>();
        ResultGroup resultGroup = new ResultGroup();
        //10种type都会生成一条对应的模拟数据(对应数据库result表中的十条记录)
        for (Result.Type type : Result.Type.values()) {
            Result result = new Result();
            try {
                //在results中加入一条result，每条result有6或7条数据
                results.add(new Result(type, fake(faker.number().numberBetween(6, 8), type)));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        resultGroup.setResult(results);
        //最后results保存到数据库里
        return resultGroupRepository.save(resultGroup).getId();
    }
}
