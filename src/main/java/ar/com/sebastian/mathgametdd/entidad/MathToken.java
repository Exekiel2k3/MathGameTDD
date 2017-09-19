/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.entidad;

/**
 *
 * @author sebas
 */
public abstract class MathToken {

    protected String token = "";
    protected int precedence = 0;
    protected int index = -1;
    protected MathToken previousToken;
    protected MathToken nextToken;

    public MathToken(String token) {
        this.token = token;
    }

    public MathToken(int precedence) {
        this.precedence = precedence;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
    
    public String getToken() {
        return token;
    }

    public int getPrecedence() {
        return precedence;
    }
    
    public abstract double resolve() throws Exception;

    public MathToken getNextToken() {
        return nextToken;
    }

    public MathToken getPreviousToken() {
        return previousToken;
    }

    public void setNextToken(MathToken nextToken) {
        this.nextToken = nextToken;
    }

    public void setPreviousToken(MathToken previousToken) {
        this.previousToken = previousToken;
    }
}
