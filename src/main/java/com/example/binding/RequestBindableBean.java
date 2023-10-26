package com.example.binding;

import com.example.validation.ValidQueryParameters;
import io.micronaut.core.annotation.Introspected;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Introspected
@ValidQueryParameters
public class RequestBindableBean {
    @Positive
    private int min;
    @PositiveOrZero
    private int max;
    @Digits(integer = 10, fraction = 0)
    private String accountNumber;

    public RequestBindableBean(@Positive int min, @PositiveOrZero int max, @Digits(integer = 10, fraction = 0) String accountNumber) {
        this.min = min;
        this.max = max;
        this.accountNumber = accountNumber;
    }

    public @Positive int getMin() {
        return this.min;
    }

    public @PositiveOrZero int getMax() {
        return this.max;
    }

    public @Digits(integer = 10, fraction = 0) String getAccountNumber() {
        return this.accountNumber;
    }
}
