package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/7
 */
@Component
@Slf4j
public class CheckTokenUtil {
	@Value("${checkToken.url}")
	private static String checkTokeUrl;

	public static JSONObject checkToken(String token) {
		// token校验通过了再去解析
		String result = doPostSSL(checkTokeUrl, token);
		JSONObject resultObject = JSONObject.parseObject(result);
		if (Objects.isNull(resultObject)) {
			throw new RuntimeException("统一认证系统返回异常!");
		}
		if (!resultObject.getString("code").equals("0")) {
			throw new RuntimeException(resultObject.getString("message"));
		}
		String[] contexts = token.split("\\.");
		// token第二段带的是用户信息
		String s = new String(Base64.decodeBase64URLSafe(contexts[1]));
		return JSONObject.parseObject(s);
	}

	private static String doPostSSL(String url, String token) {
		// 创建Httpclient对
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			SSLConnectionSocketFactory SCSF =
					new SSLConnectionSocketFactory(SSLContexts.custom().loadTrustMaterial(null,
					                                                                      new TrustSelfSignedStrategy()).build(),
					                               NoopHostnameVerifier.INSTANCE);
			//创建自定义的httpclient对象
			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(SCSF).build();
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
			httpPost.setHeader("x-token", token);
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
			log.info("请求验证token，url：{}， token：{}，result：{}", url, token, resultString);
		} catch (Exception e) {
			log.error("请求验证token失败， url：{}, token:{},异常原因:{}, 异常信息:{}", url, token, e.getCause(), e.getMessage());
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
			}
		}
		return resultString;
	}
}
