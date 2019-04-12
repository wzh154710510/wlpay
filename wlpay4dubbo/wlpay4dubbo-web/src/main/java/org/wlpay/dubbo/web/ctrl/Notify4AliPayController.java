package org.wlpay.dubbo.web.ctrl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.wlpay.common.constant.PayConstant;
import org.wlpay.common.domain.RespResult;
import org.wlpay.common.util.MyLog;
import org.wlpay.dubbo.web.service.NotifyPayService;

import com.alibaba.fastjson.JSONObject;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description: 接收处理支付宝通知
 * @author dingzhiwei jmdhappy@126.com
 * @date 2017-07-05
 * @version V1.0
 * @Copyright: www.xxpay.org
 */
@RestController
public class Notify4AliPayController {

	private static final MyLog _log = MyLog.getLog(Notify4AliPayController.class);

	@Autowired
	private NotifyPayService notifyPayService;
	
	@Value("${rsa.public.key}")
	private String RSAPUBLICKEY;
	
	@Value("${rsa.private.key}")
	private String RSAPRIVATEKEY;

	/**
	 * 支付宝移动支付后台通知响应
	 * @param request
	 * @return
	 * @throws ServletException
	 * @throws IOException
     */
	@RequestMapping(value = "/notify/pay/aliPayNotifyRes.htm")
	@ResponseBody
	public String aliPayNotifyRes(HttpServletRequest request) throws ServletException, IOException {
		_log.info("====== 开始接收支付宝支付回调通知 ======");
		String notifyRes = doAliPayRes(request);
		_log.info("响应给支付宝:{}", notifyRes);
		_log.info("====== 完成接收支付宝支付回调通知 ======");
		return notifyRes;
	}
	
	@PostMapping("notify/alipay")
	public RespResult<Object> notifyAlipay(String params) {
		_log.info("====== 开始接收支付宝支付回调通知 ======,params={}",params);
		if(StringUtils.isEmpty(params)) {
			return RespResult.buildErrorMessage();
		}
		String rsaDecodeStr="";
		try {
			rsaDecodeStr=SecureUtil.rsa(RSAPRIVATEKEY, RSAPUBLICKEY).decryptStr(params, KeyType.PrivateKey);
		} catch (Exception e) {
			e.printStackTrace();
			return RespResult.buildErrorMessage();
		}
		
		boolean flag=notifyPayService.doAliPayIndividualNotify(rsaDecodeStr);
		_log.info("====== 完成接收支付宝支付回调通知 ======");
		if(!flag) {
			return RespResult.buildErrorMessage();
		}
		return RespResult.buildSuccessMessage();	
	}
	

	public String doAliPayRes(HttpServletRequest request) throws ServletException, IOException {
		String logPrefix = "【支付宝支付回调通知】";
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		_log.info("{}通知请求数据:reqStr={}", logPrefix, params);
		if(params.isEmpty()) {
			_log.error("{}请求参数为空", logPrefix);
			return PayConstant.RETURN_ALIPAY_VALUE_FAIL;
		}
		return notifyPayService.doAliPayNotify(params);
	}
	
	
	
	
	
}
