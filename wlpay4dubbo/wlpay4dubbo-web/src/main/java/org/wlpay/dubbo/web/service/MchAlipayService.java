package org.wlpay.dubbo.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class MchAlipayService {

	 @Autowired
	 private RpcCommonService rpcCommonService;
	 
	 
	 
	 public JSONArray getByMchId(String mchId) {
		 JSONArray array=rpcCommonService.rpcMchAlipayService.selectByMchId(mchId);
		 return null;
	 }
	
}
