package org.example.loan.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ValidationError {

    private final String field;

    private final String message;
}
