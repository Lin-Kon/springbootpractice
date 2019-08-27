package com.example.springbootpractice.exception;

public class ResourceDoesNotExistException extends Exception {
    public ResourceDoesNotExistException(String resource) {super(resource + " does not exist");}
}
