package com.example.demo.aop;

import com.example.demo.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 黄永琦
 * @description 自定义注解切面
 * @date 2021/6/11
 * @version: 1.0
 * @company: 数研院(福建)信息产业发展有限公司
 */
@Aspect
public class WebLogAspect {
	/**
	 * 换行符
	 */
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
	@Autowired
	private JsonUtil jsonUtil;

	@Pointcut("@annotation(com.example.demo.aop.WebLog)")
	public void webLog() {
	}

	/**
	 * 在切点之前织入
	 *
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Throwable {
		// 开始打印请求日志
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 打印请求相关参数
		logger.info("========================================== Start ==========================================");
		// 打印请求 url
		logger.info("URL            : {}", request.getRequestURL().toString());
		// 打印 Http method
		logger.info("HTTP Method    : {}", request.getMethod());
		// 打印调用 controller 的全路径以及执行方法
		logger.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(),
		            joinPoint.getSignature().getName());
		// 打印请求的 IP
		logger.info("IP             : {}", request.getRemoteAddr());
		// 打印请求入参
		logger.info("Request Args   : {}", jsonUtil.objectToJson(joinPoint.getArgs()));
	}

	/**
	 * 在切点之后织入
	 *
	 * @throws Throwable
	 */
	@After("webLog()")
	public void doAfter() throws Throwable {
		// 接口结束后换行，方便分割查看
		logger.info("=========================================== End ===========================================" + LINE_SEPARATOR);
	}

	/**
	 * 环绕
	 *
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("webLog()")
	public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = joinPoint.proceed();
		logger.info("Response Args:{}", jsonUtil.objectToJson(result));
	}
}
