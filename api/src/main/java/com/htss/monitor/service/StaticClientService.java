package com.htss.monitor.service;

import com.htss.monitor.bean.ResultVO;

public interface StaticClientService {

  /**
   * 获取所有服务
   * @return
   */
  ResultVO getAllService();

  /**
   * 获得此service下的 方法 当前时间的调用量
   * @param serviceName
   * @param methodName
   * @param type
   * @return
   */
  ResultVO getMethodSumOneDay(String serviceName,String methodName ,String type);
}
