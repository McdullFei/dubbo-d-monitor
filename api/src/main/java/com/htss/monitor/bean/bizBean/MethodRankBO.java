package com.htss.monitor.bean.bizBean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zxg on 16/7/4.
 * 14:18
 * no bug,以后改代码的哥们，祝你好运~！！
 * 方法排行榜的bo类
 */
@Data
public class MethodRankBO implements Serializable {
  //方法名
  public String methodName;
  //函数
  public String serviceName;
  //调用次数
  public Integer usedNum;
}
