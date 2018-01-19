package cn.ar.allremem.common.interceptor;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
// @ComponentScan(basePackages = { "cn.ar.allremem.web.action" })
// 必须存在 扫描的API Controller package name 也可以直接扫描class(basePackageClasses)
public class WebAppConfig // extends WebMvcConfigurerAdapter
{
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()// 选择哪些路径和API会生成document
				// .apis(RequestHandlerSelectors.basePackage("com.gm.ds.api"))
				.apis(RequestHandlerSelectors.any())// 对所有api进行监控
				.paths(PathSelectors.any())// 对所有路径进行监控
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("app services api").description("app interface HTTP service")
				.termsOfServiceUrl("http://www.allremem-service.com").license("LICENSE")
				.licenseUrl("http://www.allremem-license.com")
				.contact(new Contact("zhangxy", "http://www.allremem-contact.com", "zhangxy@allremem.com"))
				.version("1.0.0").build();
	}

	// @Override
	// public void addResourceHandlers(ResourceHandlerRegistry registry) {
	// registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	// registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	// }

}
