package kr.codesquad.todolist.interceptor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.codesquad.todolist.dto.card.CreateCardRequest;
import kr.codesquad.todolist.service.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;


@Component
public class TestInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(TestInterceptor.class);
    private final ObjectMapper objectMapper;
    private final LogService logService;

    public TestInterceptor(ObjectMapper objectMapper, LogService logService) {
        this.objectMapper = objectMapper;
        this.logService = logService;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) {
            return;
        }

        final ContentCachingRequestWrapper cachingRequest = (ContentCachingRequestWrapper) request;
        final ContentCachingResponseWrapper cachingResponse = (ContentCachingResponseWrapper) response;

        if (cachingRequest.getContentType() != null && cachingRequest.getContentType().contains("application/json")) {

            if (cachingRequest.getContentAsByteArray() != null && cachingRequest.getContentAsByteArray().length != 0){
                log.info("requestBody : {}", objectMapper.readTree(cachingResponse.getContentAsByteArray()));;

            }
        }

        if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {

            if (cachingResponse.getContentAsByteArray() != null && cachingResponse.getContentAsByteArray().length != 0) {
                log.info("responseBody : {}", objectMapper.readTree(cachingResponse.getContentAsByteArray()));

            }
        }
    }

}
