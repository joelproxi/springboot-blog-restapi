package com.proxidev.springbootblogrestapi.dtos;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse implements Serializable {

    @JsonProperty(value = "success")
    private Boolean success;

    @JsonProperty(value = "message")
    private String message;

    @JsonIgnore
    private HttpStatus status;

    public ApiResponse() {
    }

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public ApiResponse(Boolean success, String message, HttpStatus status) {
        this.success = success;
        this.message = message;
        this.status = status;
    }

}
