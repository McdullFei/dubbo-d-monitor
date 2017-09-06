package com.htss.monitor.biz.dao.redisManager;

import com.htss.monitor.common.redis.RedisClientTemplate;
import com.htss.monitor.common.redis.RedisKeyBean;
import com.htss.monitor.common.tools.JsonUtil;
import com.htss.monitor.bean.bizBean.ApplicationChangeBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zxg on 16/1/11.
 * 20:09
 */
@Component
public class AppStopRedisManagerImpl implements AppStopRedisManager {
    @Autowired
    private RedisClientTemplate redisClientTemplate;


    @Override
    public Map<ApplicationChangeBO, String> getAllStopApp() {
        Map<ApplicationChangeBO, String> resultMap = new HashMap<>();
        for(Map.Entry<Object,Object> entry : redisClientTemplate.getAllHash(RedisKeyBean.appStopMapKey).entrySet()){
            String key = String.valueOf(entry.getKey());
            ApplicationChangeBO applicationChangeBO = JsonUtil.jsonStrToObject(key, ApplicationChangeBO.class);
            resultMap.put(applicationChangeBO,String.valueOf(entry.getValue()));
        }
        return resultMap;
    }

    @Override
    public void saveStopApp(ApplicationChangeBO applicationChangeBO,Integer number) {
        if(null == number){
            number = 0;
        }
        ApplicationChangeBO filedBO = new ApplicationChangeBO();
        filedBO.setAppName(applicationChangeBO.getAppName());
        filedBO.setHost(applicationChangeBO.getHost());
        filedBO.setPort(applicationChangeBO.getPort());


        String filed = JsonUtil.objectToJsonStr(filedBO);
        String value = applicationChangeBO.getTime()+","+number;
        redisClientTemplate.setMapKey(RedisKeyBean.appStopMapKey, filed,value);
    }

    @Override
    public void removeStopApp(ApplicationChangeBO applicationChangeBO) {
        ApplicationChangeBO filedBO = new ApplicationChangeBO();
        filedBO.setAppName(applicationChangeBO.getAppName());
        filedBO.setHost(applicationChangeBO.getHost());
        filedBO.setPort(applicationChangeBO.getPort());

        String filed = JsonUtil.objectToJsonStr(filedBO);
        redisClientTemplate.delMapKey(RedisKeyBean.appStopMapKey,filed);
    }
}
