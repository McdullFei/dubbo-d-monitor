package com.htss.monitor.biz.monitorService;

import com.htss.monitor.biz.support.service.ApplicationService;
import com.htss.monitor.bean.ResultVO;
import com.htss.monitor.bean.bizBean.ApplicationBO;
import com.htss.monitor.bean.bizBean.ServiceBO;
import com.htss.monitor.service.ApplicationClientService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//@Service
public class ApplicationClientServiceImpl implements ApplicationClientService {

  @Autowired
  private ApplicationService applicationService;

  @Override
  public ResultVO getAllAPPAndRelation() {
    try {
      Map<String, Object> resultMap = new HashMap<>();

      Map<String, ApplicationBO> allApplicationsMap = applicationService.getApplicationsBOMap();
      List<ApplicationBO> appList = new ArrayList<>();

      Set<String> groupSet = new HashSet<>();
      Integer appSum = 0;
      for (Map.Entry<String, ApplicationBO> applicationBOEntry : allApplicationsMap.entrySet()) {
        appSum += 1;
        ApplicationBO applicationBO = applicationBOEntry.getValue();
        String organization = applicationBO.getOrganization();
        if (!organization.equals("")) {
          groupSet.add(organization);
        }

        Set<String> serviceSet = new HashSet<>();
        Map<String, Set<ServiceBO>> serviceMap = applicationBO.getServiceMap();
        if (serviceMap != null) {
          for (Map.Entry<String, Set<ServiceBO>> entry : serviceMap.entrySet()) {
            Set<ServiceBO> serviceBOSet = entry.getValue();
            for (ServiceBO serviceBO : serviceBOSet) {
              serviceSet.add(serviceBO.getServiceName());
            }
          }
        }

        Set<String> providersSet = applicationBO.getProvidersSet();
        Set<String> consumersSet = applicationBO.getConsumersSet();
        if (!serviceSet.isEmpty()) applicationBO.setServiceSum(serviceSet.size());
        if (null != providersSet) applicationBO.setProviderSum(providersSet.size());
        if (null != consumersSet) applicationBO.setConsumerSum(consumersSet.size());
        appList.add(applicationBO);
      }

      //对appList 排序，按首字母
      Collections.sort(appList, new Comparator<ApplicationBO>() {
        @Override
        public int compare(ApplicationBO o1, ApplicationBO o2) {
          Integer o1First = o1.getApplicationName().codePointAt(0);
          Integer o2First = o2.getApplicationName().codePointAt(0);
          return o1First.compareTo(o2First);
        }
      });

      resultMap.put("appSum", appSum);
      resultMap.put("groupSum", groupSet.size());
      resultMap.put("appList", appList);
      resultMap.put("allApp", allApplicationsMap);
      return ResultVO.wrapSuccessfulResult(resultMap);
    } catch (Exception e) {
      e.printStackTrace();
      return ResultVO.wrapErrorResult(e.getMessage());
    }
  }

  @Override
  public ResultVO getSuccessByConsumer(String type, String source) {
    return null;
  }

  @Override
  public ResultVO getSuccessByConsumerOnHour(String type, String source) {
    return null;
  }

  @Override
  public ResultVO getSuccessByConsumerOnDay(String type, String source) {
    return null;
  }

  @Override
  public ResultVO getMethodRanking(String appName) {
    return null;
  }
}
