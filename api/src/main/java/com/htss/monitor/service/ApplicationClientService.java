package com.htss.monitor.service;

import com.htss.monitor.bean.ResultVO;

/**
 * 应用服务
 */
public interface ApplicationClientService {

  /**
   * 所有服务和其相关信息
   *
   * @return
   */
  ResultVO getAllAPPAndRelation();

  /**
   * 获得target消耗source在时间内频次
   * @param type 方式
   * @param source 资源
   * @return
   */
  ResultVO getSuccessByConsumer(String type, String source);

  /**
   * 按时间 获得target消耗source在时间内频次
   * @param type 方式
   * @param source 资源
   * @return
   */
  ResultVO getSuccessByConsumerOnHour(String type, String source);

  /**
   * 按日期:15天 获得target消耗source在时间内频次
   * @param type 方式
   * @param source 资源
   * @return
   */
  ResultVO getSuccessByConsumerOnDay(String type, String source);

  /**
   * 获得 方法打分
   * @param appName
   * @return
   */
  ResultVO getMethodRanking(String appName);


}
