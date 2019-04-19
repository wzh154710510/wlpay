package org.wlpay.mgr.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.wlpay.common.domain.RespResult;
import org.wlpay.common.util.JWTUtil;
import org.wlpay.common.vo.MchAuthentication;
import org.wlpay.dal.dao.mapper.MchAlipayMapper;
import org.wlpay.dal.dao.mapper.MchInfoMapper;
import org.wlpay.dal.dao.model.MchAlipay;
import org.wlpay.dal.dao.model.MchAlipayExample;
import org.wlpay.dal.dao.model.MchInfo;
import org.wlpay.dal.dao.model.MchInfoExample;

import java.util.List;

/**
 * Created by dingzhiwei on 17/5/4.
 */
@Component
public class MchInfoService {
	
	
	private static final Logger logger=LoggerFactory.getLogger(MchInfoService.class);

    @Autowired
    private MchInfoMapper mchInfoMapper;

    @Autowired
    private MchAlipayMapper mchAlipayMapper;
    
    public int addMchInfo(MchInfo mchInfo) {
        MchInfoExample example = new MchInfoExample();
        example.setOrderByClause("mchId DESC");
        example.setOffset(0);
        example.setLimit(1);
        List<MchInfo> mchInfos = mchInfoMapper.selectByExample(example);
        String mchId = "10000000";
        if(!CollectionUtils.isEmpty(mchInfos)) {
            mchId = String.valueOf(Integer.parseInt(mchInfos.get(0).getMchId()) + 1);
        }
        mchInfo.setMchId(mchId);
        return mchInfoMapper.insertSelective(mchInfo);
    }

    public int updateMchInfo(MchInfo mchInfo) {
        return mchInfoMapper.updateByPrimaryKeySelective(mchInfo);
    }

    public MchInfo selectMchInfo(String mchId) {
        return mchInfoMapper.selectByPrimaryKey(mchId);
    }

    public List<MchInfo> getMchInfoList(int offset, int limit, MchInfo mchInfo) {
        MchInfoExample example = new MchInfoExample();
        example.setOrderByClause("createTime DESC");
        example.setOffset(offset);
        example.setLimit(limit);
        MchInfoExample.Criteria criteria = example.createCriteria();
        setCriteria(criteria, mchInfo);
        return mchInfoMapper.selectByExample(example);
    }

    public Integer count(MchInfo mchInfo) {
        MchInfoExample example = new MchInfoExample();
        MchInfoExample.Criteria criteria = example.createCriteria();
        setCriteria(criteria, mchInfo);
        return mchInfoMapper.countByExample(example);
    }

    void setCriteria(MchInfoExample.Criteria criteria, MchInfo mchInfo) {
        if(mchInfo != null) {
            if(StringUtils.isNotBlank(mchInfo.getMchId())) criteria.andMchIdEqualTo(mchInfo.getMchId());
            if(mchInfo.getType() != null && !"-99".equals(mchInfo.getType())) criteria.andTypeEqualTo(mchInfo.getType());
        }
    }

	public RespResult<Object> login(String username, String password,String alipayAccount) {
		MchInfoExample example = new MchInfoExample();
		example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
		List<MchInfo> mchInfos= mchInfoMapper.selectByExample(example);
		if(org.apache.commons.collections.CollectionUtils.isEmpty(mchInfos)) {
			return RespResult.buildErrorMessage("登录失败，用户名或密码错误");
		}
		MchInfo mchInfo=mchInfos.get(0);
		
		MchAlipayExample alipayExample=new MchAlipayExample();
		alipayExample.createCriteria().andMchIdEqualTo(mchInfo.getMchId()).andStateEqualTo(1).andIdentifyEqualTo(alipayAccount);
		List<MchAlipay> alipays=mchAlipayMapper.selectByExample(alipayExample);
		if(org.apache.commons.collections.CollectionUtils.isEmpty(alipays)) {
			return RespResult.buildErrorMessage("收款账号不存在");
		}
		MchAuthentication mchAuthentication=new MchAuthentication();
		mchAuthentication.setEmail(mchInfo.getEmail());
		mchAuthentication.setMchId(mchInfo.getMchId());
		mchAuthentication.setName(mchInfo.getName());
		mchAuthentication.setPhone(mchInfo.getPhone());
		mchAuthentication.setToken(JWTUtil.createJWT(mchInfo.getMchId()));
		return RespResult.buildSuccessMessage(mchAuthentication);
	}

}
