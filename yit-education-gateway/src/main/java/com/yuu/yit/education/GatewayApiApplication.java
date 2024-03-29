package com.yuu.yit.education;

import com.yuu.yit.education.gateway.common.FilterPre;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 服务网关
 * 
 * @author Yuu
 */
@EnableZuulProxy
@SpringCloudApplication
public class GatewayApiApplication {

	/**
	 * 解决跨域问题
	 *
	 * @return
	 */
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.setMaxAge(18000L);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayApiApplication.class, args);
	}

	/**
	 * 鉴权过滤器
	 *
	 * @return
	 */
	@Bean
	public FilterPre filterPre() {
		return new FilterPre();
	}

}
