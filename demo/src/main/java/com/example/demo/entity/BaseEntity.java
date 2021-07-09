package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 黄永琦
 * @description: MappedSuperclass 父类不对应任何单独的表，多个子类共用相同的属性
 * @date 2021/6/7
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@MappedSuperclass
@Getter
@Setter
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -4135700415986114364L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CreatedDate
	private Date createdDate;

	@LastModifiedDate
	private Date modifyDate;

}
