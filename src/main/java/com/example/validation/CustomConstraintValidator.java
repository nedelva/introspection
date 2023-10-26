package com.example.validation;

import com.example.binding.RequestBindableBean;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomConstraintValidator implements ConstraintValidator<ValidQueryParameters, RequestBindableBean> {
    @Override
    public boolean isValid(RequestBindableBean requestBindableBean, ConstraintValidatorContext constraintValidatorContext) {
        if (requestBindableBean != null) {
            return !(requestBindableBean.getAccountNumber() == null);
        }
        return true;
    }
}
