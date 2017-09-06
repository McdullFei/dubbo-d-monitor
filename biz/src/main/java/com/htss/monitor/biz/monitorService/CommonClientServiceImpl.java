package com.htss.monitor.biz.monitorService;

import com.htss.monitor.biz.dubboService.RegistryContainer;
import com.htss.monitor.common.tools.TimeUtil;
import com.htss.monitor.bean.ResultVO;
import com.htss.monitor.service.CommonClientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

//@Service
public class CommonClientServiceImpl implements CommonClientService{
  @Autowired
  private RegistryContainer registryContainer;

  @Override
  public ResultVO getFinalTime() {
    Date finalTime = registryContainer.getFinalUpdateTime();
    String timeString = TimeUtil.getTimeString(finalTime);

    return ResultVO.wrapSuccessfulResult(timeString);
  }
}
