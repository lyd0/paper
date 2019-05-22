package com.yggdrasil.paper.controller;

import com.yggdrasil.paper.entity.RandomInfo;
import com.yggdrasil.paper.tools.GenerateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/*
* /api/v1/random 的Controller
* */

@RestController
@RequestMapping("/api/v1/random")
public class RandomGenerateController {

    @Resource
    private GenerateData generateData;


    private static Logger logger = LoggerFactory.getLogger(RandomGenerateController.class);

    /*
    * /api/v1/random/generate ，生成随机数据后返回一个response body
    * */
    @PostMapping("/generate")
    public RespBody generate(@RequestBody RandomInfo randomInfo) {
        //调用generateData.generate生成数据
        int recordListId = generateData.generate(randomInfo.getStuList(), randomInfo.getWebList(), randomInfo.getStartDate(), randomInfo.getEndDate(), randomInfo.getLength());
        Map<String, Integer> body = new HashMap<>(1);
        body.put("id", recordListId);
        return new RespBody(RespCode.SUCCESS, body);
    }
}
