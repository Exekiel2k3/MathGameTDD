/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.modelo;

/**
 *
 * @author sebas
 */
public class MathRegex {
    
    private static final String REGEX_DECIMAL_NEGATIVE_EXPRESSION = "(-?\\.?((([-+]?(\\d)*\\.?(\\d)+([eE][-+]?(\\d)*))|(\\d+))((\\.|,| )(([-+]?(\\d)*\\.?(\\d)+([eE][-+]?(\\d)*))|(\\d+)))?))";
    //Notacion cientifica "([-+]?(\d)*\.?(\d)+([eE][-+]?(\d)*))" //
    private static final String REGEX_DECIMAL_EXPRESSION = "(-{0,1}((-?\\.?(([-+]?(\\d)*\\.?(\\d)+([eE][-+]?(\\d)*)?)((\\.|,| )([-+]?(\\d)*\\.?(\\d)+([eE][-+]?(\\d)*)))?)))+((\\s+)[\\+|\\-|\\/|\\*|\\%](\\s+)-{0,1}((-?\\.?(([-+]?(\\d)*\\.?(\\d)+([eE][-+]?(\\d)*)?)((\\.|,| )([-+]?(\\d)*\\.?(\\d)+([eE][-+]?(\\d)*)))?)))*)+)";
    private static final String REGEX_EXPRESSION = "(((-?\\.?(\\d+((\\.|,| )\\d+)?)))+((\\s+)[\\+|\\-|\\/|\\*\\%](\\s+)((-?\\.?(\\d+((\\.|,| )\\d+)?)))+)+)";
    private static final String REGEX_END_WITH_OPERATOR = "(((-?\\.?(\\d+((\\.|,| )\\d+)?))*)(\\s+)[\\+|\\-|\\/|\\*|\\%])";
    private static final String REGEX_START_WITH_OPERATOR = "((\\s*)[\\+|\\-|\\/|\\*|\\%](\\s+)((-?\\.?(\\d+((\\.|,| )\\d+)?))*))";
    private static final String REGEX_NUMBER = "(\\d+)";
    private static final String REGEX_OPERATOR = "([\\+|\\-|\\/|\\*|\\%])";    
    
    public static boolean isExpressionValid(String expression) {
        String regex = REGEX_DECIMAL_EXPRESSION;
        String regexNegative = REGEX_DECIMAL_NEGATIVE_EXPRESSION;
        boolean result = expression.matches(regex) || expression.matches(regexNegative);
        return result;
    }
    
    public static boolean isExpression(String expression) {
        String regex = REGEX_EXPRESSION;
        boolean result = expression.matches(regex);
        return result;
    }
    
    public static boolean isNumberAndOperator(String expressions) {
        
        String startsWithOperator = REGEX_START_WITH_OPERATOR;
        String endsWithOperator = REGEX_END_WITH_OPERATOR;
        return expressions.matches(startsWithOperator) || 
                expressions.matches(endsWithOperator);
    }
    
    public static boolean isSubExpression(String subExp) {
        return isNumber(subExp) || isOperator(subExp);
    }

    public static boolean isNumber(String number) {
        String numberRegex = REGEX_NUMBER;
        return number.matches(numberRegex);
    }
    
    public static boolean isOperator(String operator) {
        String operatorRegex = REGEX_OPERATOR;
        return operator.matches(operatorRegex);
    }
}