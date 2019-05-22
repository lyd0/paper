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

        //如果传入的users和websites是空的 就用数据库中的
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

        //将1996Y/12M/10D/0h/0m/0s到表单提交的那个时间(例如现在2019/5/21)，将时间分成length份，最后在每个区间中取一个时刻作为随机时间。（例如45天是一个区间，每45天取一个时刻作为随机时间）
        long dateInterval = (endDate.getTime() - startDate.getTime())/length;

        int counter = 0;
        for(long tempDate = startDate.getTime() + dateInterval;//循环初始时间tempDate是第一个时间间隔(从开始时间1996/12/10向后推一个时间间隔)
            tempDate < endDate.getTime() ; //结束条件为当前时间区间大于等于提交表单的那个时刻
            tempDate += dateInterval, counter++) {//循环递增添加是向后推一个时间间隔

            date = stuRandom.getRandomDate(new Date(tempDate), new Date(tempDate+dateInterval));//这个date是当期时间间隔中的一个随机时间
            user = stuRandom.getRandomInList(users);//随机分配一个数据库中的user
            website = webRandom.getRandomInList(websites);//随机分配一个数据库中的website
            records.add(new Record(website.getUrl(), "", user.getAccountId(), Record.ActionType.VISIT, date));//生成一条Record 加入到records列表中
        }
        recordGroup.setRecords(records);//给每条record设置RecordGroup组
        recordGroup.setStatus(RecordGroup.Status.DONE);//将RecordGroup组的状态设置为结束
        recordGroupRepository.save(recordGroup);

        logger.info("已将"+counter+"条数据写入数据库");

        fakeResultsData.fakeGroup();

        return recordGroup.getId();//返回当前组的ID
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