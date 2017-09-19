/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.modelo;

import ar.com.sebastian.mathgametdd.exception.OverflowException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author sebas
 */
public class CalculatorProxy {

    private Calculator calculator;
    private Validator validator;
    
    public CalculatorProxy(Calculator calculator) {
        this.calculator = calculator;
        this.validator = new Validator();
    }

    public CalculatorProxy(Validator validator, Calculator calculator) {
        this.calculator = calculator;
        this.validator = validator;
    }

    public double binaryOperation(Class<Calculator> aClass, String operation, double arg1, double arg2) throws OverflowException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, NoSuchMethodException {    
        Method[] methods = this.calculator.getClass().getMethods();
        Object[] params = new Object[]{arg1, arg2};
        Class[] methodParameters = new Class[]{Double.TYPE, Double.TYPE};
        double result = 0;
        
        this.validator.validateArgs(arg1,arg2);
        
        for(Method method : methods){
            if(method.getName()==operation){
                Method publicMetod = this.calculator.getClass().getDeclaredMethod(operation, methodParameters); 
                publicMetod.setAccessible(true);
                result = (double) publicMetod.invoke(calculator,params);
            }
        }
        
        this.validator.validateResult(result);
        return result;
    }
}
