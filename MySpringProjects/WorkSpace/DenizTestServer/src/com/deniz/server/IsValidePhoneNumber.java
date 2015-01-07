package com.deniz.server;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(
		validatedBy = PhoneNumberValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidePhoneNumber {

	String message() default "please provide a valid phone number; " + "it should contain 10 digits";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
