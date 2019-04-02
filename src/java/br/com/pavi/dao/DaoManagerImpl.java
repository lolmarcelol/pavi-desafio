/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pavi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author narim
 */
public class DaoManagerImpl implements IDaoManager{

    Connection conexão;
    private AlunoDAOImpl alunoDAO;
    public DaoManagerImpl(){
        
    }
    
    @Override
    public void iniciar() {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String url;
            url = "jdbc:mysql://localhost:3306/pavi?useTimezone=true&serverTimezone=UTC";
            conexão = DriverManager.getConnection(url, "root", "root");
            conexão.setAutoCommit(false);
            alunoDAO = new AlunoDAOImpl(conexão);
            //clienteDao.setConexão(conexão);
            
        }
        catch( Exception ex )
        {
            throw new DaoException("Ocorreu um erro ao conectar ao banco de dados:" + 
                    ex.getMessage());
        }
    }

    @Override
    public void encerrar() 
    {
        try {
            if(!conexão.isClosed())
                conexão.close();
        } catch (SQLException ex) {
            
        }
    }

    @Override
    public void confirmarTransação() {
        try{
            conexão.commit();
        } catch(SQLException ex){
            throw new DaoException("Ocorreu um erro" + ex.getMessage());
        }       
    }

    @Override
    public void abortarTransação() {
        try{
            conexão.rollback();
        } catch(SQLException ex){
            throw new DaoException("Ocorreu um erro" + ex.getMessage());
        }       
    }
    

    @Override
    public IAlunoDAO getAlunoDao() {
        return alunoDAO;
    }
    
}
