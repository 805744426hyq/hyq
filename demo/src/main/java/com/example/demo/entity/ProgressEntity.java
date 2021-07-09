package com.example.demo.entity;

import org.springframework.stereotype.Component;

import java.time.Clock;

/**
 * @author 黄永琦
 * @description
 * @date 2021/6/29
 */
@Component
public class ProgressEntity {
	public static final String PROGRESS = "%";
	// 读取的文件的比特数
	private long pBytesRead = 0L;
	// 文件的总大小
	private long pContentLength = 0L;
	// 目前正在读取第几个文件
	private int pItems;

	private long startTime = Clock.systemDefaultZone().millis();

	public ProgressEntity() {
		pBytesRead = 0L;
		pContentLength = 0L;
	}

	public long getPBytesRead() {
		return pBytesRead;
	}

	public void setPBytesRead(long bytesRead) {
		pBytesRead = bytesRead;
	}

	public long getPContentLength() {
		return pContentLength;
	}

	public void setPContentLength(long contentLength) {
		pContentLength = contentLength;
	}

	public int getPItems() {
		return pItems;
	}

	public void setPItems(int items) {
		pItems = items;
	}

	@Override
	public String toString() {
		float tmp = (float) pBytesRead;
		float result = tmp / pContentLength * 100;
		return result + PROGRESS;
	}

}
