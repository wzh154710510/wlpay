package org.wlpay.mgr.controller.rest;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wlpay.common.domain.RespResult;
import org.wlpay.dal.dao.model.Notification;
import org.wlpay.mgr.service.NotificationService;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SignAlgorithm;

@RestController
@RequestMapping("rest/notify")
public class NotifyRestController {
	
	private static final String RSAPRIVATEKEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANA/UT013Wkz9ATkswy1dObtHIlbGouwdZDi7WemP80JfJmz8l6BM1Mcv0NTNgualMkowx1frdVM/hf5/EUa1eO9a9QMRwfLKw4HmR4Z/GjIxUMuoH19RRtUyl5KqbUWQhvmiK4P8SozZNRtGs1966y3891AU1siRjJ9O0F6zyFhAgMBAAECgYBgbEDob61yTcz6Cw7gnpBfcICbFOTosy7shDZanL7D6FWaMZjSq04heJkSyk+m9c9YiRg23Jt3vSuV3HkzYiaQc7iuaYETS4Eg+z2u9cEnDJ451+Pp6sowWF4xpQyzuT44LpxxD+EtN4KU5VAGix3nt7xrQpL8wPt4waB1ARGKJQJBAO1od8qVDa3ntEO5RRvTYX26t1rQqtG78Z2yUC5PZ7LYdyftT/d3ZNWQo7YnIQnafWUQMewMW94FkHGHC0KSptcCQQDgjjzls5cM2dM6Y/4Mwzh+43LLvYSLOUuS6hxf38RII3d34Ug1h0BakNKouG9fjf48Y3Zs1VcfZpcWC4oJ2UqHAkAu5Z4URJFpMwYnAqgmMQFnJsUliWbU+31lG14/Z6wiZiRTnMtZJRqkHWhZiS07lw+CMUrLSD21g//ejUCqK/INAkBZIXwgfJk/G5LRAXGMZUyeaO9e2Vmr3VEW/l/G2k3RiGpaJgyAWq/7grJrD9H9LqI0HBKcfk23hAQCVxAyepIvAkBnmo121SUVnnpfrL0B2omeystYg3abma2M50S2LyYI5Lc8dVzKdLLf9X3cJyserPubpOhF3hhKwTCftDkgxZhO";
	private static final String RSAPUBLICKEY  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQP1E9Nd1pM/QE5LMMtXTm7RyJWxqLsHWQ4u1npj/NCXyZs/JegTNTHL9DUzYLmpTJKMMdX63VTP4X+fxFGtXjvWvUDEcHyysOB5keGfxoyMVDLqB9fUUbVMpeSqm1FkIb5oiuD/EqM2TUbRrNfeust/PdQFNbIkYyfTtBes8hYQIDAQAB";
	
	private static final Logger logger=LoggerFactory.getLogger(NotifyRestController.class);
	
	@Autowired
	private NotificationService notificationService;
	
	public static void main(String[] args) {
		String[] a= {"a","b"};		
		System.out.println(a.length);		
	}
	
	/**
	 * m 为支付 金额
	 * @param m
	 * @return
	 */
	@PostMapping(value = "alipay")
	public RespResult<Object> notifyAlipay(String params){
		if(StringUtils.isBlank(params)) {
			return RespResult.buildErrorMessage("参数传递有误");
		}
		String jsonParams="";
		try {
			jsonParams=SecureUtil.rsa(RSAPRIVATEKEY, RSAPUBLICKEY).decryptStr(params, KeyType.PrivateKey);
		} catch (Exception e) {
			e.printStackTrace();
			return RespResult.buildErrorMessage("参数传递有误");
		}
		JSONObject jsonObj=JSONObject.parseObject(jsonParams);
		logger.info("收到支付回调{}",jsonObj);
		boolean flag=notificationService.insertNotification(jsonObj);
		if(!flag) {
			return RespResult.buildErrorMessage("系统异常");
		}
		return RespResult.buildSuccessMessage();
	}
	
