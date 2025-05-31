package com.books.bookRental.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    public String resourceName;
    public String fieldName;
    public String fieldValue;

    public ResourceNotFoundException (String resourceName, String fieldName, String fieldValue) {
        super(resourceName+" not found with given input data "+fieldName+": "+fieldValue);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


}
