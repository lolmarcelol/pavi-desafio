/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pavi.controllers;

import br.com.pavi.models.Aluno;
import java.util.List;

/**
 *
 * @author narim
 */
public interface IAlunoManager {
    public Aluno cadastrar(Aluno aluno);
    public List<Aluno> listar();
    public boolean deletar(int id);
    public Aluno findById(int id);
    public boolean alterar(Aluno aluno);
    
}
