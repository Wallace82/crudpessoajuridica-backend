package com.desafio.crudpj.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.desafio.crudpj.core.validator.CnpjValidator;

@Constraint(validatedBy = {CnpjValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface Cnpj {

  String message() default "CNPJ inválido";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}