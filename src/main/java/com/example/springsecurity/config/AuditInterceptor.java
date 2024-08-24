package com.example.springsecurity.config;

import com.example.springsecurity.Utility.CompressionUtils;
import com.example.springsecurity.services.AuditService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class AuditInterceptor implements HandlerInterceptor {

    @Autowired
    private AuditService auditService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(request instanceof ContentCachingRequestWrapper)) {
            request = new ContentCachingRequestWrapper(request);
        }
        if (!(response instanceof ContentCachingResponseWrapper)) {
            response = new ContentCachingResponseWrapper(response);
        }

        request.setAttribute("wrappedRequest", request);
        request.setAttribute("wrappedResponse", response);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws IOException {
        ContentCachingRequestWrapper wrappedRequest = (ContentCachingRequestWrapper) request.getAttribute("wrappedRequest");
        ContentCachingResponseWrapper wrappedResponse = (ContentCachingResponseWrapper) request.getAttribute("wrappedResponse");

        if (wrappedRequest != null && wrappedResponse != null) {

            // Get request body
            String requestPayload = new String(wrappedRequest.getContentAsByteArray(), request.getCharacterEncoding());
            // Get response body
            String responsePayload = new String(wrappedResponse.getContentAsByteArray(), response.getCharacterEncoding());

            // Determine if there was an error
            String error = (ex != null) ? ex.getMessage() : null;

            // Save the log
            auditService.saveLog(request.getRequestURI(), CompressionUtils.compress(requestPayload), CompressionUtils.compress(requestPayload), error);

            // Copy the response body back to the response to send it to the client
            wrappedResponse.copyBodyToResponse();
        }
    }

}
