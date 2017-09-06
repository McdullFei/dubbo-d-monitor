package com.htss.monitor.biz.monitorService;

import com.htss.monitor.biz.support.service.AppChangeService;
import com.htss.monitor.bean.ResultVO;
import com.htss.monitor.bean.bizBean.ApplicationChangeBO;
import com.htss.monitor.biz.support.service.ApplicationService;
import com.htss.monitor.biz.support.service.HostService;
import com.htss.monitor.biz.support.service.ServicesService;
import com.htss.monitor.common.tools.TimeUtil;
import com.htss.monitor.service.IndexClientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@Service
public class IndexClientServiceImpl implements IndexClientService {
  @Autowired
  private AppChangeService appChangeService;

  @Autowired
  private ServicesService servicesService;

  @Autowired
  private ApplicationService applicationService;

  @Autowired
  private HostService hostService;

  @Override
  public ResultVO getMonthChangeData(String month, String day, Integer pageIndex, Integer pageSize) {
    Map<String,Object> resultMap = new HashMap<>();

    if(day == null){
      // 取出第一个日期的数据
      Set<String> daySet = appChangeService.getDaySet(month);
      if(null == daySet || daySet.isEmpty()){
        return ResultVO.wrapErrorResult("not have record");
      }
      List<String> dayList = new ArrayList<>(daySet);
      Collections.sort(dayList, new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
          return o2.compareTo(o1);
        }
      });
      day = dayList.get(0);
      resultMap.put("day",day);
      resultMap.put("daySet",dayList);
    }
    List<ApplicationChangeBO> resultList = appChangeService.getChangeListByDay(day, pageIndex, pageSize);
    Integer sum = appChangeService.getListSum(day);
    resultMap.put("list",resultList);
    resultMap.put("sum",sum);

    return ResultVO.wrapSuccessfulResult(resultMap);
  }

  @Override
  public ResultVO indexStatic() {

    Integer appSum = applicationService.getAllApplications().size();
    Integer serviceSum = servicesService.getAllServicesString().size();

    Integer hostSum = hostService.getHostBOMap().keySet().size();

    String nowMonth = TimeUtil.getYearMonthString(new Date());

    List<ApplicationChangeBO> recentInsertList = appChangeService.getRecentInsertList();
    List<ApplicationChangeBO> recentDeleteList = appChangeService.getRecentDeleteList();

    Map<String,Object> resultMap = new HashMap<>();
    resultMap.put("appSum", appSum);
    resultMap.put("serviceSum", serviceSum);
    resultMap.put("hostSum", hostSum);
    resultMap.put("nowMonth", nowMonth);

    resultMap.put("recentInsertList", recentInsertList);
    resultMap.put("recentDeleteList", recentDeleteList);

    return ResultVO.wrapSuccessfulResult(resultMap);
  }
}
