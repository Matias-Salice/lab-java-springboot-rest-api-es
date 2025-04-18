package com.ironhack.lab_java_springboot_rest_api_es.exception;

public class CustomerException extends RuntimeException {
    public static class EmailNotFoundException extends RuntimeException {
        public EmailNotFoundException(String email) {
            super("The email: " +email + " not found");
        }
    }

    public static class MissingApiKeyException extends RuntimeException {
        public MissingApiKeyException() {
            super("Invalid or missing API key");
        }
    }
}
