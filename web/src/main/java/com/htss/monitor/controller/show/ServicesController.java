package com.htss.monitor.controller.show;

import com.htss.monitor.bean.ResultVO;
import com.htss.monitor.service.StaticClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zxg on 15/11/19.
 */
@Controller
@RequestMapping("/monitor/services")
public class ServicesController {
  @Autowired
  private StaticClientService staticClientService;

  //主页
  @RequestMapping(value = "main")
  public ModelAndView main() {
    return new ModelAndView("monitorView/services/servicesIndex");
  }

  //所有service
  @RequestMapping(value = "/getAllService", method = RequestMethod.GET)
  public
  @ResponseBody
  ResultVO getAllService() {
    return staticClientService.getAllService();

  }

  //获得此service下的 方法 当前时间的调用量
  @RequestMapping(value = "/getMethodSumOneDay", method = RequestMethod.GET)
  public
  @ResponseBody
  ResultVO getMethodSumOneDay(String serviceName, String methodName, String type) {
    return staticClientService.getMethodSumOneDay(serviceName, methodName, type);
  }
}
