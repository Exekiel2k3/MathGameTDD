/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.modelo;

import ar.com.sebastian.mathgametdd.entidad.MathToken;
import ar.com.sebastian.mathgametdd.exception.InvalidOperationException;
import java.util.ArrayList;

/**
 *
 * @author sebas
 */
public class MathLexer {
    
    private ExpressionFixer fixer;

    private static final String WHITE_SPACE = " ";
    private static final String STRING_EMPTY = "";
    private static final char CLOSE_PARENTHESIS_CHAR = ')';
    private static final char OPEN_PARENTHESIS_CHAR = '(';
    private static final String CLOSE_PARENTHESIS = ")";
    private static final String OPEN_PARENTHESIS = "(";    
    
    public MathLexer(ExpressionFixer fixer) {
        this.fixer = fixer;
    }
    
    public ArrayList<MathToken> getTokens(String expression) throws InvalidOperationException {
        String[] items = expression.split(WHITE_SPACE);
        return createTokenFromString(items);
    }
    
    private ArrayList<MathToken> createTokenFromString(String[] items) throws InvalidOperationException {
        ArrayList<MathToken> tokens = new ArrayList<>();
        for (String item : items) {
            if(!item.equals(STRING_EMPTY)){
                if(MathRegex.isOperator(item)){    
                    tokens.add(OperatorFactory.create(item));
                } else {
                    tokens.add(new MathNumber(item));
                }
            }
        }
        return tokens;
    }
    
    public ArrayList<String> getExpressions(String expression) throws InvalidOperationException {
        
        ArrayList<String> expressions = new ArrayList<>();
        expressions = _getExpression(expression);
        fixer.fixExpressions(expressions);
        return expressions;
    }

    private ArrayList<String> _getExpression(String subExpression) throws InvalidOperationException{
        ArrayList<String> expressions = new ArrayList<>();
        int countParenthesis = 0;
        
        if(!subExpression.contains(OPEN_PARENTHESIS) && !subExpression.contains(CLOSE_PARENTHESIS)){
            expressions.add(subExpression);
            return expressions;
        }
        
        String expressionAux;
        int length= subExpression.length();
        for(int idx = 0; idx < length; idx++  ){
            char c = subExpression.charAt(idx); 
            switch(c){
                case OPEN_PARENTHESIS_CHAR:
                    countParenthesis++;
                    break;
                case CLOSE_PARENTHESIS_CHAR:
                    countParenthesis--;
                    break;
                default:
                    int subIdx = 0;
                    expressionAux = subExpression.substring(idx, length); 
                    int idxOpen = expressionAux.indexOf(OPEN_PARENTHESIS);
                    int idxClose = expressionAux.indexOf(CLOSE_PARENTHESIS);
                    
                    if(countParenthesis==0){
                        if(idxOpen!=-1)
                            subIdx = idxOpen + idx;
                        else
                            if(idxClose!=-1)
                                subIdx = idxClose + idx;
                             else
                                subIdx = length;
                    }else{
                        if(idxOpen!=-1 && idxClose!=-1){
                            if(idxClose < idxOpen){
                                subIdx = idxClose + idx;
                            }else{
                                subIdx = idxOpen + idx;
                            }
                        }else{
                            if(idxClose==-1 && idxOpen==-1)
                                throw new InvalidOperationException();
                            
                            if(idxOpen!=-1){
                                if(idxClose > idxOpen){
                                    subIdx = idxClose + idx;
                                }else{
                                    subIdx = idxOpen + idx;
                                }
                            }else{
                                subIdx = idxClose + idx;
                            }
                        }
                    }
                    
                    expressionAux = subExpression.substring(idx,subIdx);
                    idx = subIdx-1;
                    expressions.add(expressionAux);
                    break;
            }
        }
        
        if(countParenthesis!=0)
            throw new InvalidOperationException();
        
        return expressions;
    }
}