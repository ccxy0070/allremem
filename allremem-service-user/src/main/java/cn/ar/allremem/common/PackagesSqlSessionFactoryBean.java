package cn.ar.allremem.common;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.apache.commons.lang.StringUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title PackagesSqlSessionFactoryBean.java
 * @ClassName: PackagesSqlSessionFactoryBean
 * @Package: com.cic.aop.util
 * @version V1.0
 * @since JDK 1.7
 * @author John
 * @date: 2017年5月4日 下午2:44:15 <br/>
 */
public class PackagesSqlSessionFactoryBean extends SqlSessionFactoryBean {

	// 注入配置实例:<property name="typeAliasesPackage" value="com.**.bean" />
	static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

	public void setTypeAliasesPackage(String typeAliasesPackage) {
		ResourcePatternResolver resolver = (ResourcePatternResolver) new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resolver);
		// 将加载多个绝对匹配的所有Resource
		typeAliasesPackage = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(typeAliasesPackage) + "/" + DEFAULT_RESOURCE_PATTERN;
		// 将首先通过ClassLoader.getResource("META-INF")加载非模式路径部分
		try {
			List<String> result = new ArrayList<String>();
			Resource[] resources = resolver.getResources(typeAliasesPackage);
			if (resources != null && resources.length > 0) {
				MetadataReader metadataReader = null;
				for (Resource resource : resources) {// 进行遍历模式匹配
					if (resource.isReadable()) {
						metadataReader = metadataReaderFactory.getMetadataReader(resource);
						try {
							result.add(Class.forName(metadataReader.getClassMetadata().getClassName()).getPackage()
									.getName());
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
			}
			if (result.size() > 0) {
				super.setTypeAliasesPackage(StringUtils.join(result.toArray(), ","));
			} else {
				// logger.info("参数typeAliasesPackage:" + typeAliasesPackage + "，未找到任何包");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
