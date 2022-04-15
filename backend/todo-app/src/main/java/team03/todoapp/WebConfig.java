package team03.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import team03.todoapp.interceptor.HistoryMakerInterCeptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private HistoryMakerInterCeptor historyMakerInterCeptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(historyMakerInterCeptor)
            .order(1)
            .addPathPatterns("/card")
            //.addPathPatterns("/card/{cardId}");  // 서비스단으로 옮김
            .addPathPatterns("/card/move/{cardId}");
    }

}
