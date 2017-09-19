/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.modelo;

import java.util.ArrayList;

/**
 *
 * @author sebas
 */
public class ExpressionFixer {
    
    public void fixExpressions(ArrayList<String> expressions) {
        
        boolean listHasChanges = true;
        while (listHasChanges) {
            listHasChanges = false;
            for(int i = 0; i < expressions.size(); i++){
                
                trimExpression(expressions, i);
                if(MathRegex.isNumberAndOperator(expressions.get(i))){
                    splitByOperator(expressions, i, expressions.get(i));
                    listHasChanges = true;
                    break;
                }
                isEmptyExpressionThenRemove(expressions, i);
            }
        }
    }

    private void splitByOperator(ArrayList<String> expressions, int position, String exp) {
        String[] nextExps = exp.split("(\\t|\\s)");
        int j = position;
        expressions.remove(j);
        for (String subExp : nextExps) {
            expressions.add(j, subExp.trim());
            j++;
        }
    }

    public void isEmptyExpressionThenRemove(ArrayList<String> expressions, int index) {
        if(expressions.get(index).isEmpty()){
            expressions.remove(index);
        }
    }

    private void trimExpression(ArrayList<String> expressions, int i) {
        expressions.set(i, expressions.get(i).trim());
    }
}
