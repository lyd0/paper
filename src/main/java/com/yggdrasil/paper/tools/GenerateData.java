package com.yggdrasil.paper.tools;

import com.yggdrasil.paper.entity.Record;
import com.yggdrasil.paper.entity.RecordGroup;
import com.yggdrasil.paper.entity.User;
import com.yggdrasil.paper.entity.Website;
import com.yggdrasil.paper.repository.RecordGroupRepository;
import com.yggdrasil.paper.repository.RecordRepository;
import com.yggdrasil.paper.repository.UserRepository;
import com.yggdrasil.paper.repository.WebsiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
*  生成数据工具
* */
@Component
//@EnableAutoConfiguration
public class GenerateData {

    @Resource
    private UserRepository userRepository;

    @Resource
    private WebsiteRepository websiteRepository;

    @Resource
    private FakeResultsData fakeResultsData;

    @Resource
    private RecordGroupRepository recordGroupRepository;

    private static Logger logger = LoggerFactory.getLogger(GenerateData.class);

    @Transactional
    public int generate(List<String> sNoList, List<String> wNoList, Date startDate, Date endDate, int length){

        List<User> users;
        List<Website> websites;
        RecordGroup recordGroup = new RecordGroup();
        List<Record> records = new ArrayList<>();
        recordGroup.setStatus(RecordGroup.Status.GENERATING);
        recordGroupRepository.save(recordGroup);

        if(sNoList.size() > 0 || wNoList.size() > 0) {
            users = userRepository.findAll(sNoList);
            websites = websiteRepository.findAll(wNoList);
        } else {
            users = userRepository.findAll();
            websites = websiteRepository.findAll();
        }
        MyRandom stuRandom = new MyRandom();
        MyRandom webRandom = new MyRandom();

        User user;
        Website website;
        Date date;
        long dateInterval = (endDate.getTime() - startDate.getTime())/length;
        int counter = 0;
        for(long tempDate = startDate.getTime() + dateInterval;
            tempDate < endDate.getTime() ;
            tempDate += dateInterval, counter++) {
            date = stuRandom.getRandomDate(new Date(tempDate), new Date(tempDate+dateInterval));
            user = stuRandom.getRandomInList(users);
            website = webRandom.getRandomInList(websites);
            records.add(new Record(website.getUrl(), "", user.getAccountId(), Record.ActionType.VISIT, date));
        }
        recordGroup.setRecords(records);
        recordGroup.setStatus(RecordGroup.Status.DONE);
        recordGroupRepository.save(recordGroup);

        logger.info("已将"+counter+"条数据写入数据库");

        fakeResultsData.fakeGroup();

        return recordGroup.getId();
//        new Thread(() -> {
            //TODO: 目前calculate为测试方法，正式方法为下面被注释的
//            hadoopService.calculate(inputFile.getAbsolutePath(),
//                                    "/input" + inputFile.getName(),
//                                    inputFile.getName());
//            hadoopService.calculate(inputFile.getName());
//        }).start();
//        hadoopService.calculate(inputFile.getAbsolutePath(),
//                                "/input"+inputFile.getName(),
//                                inputFile.getName());
    }
}