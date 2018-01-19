package cn.ar.allremem.common.spring.util;

import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
//通过JavaConfig的方式注入,让SpringMVC加载我们定义的CustomRequestMappingHandlerMapping以覆盖原先的RequestMappingHandlerMapping
//@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
		handlerMapping.setOrder(0);
		handlerMapping.setInterceptors(getInterceptors());
		return handlerMapping;
	}
}
