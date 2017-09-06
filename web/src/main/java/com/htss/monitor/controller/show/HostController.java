package com.htss.monitor.controller.show;

import com.htss.monitor.bean.ResultVO;
import com.htss.monitor.service.HostClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zxg on 15/12/3.
 * 16:39
 */
@Controller
@RequestMapping("/monitor/hosts")
public class HostController {

  @Autowired
  private HostClientService hostClientService;

  //主页
  @RequestMapping(value = "main")
  public ModelAndView main() {
    return new ModelAndView("monitorView/host/hostIndex");
  }

  @RequestMapping(value = "/getAllHostPage", method = RequestMethod.GET)
  public
  @ResponseBody
  ResultVO getAllHostPage() {
    return hostClientService.getAllHostPage();
  }

}
