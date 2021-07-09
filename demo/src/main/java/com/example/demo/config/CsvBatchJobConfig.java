package com.example.demo.config;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author 黄永琦
 * @description
 * @date 2021/7/6
 */
@Configuration
public class CsvBatchJobConfig {
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final DataSource dataSource;

	@Autowired
	public CsvBatchJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
	                         DataSource dataSource) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.dataSource = dataSource;
	}

}
