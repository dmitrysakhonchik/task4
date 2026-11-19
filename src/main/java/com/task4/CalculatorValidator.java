package com.task4;

public class CalculatorValidator {

    public String validate(String firstOperandInput, String secondOperandInput, String selectedOperation) {
        if (firstOperandInput == null || firstOperandInput.isBlank()
                || secondOperandInput == null || secondOperandInput.isBlank()) {
            return "Enter both numbers.";
        }

        if (selectedOperation == null || selectedOperation.isBlank()) {
            return "Select an operation.";
        }

        try {
            Integer.parseInt(firstOperandInput);
            var secondOperand = Integer.parseInt(secondOperandInput);
            if (selectedOperation.equals("divide") && secondOperand == 0) {
                return "Cannot divide by zero.";
            }
        } catch (NumberFormatException exception) {
            return "Enter valid integers.";
        }

        return null;
    }
}
