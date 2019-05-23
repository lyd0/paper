package com.paper.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paper.entity.Record;
import com.paper.repository.RecordRepository;
import com.paper.repository.UserRepository;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(value = {"classpath:db/user.sql", "classpath:db/website.sql"})
//@Transactional
public class RandomGenerateControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void generate() throws Exception {
        List<String> stuList = new ArrayList<>();
//        stuList.add("140230211");
//        stuList.add("140230212");
//        stuList.add("140230213");
//        stuList.add("140230214");
//        stuList.add("140230215");
//        stuList.add("140230216");

        List<String> webList = new ArrayList<>();
//        webList.add("www.baidu.com");
//        webList.add("www.google.com");
//        webList.add("www.hao123.com");
//        webList.add("www.bing.com");
//        webList.add("www.bilibili.com");
//        webList.add("www.sina.com");

        //设置开始日期
        Date startDate = Date.from(LocalDateTime.of(1996, 12, 10, 0, 0, 0).toInstant(ZoneOffset.UTC));
        //结束日期为现在的时间
        Date endDate = new Date();
        int length = 5000;
        //把stuList webList startDate endDate length 封装到reqBody中
        Map<String, Object> reqBody = new HashMap<>(5);
        reqBody.put("stuList", stuList);
        reqBody.put("webList", webList);
        reqBody.put("startDate", startDate);
        reqBody.put("endDate", endDate);
        reqBody.put("length", length);
        ObjectMapper objectMapper = new ObjectMapper();
        //将reqBody转换为字符串{"length":100,"stuList":[],"webList":[],"endDate":时间戳,"startDate":时间戳}
        String body = objectMapper.writeValueAsString(reqBody);

        //向generate发起post请求，把reqBody发送过去，模拟网页点击post将数据发送
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/random/generate").contentType(MediaType.APPLICATION_JSON).content(body))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode", Is.is(RespCode.SUCCESS.getCode())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Is.is(RespCode.SUCCESS.getMsg())));


        List<Record> records = recordRepository.findAll();
        Assert.assertEquals(length, records.size());
    }
}
