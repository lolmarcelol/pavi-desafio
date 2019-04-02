/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pavi.controllers;

import br.com.pavi.dao.DaoManagerImpl;
import br.com.pavi.dao.IAlunoDAO;
import br.com.pavi.dao.IDaoManager;
import br.com.pavi.models.Aluno;
import java.util.List;

/**
 *
 * @author narim
 */
public class AlunoManagerImpl implements IAlunoManager{

    @Override
    public Aluno cadastrar(Aluno aluno) {
       IDaoManager manager;
       manager = new DaoManagerImpl();
       
       try{
           manager.iniciar();
           IAlunoDAO dao = manager.getAlunoDao();
           Aluno a ;
           a = dao.inserir(aluno);
           manager.confirmarTransação();
           manager.encerrar();
           return a;
       }catch(Exception ex){
           manager.abortarTransação();
           throw ex;
       }               
    }

    @Override
    public List<Aluno> listar() {
        IDaoManager manager;
       manager = new DaoManagerImpl();
       
       try{
           manager.iniciar();
           IAlunoDAO dao = manager.getAlunoDao();
           List <Aluno> l ;
           l = dao.listar();
           manager.confirmarTransação();
           manager.encerrar();
           return l;
       }catch(Exception ex){
           manager.abortarTransação();
           throw ex;
       }               
    }

    @Override
    public boolean deletar(int id) {
        IDaoManager manager;
       manager = new DaoManagerImpl();
       
       try{
           manager.iniciar();
           IAlunoDAO dao = manager.getAlunoDao();
           boolean retorno ;
           retorno = dao.deletar(id);
           manager.confirmarTransação();
           manager.encerrar();
           return retorno;
       }catch(Exception ex){
           manager.abortarTransação();
           throw ex;
       }               
    }

    @Override
    public Aluno findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alterar(Aluno aluno) {
        IDaoManager manager;
        manager = new DaoManagerImpl();
       
       try{
           manager.iniciar();
           IAlunoDAO dao = manager.getAlunoDao();
           boolean retorno ;
           retorno = dao.alterar(aluno);
           manager.confirmarTransação();
           manager.encerrar();
           return retorno;
       }catch(Exception ex){
           manager.abortarTransação();
           throw ex;
       }              
    }
    
    
    
}
