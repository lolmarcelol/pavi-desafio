/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pavi.dao;

/**
 *
 * @author narim
 */
public class DaoException extends RuntimeException {

    public DaoException(String mensagem) 
    {
        super(mensagem);
    }
    
}
