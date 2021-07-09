package com.example.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

/**
 * @author 黄永琦
 * @description
 * @date 2021/6/29
 */

@RestController
@RequestMapping("/uploadProgress")
@Api("文件上传进度")
@Slf4j
public class UploadController {

	// 上传文件根路径
	@Value("${file.path}")
	private String fileRootPath;

	@GetMapping(value = "/showUpload")
	public ModelAndView showUpload() {
		return new ModelAndView("/UploadProgressDemo");
	}

	@PostMapping("/upload")
	@ApiOperation("上传文件")
	public void uploadFile(MultipartFile multipartFile) throws IOException {
		// https://blog.csdn.net/qq_27607579/article/details/77914958
		String originalFilename = multipartFile.getOriginalFilename();
		log.info("原始文件名: {}", originalFilename);
		File path = new File(fileRootPath);
		if (!path.exists() && !path.isDirectory()) {
			path.mkdirs();
		}
		File file = new File(path + File.separator + originalFilename);
		multipartFile.transferTo(file);
		log.info("上传后文件的全路径: {}", file.getAbsolutePath());

	}

}
