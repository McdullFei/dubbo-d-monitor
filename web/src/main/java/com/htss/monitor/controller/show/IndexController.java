package com.htss.monitor.controller.show;

import com.htss.monitor.biz.support.service.AppChangeService;
import com.htss.monitor.biz.support.service.ApplicationService;
import com.htss.monitor.biz.support.service.HostService;
import com.htss.monitor.biz.support.service.ServicesService;
import com.htss.monitor.common.tools.TimeUtil;
import com.htss.monitor.bean.MonitorConstants;
import com.htss.monitor.bean.ResultVO;
import com.htss.monitor.bean.bizBean.ApplicationChangeBO;
import com.htss.monitor.service.IndexClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by zxg on 15/11/5.
 * 18:02
 */
@Controller
@RequestMapping("/monitor/dash")
public class IndexController {
  @Autowired
  private ApplicationService applicationService;
  @Autowired
  private ServicesService servicesService;
  @Autowired
  private HostService hostService;
  @Autowired
  private AppChangeService appChangeService;

  @Autowired
  private IndexClientService indexClientService;

  //主页
  @RequestMapping(value = "main")
  public ModelAndView main(HttpServletRequest request) {
    HttpSession session = request.getSession();
    String name = (String) session.getAttribute(MonitorConstants.SESSION_USER_NAME);
    if (name == null) name = "null";

    ModelAndView modelAndView = new ModelAndView("monitorView/main");

    modelAndView.addObject("name", name);

    return modelAndView;
  }

  //首页
  @RequestMapping(value = "index")
  public ModelAndView index() {
    ModelAndView modelAndView = new ModelAndView("monitorView/dashBoard/dashBoard");

    Integer appSum = applicationService.getAllApplications().size();
    Integer serviceSum = servicesService.getAllServicesString().size();

    Integer hostSum = hostService.getHostBOMap().keySet().size();

    String nowMonth = TimeUtil.getYearMonthString(new Date());

    modelAndView.addObject("appSum", appSum);
    modelAndView.addObject("serviceSum", serviceSum);
    modelAndView.addObject("hostSum", hostSum);
    modelAndView.addObject("nowMonth", nowMonth);

    //最近修改记录
    List<ApplicationChangeBO> recentInsertList = appChangeService.getRecentInsertList();
    List<ApplicationChangeBO> recentDeleteList = appChangeService.getRecentDeleteList();
    modelAndView.addObject("recentInsertList", recentInsertList);
    modelAndView.addObject("recentDeleteList", recentDeleteList);

    return modelAndView;
  }

  //获得月份和类型相关的数据
  @RequestMapping(value = "/getMonthChangeData", method = RequestMethod.GET)
  public
  @ResponseBody
  ResultVO getMonthChangeData(String month, String day, Integer pageIndex, Integer pageSize) {
    return indexClientService.getMonthChangeData(month, day, pageIndex, pageSize);

  }

}
