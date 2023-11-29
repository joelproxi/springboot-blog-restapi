package com.proxidev.springbootblogrestapi.exceptions;

import com.proxidev.springbootblogrestapi.dtos.ApiResponse;

public class ResourceNotFoundException extends RuntimeException {

    private ApiResponse apiResponse;

    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super();
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ApiResponse getApiResponse() {
        return apiResponse;
    }

    public void setApiResponse(ApiResponse apiResponse) {
        String message = String.format("%s not with %s: < %s > not found.", resourceName, fieldName, fieldValue);
        this.apiResponse = new ApiResponse(Boolean.FALSE, message);
    }

}
