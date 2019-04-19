package org.wlpay.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.wlpay.common.domain.BaseParam;
import org.wlpay.common.enumm.RetEnum;
import org.wlpay.common.util.*;
import org.wlpay.dal.dao.mapper.MchAlipayMapper;
import org.wlpay.dal.dao.model.MchAlipay;
import org.wlpay.dal.dao.model.MchAlipayExample;
import org.wlpay.dal.dao.model.PayOrder;
import org.wlpay.dal.dao.model.PayOrderExample;
import org.wlpay.dubbo.api.service.IPayOrderService;
import org.wlpay.dubbo.service.BaseService4PayOrder;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: dingzhiwei
 * @date: 17/9/8
 * @description:
 */
@Service(version = "1.0.0")
public class PayOrderServiceImpl extends BaseService4PayOrder implements IPayOrderService {

    private static final MyLog _log = MyLog.getLog(PayOrderServiceImpl.class);

    @Autowired
    private MchAlipayMapper mchAlipayMapper;
    
    @Override
    public Map create(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            _log.warn("新增支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        JSONObject payOrderObj = baseParam.isNullValue("payOrder") ? null : JSONObject.parseObject(bizParamMap.get("payOrder").toString());
        if(payOrderObj == null) {
            _log.warn("新增支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        PayOrder payOrder = BeanConvertUtils.map2Bean(payOrderObj, PayOrder.class);
        if(payOrder == null) {
            _log.warn("新增支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        MchAlipayExample mchAlipayExample=new MchAlipayExample();
        mchAlipayExample.createCriteria().andMchIdEqualTo(payOrder.getMchId()).andStateEqualTo(1);
        List<MchAlipay> mchAlipayList=mchAlipayMapper.selectByExample(mchAlipayExample);
        if(CollectionUtils.isEmpty(mchAlipayList)) {
        	_log.error("商户【{}】未配置收款账户", payOrder.getMchId());
        	return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_RECEVE_ACCOUNT_NOT_EXISTS);
        }
        
        PayOrderExample example=new PayOrderExample();
        example.createCriteria().andMchIdEqualTo(payOrder.getMchId())
        .andRealAmountEqualTo(payOrder.getRealAmount())
        .andStatusEqualTo((byte)1)
        .andExpireTimeGreaterThan(new Date().getTime());
        List<PayOrder> payOrderList=payOrderMapper.selectByExample(example);
        
        if(CollectionUtils.isNotEmpty(payOrderList)) {
        	for(int i=0;i<mchAlipayList.size();i++) {
        		boolean hasPid=false;
        		for(int j=0;j<payOrderList.size();j++) {
        			if(StringUtils.equals(mchAlipayList.get(i).getPid(), payOrderList.get(j).getAlipayPid())) {
        				hasPid=true;
        			}
        		}
        		if(!hasPid) {
        			payOrder.setAlipayPid(mchAlipayList.get(i).getPid());
        		}
        	}
        	if(StringUtils.isBlank(payOrder.getAlipayPid())) {
        		if(Math.abs(payOrder.getRealAmount()-payOrder.getAmount())>20) {
            		return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_SUPERVENE_HIGH);
            	}
            	if((payOrder.getRealAmount()<=payOrder.getAmount())) {     		
            		Long realAmountL=payOrder.getRealAmount()-1;          		
            		realAmountL=((payOrder.getAmount()-realAmountL==20)||realAmountL<=0)?payOrder.getAmount()+1:realAmountL;           				
            		payOrderObj.put("realAmount", realAmountL);
            	}else {
            		payOrderObj.put("realAmount", payOrder.getRealAmount()+1);
            	}
            	Map<String,Object> paramMap = new HashMap<>();
        	    paramMap.put("payOrder", payOrderObj);
        	    String jsonParamAgain = RpcUtil.createBaseParam(paramMap);
        		return create(jsonParamAgain);
        	}
        }else {
        	payOrder.setAlipayPid(mchAlipayList.get(0).getPid());
        }
        int result = super.baseCreatePayOrder(payOrder);
        return RpcUtil.createBizResult(baseParam, result);
    }

    @Override
    public Map select(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            _log.warn("根据支付订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String payOrderId = baseParam.isNullValue("payOrderId") ? null : bizParamMap.get("payOrderId").toString();
        if (ObjectValidUtil.isInvalid(payOrderId)) {
            _log.warn("根据支付订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        PayOrder payOrder = super.baseSelectPayOrder(payOrderId);
        if(payOrder == null) return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_DATA_NOT_EXISTS);
        String jsonResult = JsonUtil.object2Json(payOrder);
        return RpcUtil.createBizResult(baseParam, jsonResult);
    }

    @Override
    public Map selectByMchIdAndPayOrderId(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            _log.warn("根据商户号和支付订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String mchId = baseParam.isNullValue("mchId") ? null : bizParamMap.get("mchId").toString();
        String payOrderId = baseParam.isNullValue("payOrderId") ? null : bizParamMap.get("payOrderId").toString();
        if (ObjectValidUtil.isInvalid(mchId, payOrderId)) {
            _log.warn("根据商户号和支付订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        PayOrder payOrder = super.baseSelectByMchIdAndPayOrderId(mchId, payOrderId);
        if(payOrder == null) return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_DATA_NOT_EXISTS);
        String jsonResult = JsonUtil.object2Json(payOrder);
        return RpcUtil.createBizResult(baseParam, jsonResult);
    }

    @Override
    public Map selectByMchIdAndMchOrderNo(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            _log.warn("根据商户号和商户订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String mchId = baseParam.isNullValue("mchId") ? null : bizParamMap.get("mchId").toString();
        String mchOrderNo = baseParam.isNullValue("mchOrderNo") ? null : bizParamMap.get("mchOrderNo").toString();
        if (ObjectValidUtil.isInvalid(mchId, mchOrderNo)) {
            _log.warn("根据商户号和商户订单号查询支付订单失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        PayOrder payOrder = super.baseSelectByMchIdAndMchOrderNo(mchId, mchOrderNo);
        if(payOrder == null) return RpcUtil.createFailResult(baseParam, RetEnum.RET_BIZ_DATA_NOT_EXISTS);
        String jsonResult = JsonUtil.object2Json(payOrder);
        return RpcUtil.createBizResult(baseParam, jsonResult);
    }

    @Override
    public Map updateStatus4Ing(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            _log.warn("修改支付订单状态为支付中失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String payOrderId = baseParam.isNullValue("payOrderId") ? null : bizParamMap.get("payOrderId").toString();
        String channelOrderNo = baseParam.isNullValue("channelOrderNo") ? null : bizParamMap.get("channelOrderNo").toString();
        if (ObjectValidUtil.isInvalid(payOrderId, channelOrderNo)) {
            _log.warn("修改支付订单状态为支付中失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        int result =  super.baseUpdateStatus4Ing(payOrderId, channelOrderNo);
        return RpcUtil.createBizResult(baseParam, result);
    }

    @Override
    public Map updateStatus4Success(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            _log.warn("修改支付订单状态为支付成功失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String payOrderId = baseParam.isNullValue("payOrderId") ? null : bizParamMap.get("payOrderId").toString();
        if (ObjectValidUtil.isInvalid(payOrderId)) {
            _log.warn("修改支付订单状态为支付成功失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        int result = super.baseUpdateStatus4Success(payOrderId);
        return RpcUtil.createBizResult(baseParam, result);
    }

    @Override
    public Map updateStatus4Complete(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            _log.warn("修改支付订单状态为支付完成失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String payOrderId = baseParam.isNullValue("payOrderId") ? null : bizParamMap.get("payOrderId").toString();
        if (ObjectValidUtil.isInvalid(payOrderId)) {
            _log.warn("修改支付订单状态为支付完成失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        int result =  super.baseUpdateStatus4Complete(payOrderId);
        return RpcUtil.createBizResult(baseParam, result);
    }

    @Override
    public Map updateNotify(String jsonParam) {
        BaseParam baseParam = JsonUtil.getObjectFromJson(jsonParam, BaseParam.class);
        Map<String, Object> bizParamMap = baseParam.getBizParamMap();
        if (ObjectValidUtil.isInvalid(bizParamMap)) {
            _log.warn("修改支付订单通知次数失败, {}. jsonParam={}", RetEnum.RET_PARAM_NOT_FOUND.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_NOT_FOUND);
        }
        String payOrderId = baseParam.isNullValue("payOrderId") ? null : bizParamMap.get("payOrderId").toString();
        Byte count = baseParam.isNullValue("count") ? null : Byte.parseByte(bizParamMap.get("count").toString());
        if (ObjectValidUtil.isInvalid(payOrderId, count)) {
            _log.warn("修改支付订单通知次数失败, {}. jsonParam={}", RetEnum.RET_PARAM_INVALID.getMessage(), jsonParam);
            return RpcUtil.createFailResult(baseParam, RetEnum.RET_PARAM_INVALID);
        }
        int result = super.baseUpdateNotify(payOrderId, count);
        return RpcUtil.createBizResult(baseParam, result);
    }
}
