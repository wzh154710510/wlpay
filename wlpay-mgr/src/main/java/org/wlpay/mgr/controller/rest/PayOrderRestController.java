package org.wlpay.mgr.controller.rest;

import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wlpay.common.domain.RespResult;
import org.wlpay.common.util.AmountUtil;
import org.wlpay.common.util.DateUtil;
import org.wlpay.common.util.JWTUtil;
import org.wlpay.common.util.MyLog;
import org.wlpay.dal.dao.model.PayOrder;
import org.wlpay.dal.dao.plugin.PageModel;
import org.wlpay.mgr.controller.PayOrderController;
import org.wlpay.mgr.service.PayOrderService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/rest/pay_order")
public class PayOrderRestController {

	private static final Logger logger=LoggerFactory.getLogger(PayOrderRestController.class);
	
	
	private final static MyLog _log = MyLog.getLog(PayOrderController.class);

	@Autowired
	private PayOrderService payOrderService;

	@PostMapping("list")
	public RespResult<Object> list(HttpServletRequest request, Integer current, Integer size, PayOrder payOrder) {
		
		
		String token = request.getHeader("Authentication");
		String mchId = "";
		try {
			mchId = JWTUtil.getParam(token);
		} catch (Exception e) {
			// TODO: handle exception
			return RespResult.buildErrorMessage();
		}
		logger.info("current={},size={},mchId={}\r\ntoken={}",current,size,mchId,token);
		payOrder.setMchId(mchId);
		PageModel pageModel = new PageModel();
		int count = payOrderService.count(payOrder);
		if (count <= 0) {
			return RespResult.buildSuccessMessage();
		}
		List<PayOrder> payOrderList = payOrderService.getPayOrderList((current - 1) * size, size, payOrder);
		logger.info("result size={}",payOrderList.size());
		if (!CollectionUtils.isEmpty(payOrderList)) {
			JSONArray array = new JSONArray();
			for (PayOrder po : payOrderList) {
				JSONObject object = (JSONObject) JSONObject.toJSON(po);
				if (po.getCreateTime() != null)
					object.put("createTime", DateUtil.date2Str(po.getCreateTime()));
				if (po.getAmount() != null)
					object.put("amount", AmountUtil.convertCent2Dollar(po.getAmount() + ""));
				if(po.getRealAmount() !=null) 
					object.put("realAmount", AmountUtil.convertCent2Dollar(po.getRealAmount() + ""));
				array.add(object);
			}
			pageModel.setList(array);
		}
		pageModel.setCount(count);
		pageModel.setMsg("ok");
		pageModel.setRel(true);
		return RespResult.buildSuccessMessage(pageModel);
	}

}
