package com.htss.monitor.service;

import com.htss.monitor.bean.ResultVO;

public interface IndexClientService {
  /**
   * 获得月份和类型相关的数据
   * @param month
   * @param day
   * @param pageIndex
   * @param pageSize
   * @return
   */
  ResultVO getMonthChangeData(String month, String day, Integer pageIndex, Integer pageSize);

  /**
   * 首页统计
   * @return
   */
  ResultVO indexStatic();
}
