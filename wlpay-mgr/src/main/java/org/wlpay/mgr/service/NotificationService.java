package org.wlpay.mgr.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.wlpay.dal.dao.mapper.NotificationMapper;
import org.wlpay.dal.dao.model.Notification;
import org.wlpay.mgr.controller.rest.NotifyRestController;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.http.HttpUtil;

@Component
public class NotificationService {

	@Autowired
	private NotificationMapper basemaMapper;
	private static final Logger logger=LoggerFactory.getLogger(NotificationService.class);
	
	@Value("${rsa.public.key}")
	private String RSAPUBLICKEY;
	
	@Value("${rsa.private.key}")
	private String RSAPRIVATEKEY;
	
	@Value("${pay.server.url.notice}")
	private String PAYSERVERURLNOTICE;
	
	public boolean insertNotification(JSONObject jsonObj) {
		String mercId=jsonObj.getString("mercId");
		String channel=jsonObj.getString("channel");
		String userPayName=jsonObj.getString("userPayName");
		String amount=jsonObj.getString("amount");
		Date listenerTime=new Date(jsonObj.getLongValue("listenerTime"));
		String notifiTiTle=jsonObj.getString("notifiTiTle");
		String notifiText=jsonObj.getString("notifiText");
		Notification notification=new Notification();
		notification.setAmount(amount);
		notification.setChannel(channel);
		notification.setCreateTime(new Date());
		notification.setListenerTime(listenerTime);
		notification.setMchID(mercId);
		notification.setNotifiText(notifiText);
		notification.setNotifiTiTle(notifiTiTle);
		notification.setUserPayName(userPayName);
		int i=basemaMapper.insertSelective(notification);
		if(i>0) {
			Map<String,Object> paramMap=new HashMap<String,Object>();
			String notificationStr=JSONObject.toJSONString(notification);
			String rsaEncodeStr=SecureUtil.rsa(RSAPRIVATEKEY, RSAPUBLICKEY).encryptBase64(notificationStr, KeyType.PublicKey);
			paramMap.put("params", rsaEncodeStr);
			logger.info("发送HTTP请求{},参数为{}",PAYSERVERURLNOTICE,paramMap);
			String result=HttpUtil.post(PAYSERVERURLNOTICE, paramMap);
			return true;
		}
		return false;
	}
	
}
