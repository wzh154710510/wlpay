package org.wlpay.dubbo.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.wlpay.common.constant.PayConstant;
import org.wlpay.dal.dao.mapper.PayOrderMapper;
import org.wlpay.dal.dao.model.PayOrder;
import org.wlpay.dal.dao.model.PayOrderExample;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.List;

/**
 * @author: dingzhiwei
 * @date: 17/10/30
 * @description:
 */
@Service
public class BaseService4PayOrder extends BaseService{

	private static final Logger logger =LoggerFactory.getLogger(BaseService4PayOrder.class);
	
	
    @Autowired
    protected PayOrderMapper payOrderMapper;

    public int baseCreatePayOrder(PayOrder payOrder) {
        return payOrderMapper.insertSelective(payOrder);
    }

    public PayOrder baseSelectPayOrder(String payOrderId) {
        return payOrderMapper.selectByPrimaryKey(payOrderId);
    }

    public PayOrder baseSelectByMchIdAndPayOrderId(String mchId, String payOrderId) {
        PayOrderExample example = new PayOrderExample();
        PayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andMchIdEqualTo(mchId);
        criteria.andPayOrderIdEqualTo(payOrderId);
        List<PayOrder> payOrderList = payOrderMapper.selectByExample(example);
        return CollectionUtils.isEmpty(payOrderList) ? null : payOrderList.get(0);
    }

    public PayOrder baseSelectByMchIdAndMchOrderNo(String mchId, String mchOrderNo) {
        PayOrderExample example = new PayOrderExample();
        PayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andMchIdEqualTo(mchId);
        criteria.andMchOrderNoEqualTo(mchOrderNo);
        List<PayOrder> payOrderList = payOrderMapper.selectByExample(example);
        return CollectionUtils.isEmpty(payOrderList) ? null : payOrderList.get(0);
    }

    public int baseUpdateStatus4Ing(String payOrderId, String channelOrderNo) {
        PayOrder payOrder = new PayOrder();
        payOrder.setStatus(PayConstant.PAY_STATUS_PAYING);
        if(channelOrderNo != null) payOrder.setChannelOrderNo(channelOrderNo);
        payOrder.setPaySuccTime(System.currentTimeMillis());
        PayOrderExample example = new PayOrderExample();
        PayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andPayOrderIdEqualTo(payOrderId);
        criteria.andStatusEqualTo(PayConstant.PAY_STATUS_INIT);
        return payOrderMapper.updateByExampleSelective(payOrder, example);
    }

    public int baseUpdateStatus4Success(String payOrderId) {
        return baseUpdateStatus4Success(payOrderId, null);
    }

    public int baseUpdateStatus4Success(String payOrderId, String channelOrderNo) {
        PayOrder payOrder = new PayOrder();
        payOrder.setPayOrderId(payOrderId);
        payOrder.setStatus(PayConstant.PAY_STATUS_SUCCESS);
        payOrder.setPaySuccTime(System.currentTimeMillis());
        if(StringUtils.isNotBlank(channelOrderNo)) payOrder.setChannelOrderNo(channelOrderNo);
        PayOrderExample example = new PayOrderExample();
        PayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andPayOrderIdEqualTo(payOrderId);
        criteria.andStatusEqualTo(PayConstant.PAY_STATUS_PAYING);
        return payOrderMapper.updateByExampleSelective(payOrder, example);
    }

    public int baseUpdateStatus4Complete(String payOrderId) {
        PayOrder payOrder = new PayOrder();
        payOrder.setPayOrderId(payOrderId);
        payOrder.setStatus(PayConstant.PAY_STATUS_COMPLETE);
        PayOrderExample example = new PayOrderExample();
        PayOrderExample.Criteria criteria = example.createCriteria();
        criteria.andPayOrderIdEqualTo(payOrderId);
        criteria.andStatusEqualTo(PayConstant.PAY_STATUS_SUCCESS);
        return payOrderMapper.updateByExampleSelective(payOrder, example);
    }

    public int baseUpdateNotify(String payOrderId, byte count) {
        PayOrder newPayOrder = new PayOrder();
        newPayOrder.setNotifyCount(count);
        newPayOrder.setLastNotifyTime(System.currentTimeMillis());
        newPayOrder.setPayOrderId(payOrderId);
        return payOrderMapper.updateByPrimaryKeySelective(newPayOrder);
    }

	public PayOrder baseSelectByRealAmountAndMchId(String mchID,String pid, String realAmount,Date listenerTime) {
		PayOrderExample example = new PayOrderExample();
	    PayOrderExample.Criteria criteria = example.createCriteria();
	    criteria.andMchIdEqualTo(mchID);
	    criteria.andRealAmountEqualTo(Long.parseLong(realAmount));
	    criteria.andStatusEqualTo((byte)1);
	    criteria.andExpireTimeGreaterThanOrEqualTo(listenerTime.getTime());
	    List<PayOrder> payOrderList = payOrderMapper.selectByExample(example);
	    if(!CollectionUtils.isEmpty(payOrderList)) {
	    	logger.info("匹配到订单数量为{}", payOrderList.size());
	    	if(payOrderList.size()>1) {
	    		logger.error("订单匹配失败");
	    		return null;
	    	}
	    	logger.info("mchID={},realAmount={},listenerTime={},payorder={}",mchID,realAmount,listenerTime,JSONObject.toJSONString(payOrderList.get(0)));
	    	return payOrderList.get(0);
	    }
        return null;
	}

}
