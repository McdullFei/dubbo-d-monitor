package com.htss.monitor.controller;

import com.htss.monitor.bean.ResultVO;
import com.htss.monitor.service.CommonClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zxg on 15/11/11.
 */
@Controller
@RequestMapping("/monitor/common")
public class CommonController {

  @Autowired
  private CommonClientService commonClientService;

  //测试
  @RequestMapping(value = "/getFinalTime", method = RequestMethod.GET)
  public
  @ResponseBody
  ResultVO getFinalTime() {
    return commonClientService.getFinalTime();
  }
}
