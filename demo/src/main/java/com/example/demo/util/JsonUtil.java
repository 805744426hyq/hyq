package com.example.demo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * json工具类 jackson和java bean互相转换
 *
 * @author 黄永琦
 */
@Component
public class JsonUtil {

	private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
	private final ObjectMapper om;

	@Autowired
	public JsonUtil(ObjectMapper om) {
		this.om = om;
	}

	/**
	 * 对象 => json字符串
	 *
	 * @param obj 源对象
	 */
	public <T> String objectToJson(T obj) {
		if (Objects.nonNull(obj)) {
			try {
				return om.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				log.warn(e.getMessage(), e);
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		return null;
	}

	/**
	 * json字符串 => 对象
	 *
	 * @param json  源json串
	 * @param clazz 对象类
	 * @param <T>   泛型
	 */
	public <T> T jsonToObject(String json, Class<T> clazz) {
		return parse(json, clazz, null);
	}

	/**
	 * json字符串 => 对象
	 *
	 * @param json 源json串
	 * @param type 对象类型
	 * @param <T>  泛型
	 */
	public <T> T parse(String json, TypeReference<?> type) {

		return parse(json, null, type);
	}

	/**
	 * json => 对象处理方法 <br>
	 * 参数clazz和type必须一个为null，另一个不为null <br>
	 *
	 * @param json  源json串
	 * @param clazz 对象类
	 * @param type  对象类型
	 * @param <T>   泛型
	 */
	@SuppressWarnings("unchecked")
	private <T> T parse(String json, Class<T> clazz, TypeReference<?> type) {

		T obj = null;
		if (StringUtils.isNotEmpty(json)) {
			try {
				boolean b = Objects.nonNull(clazz);
				obj = b ? om.readValue(json, clazz) : (T) om.readValue(json, type);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
				throw new IllegalArgumentException(e.getMessage());
			}
		}
		return obj;
	}

	/**
	 * 校验参数是否是json类型
	 *
	 * @param content
	 * @return
	 */
	public boolean isJsonType(String content) {
		try {
			om.readTree(content);
		} catch (JsonProcessingException e) {
			return false;
		}
		return true;
	}

	public byte[] writeValueAsBytes(Object value) {
		try {
			return om.writeValueAsBytes(value);
		} catch (JsonProcessingException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}
}
