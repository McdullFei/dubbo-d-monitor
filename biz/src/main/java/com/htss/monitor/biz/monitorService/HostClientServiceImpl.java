package com.htss.monitor.biz.monitorService;

import com.htss.monitor.biz.support.service.HostService;
import com.htss.monitor.bean.ResultVO;
import com.htss.monitor.bean.bizBean.HostBO;
import com.htss.monitor.service.HostClientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Service
public class HostClientServiceImpl implements HostClientService{
  @Autowired
  private HostService hostService;

  @Override
  public ResultVO getAllHostPage() {
    try {
      Map<String,Object> map = new HashMap<>();

      Map<String,HostBO> hostMap = hostService.getHostBOMap();

      List<String> hostList = new ArrayList<>(hostMap.keySet());
      Collections.sort(hostList);


      map.put("sum", hostList.size());
      map.put("hostMap", hostMap);
      map.put("hostList", hostList);
      return ResultVO.wrapSuccessfulResult(map);
    } catch (Exception e) {
      e.printStackTrace();
      return ResultVO.wrapErrorResult(e.getMessage());
    }
  }
}
