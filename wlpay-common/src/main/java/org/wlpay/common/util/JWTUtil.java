package org.wlpay.common.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import cn.hutool.core.date.DateUtil;

public class JWTUtil {
	
	private static final String SECRET="asdigqwd%W7R4SCKGHSJDVAGV*/SVDSDG3asgdsdv(]~``osfdh]{";
	
	private static final Integer offsetMinute=30;
	
	public static String createJWT(String mchId) {
		return createJWT(mchId,null);
	}
	
	public static String createJWT(String mchId,Long expriceTimeStamp) {
		Date iatDate = new Date();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
		String token = JWT.create().withHeader(map) 
				.withClaim("iss", "Service") 
				.withClaim("aud", "ALL")
				.withClaim("mchId", null == mchId ? null : mchId.toString())
				.withIssuedAt(iatDate) 
				.withExpiresAt(Objects.nonNull(expriceTimeStamp)?new Date(expriceTimeStamp):DateUtil.offsetMinute(new Date(iatDate.getTime()), offsetMinute)) 
				.sign(Algorithm.HMAC256(SECRET)); 
		return token;
	}
	
	/**
	 * 解密Token
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Claim> verifyToken(String token) throws JWTVerificationException{
		DecodedJWT jwt = null;
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
			jwt = verifier.verify(token);
		} catch (Exception e) {
			// e.printStackTrace();
			// token 校验失败, 抛出Token验证非法异常
			throw new JWTVerificationException("token 验证失败");
		}
		return jwt.getClaims();
	}
 
	/**
	 * 根据Token获取user_id
	 * 
	 * @param token
	 * @return user_id
	 */
	public static String getParam(String token) throws JWTVerificationException{
		Map<String, Claim> claims = verifyToken(token);
		Claim user_id_claim = claims.get("mchId");
		if (null == user_id_claim || StringUtils.isEmpty(user_id_claim.asString())) {
			// token 校验失败, 抛出Token验证非法异常
			throw new JWTVerificationException("token 验证失败");
		}
		return user_id_claim.asString();
	}

	
	public static void main(String[] args) {
		System.out.println(createJWT("123"));
	}

}
