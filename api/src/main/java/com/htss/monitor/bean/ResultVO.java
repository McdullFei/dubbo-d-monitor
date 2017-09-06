package com.htss.monitor.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by zxg on 15/11/2.
 */
@Data
public class ResultVO implements Serializable{

  private boolean success;
  private String code = "000";
  private String msg = "";
  private Object data;

  public static ResultVO wrapSuccessfulResult() {
    ResultVO vo = new ResultVO();
    vo.setSuccess(true);
    return vo;
  }

  public static ResultVO wrapSuccessfulResult(Object data) {
    ResultVO vo = new ResultVO();
    vo.setSuccess(true);
    vo.setData(data);
    return vo;
  }

  public static ResultVO wrapErrorResult(String msgStr) {
    ResultVO vo = new ResultVO();
    vo.setSuccess(false);

    vo.setMsg(msgStr);

    return vo;
  }

  public static ResultVO wrapErrorResult(String code, String msgStr) {
    ResultVO vo = new ResultVO();
    vo.setCode(code);
    vo.setSuccess(false);
    vo.setMsg(msgStr);

    return vo;
  }

}
