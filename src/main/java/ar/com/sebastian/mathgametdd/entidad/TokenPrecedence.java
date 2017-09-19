/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.sebastian.mathgametdd.entidad;

import java.util.ArrayList;

/**
 *
 * @author sebas
 */
public interface TokenPrecedence {

    MathToken getMaxPrecedence(ArrayList<MathToken> tokens);

}
