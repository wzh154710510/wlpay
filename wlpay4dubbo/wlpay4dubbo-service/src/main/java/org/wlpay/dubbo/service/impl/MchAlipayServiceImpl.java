package org.wlpay.dubbo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.wlpay.dal.dao.mapper.MchAlipayMapper;
import org.wlpay.dal.dao.model.MchAlipay;
import org.wlpay.dal.dao.model.MchAlipayExample;
import org.wlpay.dubbo.api.service.IMchAlipayService;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service(version = "1.0.0")
public class MchAlipayServiceImpl implements IMchAlipayService{
	
	@Autowired
	private MchAlipayMapper baseMapper;
	
	@Override
	public JSONArray selectByMchId(String mchId) {
		MchAlipayExample example=new MchAlipayExample();
		example.createCriteria().andMchIdEqualTo(mchId).andStateEqualTo(1);
		List<MchAlipay>  mchAlipayList=baseMapper.selectByExample(example);
		return JSONArray.parseArray(JSONObject.toJSONString(mchAlipayList));
	}

}
