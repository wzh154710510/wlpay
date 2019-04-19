package org.wlpay.common.vo;

import java.io.Serializable;

/**
 * 商户授权信息
 * @author AngkorW
 *
 */
public class MchAuthentication implements Serializable{


	private static final long serialVersionUID = 1L;

	/**
	 * 登录token
	 */
	private String token;
	
	/**
	 * 商户id
	 */
	private String mchId;
	
	/**
	 * 商户名称
	 */
	private String name;
	/**
	 * 请求私钥
	 */
	private String reqKey;
	/**
	 * 响应私钥
	 */
	private String resKey;
	/**
	 * 用户手机号
	 */
	private String phone;
	
	private String identify;
	
	
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	/**
	 * 用户邮箱
	 */
	private String email;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReqKey() {
		return reqKey;
	}
	public void setReqKey(String reqKey) {
		this.reqKey = reqKey;
	}
	public String getResKey() {
		return resKey;
	}
	public void setResKey(String resKey) {
		this.resKey = resKey;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
