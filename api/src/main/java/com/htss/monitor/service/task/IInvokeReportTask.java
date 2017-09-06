package com.htss.monitor.service.task;

public interface IInvokeReportTask {
  /**
   * 每天每个小时小时 :01
   */
  void everyHourDo();

  /**
   * 每天凌晨 00：01分执行
   */
  void everyDayDo();
}
