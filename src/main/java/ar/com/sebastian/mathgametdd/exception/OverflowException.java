/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.exception;

/**
 *
 * @author sebas
 */
public class OverflowException extends Exception {

    /**
     * Creates a new instance of <code>OverflowException</code> without detail
     * message.
     */
    public OverflowException() {
    }

    /**
     * Constructs an instance of <code>OverflowException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public OverflowException(String msg) {
        super(msg);
    }
}
