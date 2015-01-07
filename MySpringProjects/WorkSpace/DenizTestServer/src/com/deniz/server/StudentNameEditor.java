package com.deniz.server;

import org.springframework.beans.propertyeditors.PropertiesEditor;

public class StudentNameEditor extends PropertiesEditor {

	@Override
	public void setAsText(String studentName) throws IllegalArgumentException {

		if (studentName.contains("Mr.") || studentName.contains("Ms.")) {
			setValue(studentName);
		} else {
			studentName = "Ms. " + studentName;

			setValue(studentName);
		}

	}

}
