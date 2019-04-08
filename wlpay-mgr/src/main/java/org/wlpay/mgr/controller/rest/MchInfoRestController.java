package org.wlpay.mgr.controller.rest;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wlpay.common.domain.RespResult;
import org.wlpay.mgr.service.MchInfoService;

@RestController
@RequestMapping("/rest/mch_info")
public class MchInfoRestController {
	
	@Autowired
	private MchInfoService mchInfoService;
	
	@RequestMapping("login")
	public RespResult<Object> login(String username,String password){
		if(Objects.isNull(username)||Objects.isNull(password)) {
			return RespResult.buildErrorMessage("参数有误");
		}
		return mchInfoService.login(username,password);
	}
	
}
