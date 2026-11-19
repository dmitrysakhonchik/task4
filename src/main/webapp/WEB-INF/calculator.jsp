<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Web Calculator</title>
</head>
<body>
<main>
    <h1>Web Calculator</h1>
    <c:url var="calculatorUrl" value="/calculator"/>
    <form action="${calculatorUrl}" method="post">
        <p>
            <label for="firstOperand">First number</label>
            <input id="firstOperand" name="firstOperand" type="text" inputmode="numeric"
                   value="<c:out value='${firstOperand}'/>">
        </p>
        <p>
            <label for="selectedOperation">Operation</label>
            <select id="selectedOperation" name="selectedOperation">
                <option value="">Select an operation</option>
                <option value="add" <c:if test="${selectedOperation eq 'add'}">selected</c:if>>Addition (+)</option>
                <option value="subtract" <c:if test="${selectedOperation eq 'subtract'}">selected</c:if>>Subtraction (−)</option>
                <option value="multiply" <c:if test="${selectedOperation eq 'multiply'}">selected</c:if>>Multiplication (×)</option>
                <option value="divide" <c:if test="${selectedOperation eq 'divide'}">selected</c:if>>Division (÷)</option>
            </select>
        </p>
        <p>
            <label for="secondOperand">Second number</label>
            <input id="secondOperand" name="secondOperand" type="text" inputmode="numeric"
                   value="<c:out value='${secondOperand}'/>">
        </p>
        <button type="submit">Calculate</button>
    </form>

    <c:choose>
        <c:when test="${not empty errorMessage}">
            <p role="alert"><c:out value="${errorMessage}"/></p>
        </c:when>
        <c:when test="${not empty calculationResult}">
            <p role="status">
                <c:out value="${firstOperand}"/>
                <c:choose>
                    <c:when test="${selectedOperation eq 'add'}">+</c:when>
                    <c:when test="${selectedOperation eq 'subtract'}">−</c:when>
                    <c:when test="${selectedOperation eq 'multiply'}">×</c:when>
                    <c:otherwise>÷</c:otherwise>
                </c:choose>
                <c:out value="${secondOperand}"/> = <c:out value="${calculationResult}"/>
            </p>
        </c:when>
        <c:otherwise>
            <p>Enter two numbers and select an operation.</p>
        </c:otherwise>
    </c:choose>
</main>
</body>
</html>
