package com.htss.monitor.controller.show;

import com.htss.monitor.bean.ResultVO;
import com.htss.monitor.service.ApplicationClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by zxg on 15/11/7.
 * 16:39
 */
@Controller
@RequestMapping("/monitor/application")
public class ApplicationController {

  @Resource
  private ApplicationClientService applicationClientService;

  //主页
  @RequestMapping(value = "main")
  public ModelAndView main() {
    return new ModelAndView("monitorView/application/appIndex");
  }

  //所有服务和其相关信息
  @RequestMapping(value = "/getAllAPPAndRelation", method = RequestMethod.GET)
  public
  @ResponseBody
  ResultVO getAllAPPAndRelation() {
    return applicationClientService.getAllAPPAndRelation();

  }

  //获得target消耗source在时间内频次
  @RequestMapping(value = "/getSuccessByConsumer", method = RequestMethod.GET)
  public
  @ResponseBody
  ResultVO getSuccessByConsumer(String type, String source) {
    return applicationClientService.getSuccessByConsumer(type, source);
  }

  // 按时间
  @RequestMapping(value = "/getSuccessByConsumerOnHour", method = RequestMethod.GET)
  public
  @ResponseBody
  ResultVO getSuccessByConsumerOnHour(String type, String source) {
    return applicationClientService.getSuccessByConsumerOnHour(type, source);

  }

  // 按日期:15天
  @RequestMapping(value = "/getSuccessByConsumerOnDay", method = RequestMethod.GET)
  public
  @ResponseBody
  ResultVO getSuccessByConsumerOnDay(String type, String source) {

    return applicationClientService.getSuccessByConsumerOnDay(type, source);

  }

  // 获得
  @RequestMapping(value = "/getMethodRanking", method = RequestMethod.GET)
  public
  @ResponseBody
  ResultVO getMethodRanking(String appName) {
    return applicationClientService.getMethodRanking(appName);
  }
}
