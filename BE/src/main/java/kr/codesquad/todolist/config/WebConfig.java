package kr.codesquad.todolist.config;

import kr.codesquad.todolist.interceptor.LogAddInterceptor;
import kr.codesquad.todolist.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LogAddInterceptor logAddInterceptor;
    private final TestInterceptor testInterceptor;

    public WebConfig(LogAddInterceptor logAddInterceptor, TestInterceptor testInterceptor) {
        this.logAddInterceptor = logAddInterceptor;
        this.testInterceptor = testInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logAddInterceptor).addPathPatterns("/**");
        registry.addInterceptor(testInterceptor).addPathPatterns("/**");
    }


}
