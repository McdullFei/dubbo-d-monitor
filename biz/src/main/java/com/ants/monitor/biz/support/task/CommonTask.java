package com.ants.monitor.biz.support.task;

import com.ants.monitor.common.tools.TimeUtil;
import com.ants.monitor.dao.mapper.InvokeDOMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class CommonTask {
  @Autowired
  private ThreadPoolTaskExecutor taskExecutor;
  @Autowired
  private InvokeDOMapper invokeDOMapper;

  //每天凌晨 00：30分执行
  public void everyDayDo() {

    //每天更新
    AppConsumerOnDayProcess appConsumerOnDayProcess = new AppConsumerOnDayProcess();
    taskExecutor.execute(appConsumerOnDayProcess);
  }


  //每天
  private class AppConsumerOnDayProcess implements Runnable {
    @Override
    public void run() {
      try {
        //每天删除 大于15天的日期的原始数据
        String minDate = TimeUtil.getBeforDateByNumber(new Date(), -15);
        invokeDOMapper.deleteByDate(minDate);

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
