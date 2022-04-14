package codesquad.be.todoserver.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	private static final String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss";

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		configurer
			.addPathPrefix("/api",
				HandlerTypePredicate.forBasePackage("codesquad.be.todoserver.controller"));
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);
		LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(formatter);
		LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(
			formatter);

		JavaTimeModule module = new JavaTimeModule();
		module.addSerializer(LocalDateTime.class, localDateTimeSerializer);
		module.addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(module);
		converters.add(0, new MappingJackson2HttpMessageConverter(mapper));
	}
}
