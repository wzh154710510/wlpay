package org.wlpay.dubbo.web;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.crypto.SecretKey;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.ImageUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

public class TestController {

	
	public static void main(String[] args) throws Exception, IOException {
		String aliPayRedirectUrl="alipays://platformapi/startapp?appId=10000011&url=http://www.baidu.com";
		
		String str=Base64Encoder.encode(QrCodeUtil.generatePng(aliPayRedirectUrl, 320, 320));
		
		System.out.println(str);
		
		
		String encodestr=SecureUtil.aes("qwertyuiopasdfgh".getBytes()).encryptBase64("wanzhiheng");
		
		System.out.println(encodestr);
		
		
		String decodeStr=SecureUtil.aes("qwertyuiopasdfgh".getBytes()).decryptStr("B/P7l64pAKvV9fG4bE2wdA==");
		
		System.out.println(decodeStr);
	}
}
