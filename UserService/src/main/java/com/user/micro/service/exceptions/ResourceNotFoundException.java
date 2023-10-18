package com.user.micro.service.exceptions;

public class ResourceNotFoundException extends  RuntimeException{


    //extra property u want to manage
    public ResourceNotFoundException(){
        super("Resource not found on server !!");
    }

    public ResourceNotFoundException(String message){
        super(message);
    }
}
