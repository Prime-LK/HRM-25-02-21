package com.prime.hrm.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.prime")
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Bean(name = "beanNameViewResolver")
    public ViewResolver beanNameViewResolver() {
        BeanNameViewResolver resolver = new BeanNameViewResolver();
        return resolver;
    }

	@Bean(name = "jspViewResolver")
    public ViewResolver jspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/view/hrm/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
	
	/*@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}*/
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	     registry.addResourceHandler("/css/**").addResourceLocations("/css/**");
	        registry.addResourceHandler("/images/**").addResourceLocations("/images/**");
	        registry.addResourceHandler("/js/**").addResourceLocations("/js/**");
	  
	}

	@Bean
	public MultipartResolver multipartResolver() {
			
		   CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		   multipartResolver.setMaxUploadSize(52428800); // 50MB
		   
		   return multipartResolver;
		
	}
	
	   @Bean
	   public MessageSource messageSource() {
	      ResourceBundleMessageSource source = new ResourceBundleMessageSource();
	      source.setBasename("messages");
	      return source;
	   }

}
