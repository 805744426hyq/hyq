package com.example.demo.bean.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/9
 */
@Data
public class AddressData {
	private AddressComponent addressComponent;
	private String business;
	private String cityCode;
	@JsonProperty("formatted_address")
	private String formattedAddress;

	private Location location;

	@JsonProperty("sematic_description")
	private String sematicDescription;

}
