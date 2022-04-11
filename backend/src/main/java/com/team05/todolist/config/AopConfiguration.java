package com.team05.todolist.config;

import com.team05.todolist.domain.dto.CardDTO;
import com.team05.todolist.domain.dto.LogDTO;
import com.team05.todolist.domain.dto.ResponseDTO;
import java.lang.reflect.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopConfiguration {

	private final Logger logger = LoggerFactory.getLogger(AopConfiguration.class);

	/*
	@Pointcut : Aspect 적용 위치 지정자
	 */
	@Pointcut("execution(* com.team05.todolist.controller.CardController.*(..))")
	private void cut() {
	}

	@AfterReturning(value = "cut()", returning = "returnObj")
	public void checkLog(JoinPoint joinPoint, Object returnObj) {
		Method method = getMethod(joinPoint);
		logger.debug("=================== Event | {} ===================", method.getName());
		if (returnObj instanceof ResponseEntity) {
			ResponseDTO responseDTO = (ResponseDTO) ((ResponseEntity<?>) returnObj).getBody();
			printLog(responseDTO);
		}
	}

	private void printLog(ResponseDTO responseDTO) {
		if (responseDTO != null) {
			CardDTO cardDTO = responseDTO.getCard();
			LogDTO logDTO = responseDTO.getLog();
			logger.debug("[card-{}] order: {}, section: {}, title: {}", cardDTO.getCardId(),
				cardDTO.getOrder(), cardDTO.getSection(), cardDTO.getTitle());
			logger.debug("[log-Time] {}", logDTO.getLogTime());
		}
	}

	// joinPoint로부터 메서드 정보를 얻음
	private Method getMethod(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		return signature.getMethod();
	}
}
