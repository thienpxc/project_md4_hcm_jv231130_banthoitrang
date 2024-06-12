package rikkei.academy.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration // đây là lớp cấu hình
@EnableWebMvc // cho phép bật cấu hình MVC
@ComponentScan(basePackages = "rikkei.academy")// phát hện component :  @Component , @Controller, @Service, @Repository
public class WebConfig implements WebMvcConfigurer, ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // khởi tạo bean
    // thymeleaf config
    // caaus hình thymeleaf
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setContentType("UTF-8");
        return viewResolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("addInterceptors");
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/admin/**","");

    }

    // cấu hình file upload
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getResolver()  {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(52428800); // 50MB
        return resolver;
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**","/css/**","/js/**","/img/**","/scss/**","/cssLogin/**","/jsLogin/**")
                .addResourceLocations("/uploads/","/access/admin/css/","/access/admin/js/","/access/admin/img/",
                        "/access/customer/css/","/access/customer/js/","/access/customer/img/","/access/customer/scss/","/access/login/cssLogin/","/access/login/jsLogin/");
    }


}