	/*
	 * public static void main(String[] args) {
	 * 
	 * KeyPair
	 * pair=SecureUtil.generateKeyPair(SignAlgorithm.SHA256withRSA.getValue());
	 * 
	 * 
	 * System.out.println(pair.getPrivate().toString());
	 * 
	 * System.out.println(pair.getPublic().toString());
	 * 
	 * System.out.println(Base64.encode(pair.getPrivate().getEncoded()));
	 * 
	 * System.out.println(Base64.encode(pair.getPublic().getEncoded()));
	 * //PrivateKey
	 * privateKey=SecureUtil.generatePrivateKey(SignAlgorithm.SHA256withRSA.getValue
	 * (), new RSAPrivateKeySpec(1,2));
	 * //System.out.println(privateKey.getAlgorithm()); //SecureUtil.rsa().
	 * 
	 * String encodeStr=SecureUtil.rsa(
	 * "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANA/UT013Wkz9ATkswy1dObtHIlbGouwdZDi7WemP80JfJmz8l6BM1Mcv0NTNgualMkowx1frdVM/hf5/EUa1eO9a9QMRwfLKw4HmR4Z/GjIxUMuoH19RRtUyl5KqbUWQhvmiK4P8SozZNRtGs1966y3891AU1siRjJ9O0F6zyFhAgMBAAECgYBgbEDob61yTcz6Cw7gnpBfcICbFOTosy7shDZanL7D6FWaMZjSq04heJkSyk+m9c9YiRg23Jt3vSuV3HkzYiaQc7iuaYETS4Eg+z2u9cEnDJ451+Pp6sowWF4xpQyzuT44LpxxD+EtN4KU5VAGix3nt7xrQpL8wPt4waB1ARGKJQJBAO1od8qVDa3ntEO5RRvTYX26t1rQqtG78Z2yUC5PZ7LYdyftT/d3ZNWQo7YnIQnafWUQMewMW94FkHGHC0KSptcCQQDgjjzls5cM2dM6Y/4Mwzh+43LLvYSLOUuS6hxf38RII3d34Ug1h0BakNKouG9fjf48Y3Zs1VcfZpcWC4oJ2UqHAkAu5Z4URJFpMwYnAqgmMQFnJsUliWbU+31lG14/Z6wiZiRTnMtZJRqkHWhZiS07lw+CMUrLSD21g//ejUCqK/INAkBZIXwgfJk/G5LRAXGMZUyeaO9e2Vmr3VEW/l/G2k3RiGpaJgyAWq/7grJrD9H9LqI0HBKcfk23hAQCVxAyepIvAkBnmo121SUVnnpfrL0B2omeystYg3abma2M50S2LyYI5Lc8dVzKdLLf9X3cJyserPubpOhF3hhKwTCftDkgxZhO"
	 * ,
	 * "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQP1E9Nd1pM/QE5LMMtXTm7RyJWxqLsHWQ4u1npj/NCXyZs/JegTNTHL9DUzYLmpTJKMMdX63VTP4X+fxFGtXjvWvUDEcHyysOB5keGfxoyMVDLqB9fUUbVMpeSqm1FkIb5oiuD/EqM2TUbRrNfeust/PdQFNbIkYyfTtBes8hYQIDAQAB"
	 * ).encryptBase64("wanzhiheng", KeyType.PublicKey);
	 * System.out.println(encodeStr); ;
	 * 
	 * String decodeStr=SecureUtil.rsa(
	 * "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBANA/UT013Wkz9ATkswy1dObtHIlbGouwdZDi7WemP80JfJmz8l6BM1Mcv0NTNgualMkowx1frdVM/hf5/EUa1eO9a9QMRwfLKw4HmR4Z/GjIxUMuoH19RRtUyl5KqbUWQhvmiK4P8SozZNRtGs1966y3891AU1siRjJ9O0F6zyFhAgMBAAECgYBgbEDob61yTcz6Cw7gnpBfcICbFOTosy7shDZanL7D6FWaMZjSq04heJkSyk+m9c9YiRg23Jt3vSuV3HkzYiaQc7iuaYETS4Eg+z2u9cEnDJ451+Pp6sowWF4xpQyzuT44LpxxD+EtN4KU5VAGix3nt7xrQpL8wPt4waB1ARGKJQJBAO1od8qVDa3ntEO5RRvTYX26t1rQqtG78Z2yUC5PZ7LYdyftT/d3ZNWQo7YnIQnafWUQMewMW94FkHGHC0KSptcCQQDgjjzls5cM2dM6Y/4Mwzh+43LLvYSLOUuS6hxf38RII3d34Ug1h0BakNKouG9fjf48Y3Zs1VcfZpcWC4oJ2UqHAkAu5Z4URJFpMwYnAqgmMQFnJsUliWbU+31lG14/Z6wiZiRTnMtZJRqkHWhZiS07lw+CMUrLSD21g//ejUCqK/INAkBZIXwgfJk/G5LRAXGMZUyeaO9e2Vmr3VEW/l/G2k3RiGpaJgyAWq/7grJrD9H9LqI0HBKcfk23hAQCVxAyepIvAkBnmo121SUVnnpfrL0B2omeystYg3abma2M50S2LyYI5Lc8dVzKdLLf9X3cJyserPubpOhF3hhKwTCftDkgxZhO"
	 * ,
	 * "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDQP1E9Nd1pM/QE5LMMtXTm7RyJWxqLsHWQ4u1npj/NCXyZs/JegTNTHL9DUzYLmpTJKMMdX63VTP4X+fxFGtXjvWvUDEcHyysOB5keGfxoyMVDLqB9fUUbVMpeSqm1FkIb5oiuD/EqM2TUbRrNfeust/PdQFNbIkYyfTtBes8hYQIDAQAB"
	 * ).decryptStr(encodeStr, KeyType.PrivateKey); System.out.println(decodeStr); }
	 */
	
}
