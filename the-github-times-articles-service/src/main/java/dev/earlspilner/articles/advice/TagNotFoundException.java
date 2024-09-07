package dev.earlspilner.articles.advice;

public class TagNotFoundException extends RuntimeException {
  public TagNotFoundException(String message) {
    super(message);
  }
}
