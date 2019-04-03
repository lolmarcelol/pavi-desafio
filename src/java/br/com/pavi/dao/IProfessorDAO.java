/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pavi.dao;

import br.com.pavi.models.Professor;

/**
 *
 * @author narim
 */
public interface IProfessorDAO {
    
    public boolean login(Professor professor);
    
}
