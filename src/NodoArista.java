/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aaron
 */
class NodoArista {
    NodoGrafo direccion;
    NodoArista ant, sig;
    
    public NodoArista(NodoGrafo d){
        direccion = d;
        ant = null;
        sig = null;
    }
}
