package com.example.demo.util;

import com.example.demo.bean.address.AddressData;
import com.example.demo.bean.address.AddressResult;
import com.example.demo.constant.CommonConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
@Slf4j
public class AddressUtil {
	private static final String key = "http://api.map.baidu.com/geocoder/v2/?output=json&ak" +
			"=Otp3xGbcjtGR8ySbIGImm9XKGdAdHt2N&";
	@Autowired
	private JsonUtil jsonUtil;

	private AddressUtil() {

	}

	private AddressData getAddInfo(StringBuilder builder) throws Exception {
		String url = builder.toString();
		HttpClient client = HttpClients.createDefault(); // 创建默认http连接
		HttpPost post = new HttpPost(url);// 创建一个post请求
		HttpResponse response = client.execute(post);// 用http连接去执行get请求并且获得http响应
		HttpEntity entity = response.getEntity();// 从response中取到响实体
		String backData = EntityUtils.toString(entity);// 把响应实体转成文本
		log.info("返回信息：{}", backData);
		// JSON转对象
		return jsonUtil.jsonToObject(backData, AddressResult.class).getResult();
	}

	/**
	 * 根据经纬度获取详细地址
	 *
	 * @param lng 经度 119.275934
	 * @param lat 纬度 26.116632
	 * @return
	 * @throws IOException
	 */
	public String getAddr(Double lng, Double lat) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append(key).append("location=").append(lat).append(",").append(lng);
		return getAddInfo(builder).getFormattedAddress();
	}

	/**
	 * 根据地址获取经纬度
	 *
	 * @param addr 地址
	 * @return
	 * @throws IOException
	 */
	public String getCoordinate(String addr) throws Exception {
		String address = URLEncoder.encode(addr, CommonConstant.UTF8);
		StringBuilder builder = new StringBuilder();
		builder.append(key).append("address=").append(address);
		return getAddInfo(builder).getLocation().toString();

	}

}
