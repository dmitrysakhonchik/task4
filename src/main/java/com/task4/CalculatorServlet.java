package com.task4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {

    private static final String CALCULATOR_VIEW = "/WEB-INF/calculator.jsp";

    private static final String FIRST_OPERAND = "firstOperand";
    private static final String SECOND_OPERAND = "secondOperand";

    private static final String ADDITION = "add";
    private static final String SUBTRACTION = "subtract";
    private static final String MULTIPLICATION = "multiply";

    private static final String SELECTED_OPERATION = "selectedOperation";
    private static final String CALCULATION_RESULT = "calculationResult";
    private static final String ERROR_MESSAGE = "errorMessage";

    private final CalculatorValidator validator = new CalculatorValidator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(CALCULATOR_VIEW).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String first = request.getParameter(FIRST_OPERAND);
        String second = request.getParameter(SECOND_OPERAND);
        String operation = request.getParameter(SELECTED_OPERATION);

        request.setAttribute(FIRST_OPERAND, first);
        request.setAttribute(SECOND_OPERAND, second);
        request.setAttribute(SELECTED_OPERATION, operation);

        String errorMessage = validator.validate(first, second, operation);
        request.setAttribute(ERROR_MESSAGE, errorMessage);

        if (errorMessage == null) {
            int firstOperand = Integer.parseInt(first);
            int secondOperand = Integer.parseInt(second);
            int calculationResult = switch (operation) {
                case ADDITION -> firstOperand + secondOperand;
                case SUBTRACTION -> firstOperand - secondOperand;
                case MULTIPLICATION -> firstOperand * secondOperand;
                default -> firstOperand / secondOperand;
            };
            request.setAttribute(CALCULATION_RESULT, calculationResult);
        }

        doGet(request, response);
    }
}
