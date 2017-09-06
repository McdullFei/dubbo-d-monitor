package com.htss.monitor.bean;

import java.util.UUID;

/**
 * 生成uuId
 * Created by zxg on 15/8/28.
 *
 * modify by renfei
 */
public class UUIDGenerator {
  public UUIDGenerator() {
  }

  public static String getUUID() {
    UUID uuid = UUID.randomUUID();
    String str = uuid.toString();
    // 去掉"-"符号
    return str.replaceAll("-", "");
  }

  //获得指定数量的UUID
  public static String[] getUUID(int number) {
    if (number < 1) {
      return null;
    }
    String[] ss = new String[number];
    for (int i = 0; i < number; i++) {
      ss[i] = getUUID();
    }
    return ss;
  }

}
