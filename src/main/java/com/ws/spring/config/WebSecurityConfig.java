package com.ws.spring.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import com.ws.spring.security.jwt.JwtAuthEntryPoint;
import com.ws.spring.security.jwt.JwtAuthTokenFilter;
import com.ws.spring.service.UserProfileServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserProfileServiceImpl userProfileService;

	@Autowired
	private JwtAuthEntryPoint unauthorizedHandler;

	@Bean
	public JwtAuthTokenFilter authenticationJwtTokenFilter() {
		return new JwtAuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userProfileService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login/v1/userLogin","/login/**", "/login","/login/v1/userLogin/**", "/v1/userLogin", "/login/v1/userLogin/**", "/index.html")
				.permitAll().anyRequest().authenticated();
		http.headers().xssProtection().block(Boolean.FALSE).disable();
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/", "/index.html","/logout.html", "/group.html", "/course.html", "/module.html", "/topic.html",
				"/student.html", "/dashboard.html", "/forum.html", "/rec_paper.html", "/test.html", "/test1.html","/college.html",
				"/css/**", "/scss/**", "/js/**", "/ui/**", "/images/**", "/plugins/**", "/v2/api-docs",
				"/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html",
				"/webjars/**");
	}

	@Bean
	public MultipartResolver multipartResolver() {
		MultipartResolver standardServletMultipartResolver = new StandardServletMultipartResolver();
		return standardServletMultipartResolver;
		//return new CommonsMultipartResolver();
		// return new CommonsMultipartResolverMine();
	}

	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-type", "x-auth-token"));
        configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	/*
	 * public static class CommonsMultipartResolverMine extends
	 * CommonsMultipartResolver {
	 * 
	 * @Override public boolean isMultipart(HttpServletRequest request) { final
	 * String header = request.getHeader("Content-Type"); if (header == null) {
	 * return false; } return header.contains("multipart/form-data"); }
	 * 
	 * }
	 */
}
