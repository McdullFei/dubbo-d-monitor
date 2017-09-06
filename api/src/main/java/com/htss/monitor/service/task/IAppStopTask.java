package com.htss.monitor.service.task;

public interface IAppStopTask {
  /**
   * 每分钟触发一次检查,若超时还未连接，则做发短信、邮件等操作。
   */
  void smsStopApp();
}
