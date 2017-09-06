package com.htss.monitor.controller.show;

import com.google.common.collect.Lists;
import com.htss.monitor.bean.MonitorConstants;
import com.htss.monitor.bean.ResultVO;
import com.htss.monitor.service.IndexClientService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by zxg on 15/11/5.
 * 18:02
 */
@Controller
@RequestMapping("/monitor/dash")
public class IndexController {
  @Resource
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

    ResultVO vo = indexClientService.indexStatic();
    Map<String, Object> m = (Map<String,Object>)vo.getData();

    modelAndView.addObject("appSum", m.get("appSum"));
    modelAndView.addObject("serviceSum", m.get("serviceSum"));
    modelAndView.addObject("hostSum", m.get("hostSum"));
    modelAndView.addObject("nowMonth", m.get("nowMonth"));

    //最近修改记录
    modelAndView.addObject("recentInsertList", m.get("recentInsertList"));
    modelAndView.addObject("recentDeleteList", m.get("recentDeleteList"));

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
