package com.example.demo.bean.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 黄永琦
 * @description JsonProperty把带下划线的json字段转成驼峰命名并注入到类中
 * JSONField把实体类的属性改成带下划线的json属性名
 * @date 2021/7/9
 */
@Data
public class AddressComponent {

	private String adcode;

	@JsonProperty("city_level")
	private Integer cityLevel;

	private String country;

	@JsonProperty("country_code")
	private String countryCode;

	@JsonProperty("country_code_iso")
	private String countryCodeIso;

	@JsonProperty("country_code_iso2")
	private String countryCodeIso2;

	private String direction;

	private String distance;

	private String province;

	private String city;

	private String district;

	private String town;

	private String street;

	@JsonProperty("street_number")
	private String streetNumber;

	@JsonProperty("town_code")
	private String townCode;

}
