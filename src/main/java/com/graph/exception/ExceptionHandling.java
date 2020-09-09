package com.graph.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;
import org.zalando.problem.Problem;
import org.zalando.problem.ProblemBuilder;
import org.zalando.problem.spring.web.advice.ProblemHandling;
import org.zalando.problem.violations.ConstraintViolationProblem;
import org.zalando.problem.violations.Violation;

import java.net.URI;
import java.util.List;

@ControllerAdvice
public class ExceptionHandling implements ProblemHandling {
    private static final String VALIDATION_TYPE = "https://zalando.github.io/problem/constraint-violation";

    private static final String ERROR_KEY = "error";
    private static final String DATA_KEY = "data";

    @ExceptionHandler(value = HandledException.class)
    public ResponseEntity handleException(Throwable e, NativeWebRequest request) {
        HandledException ex = (HandledException) e;
        Problem problem = createBaseProblemBuilder()
                .with(ERROR_KEY, ex.getError())
                .with(DATA_KEY, e.getMessage())
                .withStatus(ex.getError().getStatus())
                .build();
        return create(e, problem, request);
    }

    @Override
    public ResponseEntity<Problem> create(@NonNull final Throwable throwable, Problem problem,
                                          @NonNull final NativeWebRequest request, @NonNull final HttpHeaders headers) {
        if (problem.getType().equals(URI.create(VALIDATION_TYPE))) {
            problem = getValidationProblem(problem);
        }

        return ProblemHandling.super.create(throwable, problem, request, headers);
    }

    private ProblemBuilder createBaseProblemBuilder() {
        return Problem.builder();
    }

    private Problem getValidationProblem(Problem problem) {
        ConstraintViolationProblem constraintViolationProblem = (ConstraintViolationProblem) problem;
        List<Violation> violations = constraintViolationProblem.getViolations();
        return createBaseProblemBuilder()
                .with(ERROR_KEY, Error.VALIDATION_ERROR)
                .with(DATA_KEY, violations)
                .build();
    }
}
