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
    
    public static boolean isExpressionValid(String expression) {
//        String regex = "(-{0,1}\\d+((\\s+)[\\+|\\-|\\/|\\*](\\s+)-{0,1}\\d*)+)";
        String regex = "(-{0,1}((-?\\.?(\\d+((\\.|,| )\\d+)?)))+((\\s+)[\\+|\\-|\\/|\\*](\\s+)-{0,1}((-?\\.?(\\d+((\\.|,| )\\d+)?)))*)+)";
//        String regexNegative = "(\\-\\d+)";
        String regexNegative = "(-?\\.?(\\d+((\\.|,| )\\d+)?))";
        boolean result = expression.matches(regex) || expression.matches(regexNegative);
        return result;
    }
    
    public static boolean isExpression(String expression) {
//        String regex = "(\\d+((\\s+)[\\+|\\-|\\/|\\*](\\s+)\\d+)+)";
        String regex = "(((-?\\.?(\\d+((\\.|,| )\\d+)?)))+((\\s+)[\\+|\\-|\\/|\\*](\\s+)((-?\\.?(\\d+((\\.|,| )\\d+)?)))+)+)";
        boolean result = expression.matches(regex);
        return result;
    }
    
    public static boolean isNumberAndOperator(String expressions) {
        
//        String startsWithOperator = "((\\s*)[\\+|\\-|\\/|\\*](\\s+)(\\d*))";
        String startsWithOperator = "((\\s*)[\\+|\\-|\\/|\\*](\\s+)((-?\\.?(\\d+((\\.|,| )\\d+)?))*))";
//        String endsWithOperator = "((\\d*)(\\s+)[\\+|\\-|\\/|\\*])";
        String endsWithOperator = "(((-?\\.?(\\d+((\\.|,| )\\d+)?))*)(\\s+)[\\+|\\-|\\/|\\*])";
                
        return expressions.matches(startsWithOperator) || 
                expressions.matches(endsWithOperator);
    }
    
    public static boolean isSubExpression(String subExp) {
        String operatorRegex = "([\\+|\\-|\\/|\\*])";
        String numberRegex = "(\\d+)";
        boolean result1 = subExp.matches(numberRegex);
        boolean result2 = subExp.matches(operatorRegex);
        return result1 || result2;
    }

    public static boolean isNumber(String number) {
        String numberRegex = "(\\d+)";
        return number.matches(numberRegex);
    }

    public static boolean isOperator(String operator) {
        String operatorRegex = "([\\+|\\-|\\/|\\*])";
        return operator.matches(operatorRegex);
    }
}
