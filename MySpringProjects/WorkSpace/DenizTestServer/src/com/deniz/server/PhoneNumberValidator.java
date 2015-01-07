package com.deniz.server;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<IsValidePhoneNumber, Long> {

	@Override
	public boolean isValid(	Long phonenumber,
							ConstraintValidatorContext arg1) {

		if (phonenumber == null) {
			return false;
		}

		if (Long.toString(phonenumber).trim().matches("[0-9]{10}")) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void initialize(IsValidePhoneNumber arg0) {
		// TODO Auto-generated method stub

	}

}
