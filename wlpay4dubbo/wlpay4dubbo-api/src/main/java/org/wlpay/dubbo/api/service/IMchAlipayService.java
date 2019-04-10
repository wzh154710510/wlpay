package org.wlpay.dubbo.api.service;

import com.alibaba.fastjson.JSONArray;

public interface IMchAlipayService {

	JSONArray selectByMchId(String mchId);

}
