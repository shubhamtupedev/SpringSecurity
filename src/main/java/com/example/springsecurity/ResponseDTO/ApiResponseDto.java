package com.example.springsecurity.ResponseDTO;

public class ApiResponseDto<T> {

    private String status;

    private T response;


    public ApiResponseDto() {
    }

    public ApiResponseDto(String status, T response) {
        this.status = status;
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "ApiResponseDto{" +
                "status='" + status + '\'' +
                ", response=" + response +
                '}';
    }
}
