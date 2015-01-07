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
		validatedBy = HobbyValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidHobby {

	String listOfValidHobbies() default "Music|Football|Box|Swimming";

	String message() default "please provide a valid Hobby; " + "accepted hobbies are - Music, Football, Box, Swiming (chose anyone)";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
