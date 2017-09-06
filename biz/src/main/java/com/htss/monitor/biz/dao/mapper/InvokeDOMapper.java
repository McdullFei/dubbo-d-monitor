package com.htss.monitor.biz.dao.mapper;

import com.htss.monitor.bean.entity.InvokeDO;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvokeDOMapper {
    int deleteByPrimaryKey(String uuId);

    int insertSelective(InvokeDO record);

    InvokeDO selectByPrimaryKey(String uuId);

    int updateByPrimaryKeySelective(InvokeDO record);


    List<InvokeDO> selectByInvokeDO(InvokeDO searchDO);

    int deleteByDate(@Param(value = "date") String minDate);

}