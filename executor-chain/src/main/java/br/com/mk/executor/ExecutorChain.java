package br.com.mk.executor;

import br.com.mk.executor.exception.ExecutorFlowBreakerException;
import lombok.Builder;
import lombok.Singular;
import lombok.extern.java.Log;

import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.logging.Level;

@Log
@Builder
public class ExecutorChain<T> {

    @Singular
    List<Executor<T>> chains;

    UnaryOperator<T> errorHandler;

    UnaryOperator<T> beforeEach;

    UnaryOperator<T> afterEach;

    UnaryOperator<T> beforeAll;

    UnaryOperator<T> afterAll;

    public T execute(final T input) {
        T output = input;
        try {
            output = executeHandler(beforeAll).apply(output);
            for (var operation : chains) {
                output = executeHandler(beforeEach)
                        .andThen(executeHandler(operation::execute))
                        .andThen(executeHandler(afterEach))
                        .apply(output);
            }
        } catch (ExecutorFlowBreakerException ex) {
            throw ex;
        } catch (Exception ex) {
            output = executeHandler(errorHandler).apply(output);
        }
        return executeHandler(afterAll).apply(output);
    }

    private UnaryOperator<T> executeHandler(UnaryOperator<T> execute) {
        return (T input) -> {
            try {
                return Objects.isNull(execute) ? input : execute.apply(input);
            } catch (Exception ex) {
                log.log(Level.SEVERE, ex.getMessage(), ex);
                throw ex;
            }
        };
    }

}

