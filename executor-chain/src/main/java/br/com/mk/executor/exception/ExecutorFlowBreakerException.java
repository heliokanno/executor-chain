package br.com.mk.executor.exception;

public class ExecutorFlowBreakerException extends RuntimeException {

    public ExecutorFlowBreakerException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecutorFlowBreakerException(String message) {
        super(message);
    }

}