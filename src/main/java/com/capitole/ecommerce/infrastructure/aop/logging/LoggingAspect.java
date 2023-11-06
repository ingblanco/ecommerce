package com.capitole.ecommerce.infrastructure.aop.logging;

import java.time.LocalDateTime;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.capitole.ecommerce.infrastructure.adapter.entity.Price;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

	private static final String LOG_PARAMETERS = " DB Persistence is running with the following parameters: ####################################";
	private static final String RESPONSE_METHOD = "######################## DB PERSISTENCE LAYER: Method response:";

	@Before("execution(* com.capitole.ecommerce.infrastructure.adapter.repository.PriceRepository.findByBrandProductAndApplicationDate(..)) && args(brandId,productId,applicationDate)")
	public void logMethodParamsForfindByBrandProductAndApplicationDate(JoinPoint joinPoint, Long brandId,
			Long productId, LocalDateTime applicationDate) {

		logClassAndMethodNames(joinPoint.getClass().getCanonicalName(), joinPoint.getSignature().getName());
		log.info("brandId: " + brandId);
		log.info("productId: " + productId);
		log.info("applicationDate: " + applicationDate.toString());

	}

	@AfterReturning(pointcut = "execution(* com.capitole.ecommerce.infrastructure.adapter.repository.PriceRepository.findByBrandProductAndApplicationDate(..))", returning = "prices")
	public void logResultForfindByBrandProductAndApplicationDate(JoinPoint joinPoint, List<Price> prices)
			throws Exception {
		logResultMethod(prices, joinPoint.getSignature().getName());
	}

	private void logResultMethod(List<Price> result, String methodName) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		log.info(RESPONSE_METHOD + methodName);
		if (result != null && !result.isEmpty())
			result.stream().forEach(x -> {
				log.info("##################");
				log.info(x.getBrand().toString());
				log.info(x.getProductId().toString());
				log.info(x.getStartDate().toString());
				log.info(x.getEndDate().toString());
				log.info(x.getPrice().toString());
				log.info(x.getPriceList().toString());

			});
	}

	private void logClassAndMethodNames(String clazz, String method) {
		log.info("################ Class " + clazz);
		log.info("################ Method " + method + LOG_PARAMETERS);
	}
}