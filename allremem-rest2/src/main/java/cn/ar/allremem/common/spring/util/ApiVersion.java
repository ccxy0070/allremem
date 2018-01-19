package cn.ar.allremem.common.spring.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.Mapping;

/**
 * 
 * 接口版本标识注解[方法级设置版本]
 * 通过 /v1/hello/, /v2/hello/, /v5/hello来分别调用版本1，2，5的管理
 * 如果通过 /v4/hello/来访问接口，则要自动适配到 /v2/hello/，因为 v2是比v4低的版本中最新的版本
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface ApiVersion {
    int value();
}
