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
public enum Operation {

    ADD("add","+"),
    SUBSTRACT("substract","-"),
    MULTIPLY("multiply","*"),
    DIVISION("division","/");
    
    private String operationName;
    private String operationToken;

    private Operation(String operationName, String operationToken) {
        this.operationName = operationName;
        this.operationToken = operationToken;
    }

    public String getOperationName() {
        return operationName;
    }

    public String getOperationToken() {
        return operationToken;
    }
}
