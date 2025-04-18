package com.ironhack.lab_java_springboot_rest_api_es.exception;

public class ProductException extends RuntimeException {
  public static class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String name) {
      super("Product " +name + " not found");
    }
  }
  public static class InvalidPriceRangeException extends RuntimeException {
    public InvalidPriceRangeException() {
      super("Invalid price range. Make sure min ≤ max and both are positive.");
    }
  }

  public static class MissingApiKeyException extends RuntimeException {
    public MissingApiKeyException() {
      super("Invalid or missing API key");
    }
  }
}
