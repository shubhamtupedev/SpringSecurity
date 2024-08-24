package com.example.springsecurity.config;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CaptureResponseWrapper extends HttpServletResponseWrapper {

    private StringWriter sw = new StringWriter();
    private PrintWriter writer = new PrintWriter(sw);


    /**
     * Constructs a response adaptor wrapping the given response.
     *
     * @param response The response to be wrapped
     * @throws IllegalArgumentException if the response is null
     */
    public CaptureResponseWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public PrintWriter getWriter() {
        return writer;
    }

    public String getCapturedResponse() {
        return sw.toString();
    }
}
