package org.wlpay.dal.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.wlpay.dal.dao.model.MchAlipay;
import org.wlpay.dal.dao.model.MchAlipayExample;

public interface MchAlipayMapper {
    int countByExample(MchAlipayExample example);

    int deleteByExample(MchAlipayExample example);

    int deleteByPrimaryKey(String pid);

    int insert(MchAlipay record);

    int insertSelective(MchAlipay record);

    List<MchAlipay> selectByExample(MchAlipayExample example);

    MchAlipay selectByPrimaryKey(String pid);

    int updateByExampleSelective(@Param("record") MchAlipay record, @Param("example") MchAlipayExample example);

    int updateByExample(@Param("record") MchAlipay record, @Param("example") MchAlipayExample example);

    int updateByPrimaryKeySelective(MchAlipay record);

    int updateByPrimaryKey(MchAlipay record);
}