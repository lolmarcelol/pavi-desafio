/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pavi.dao;

import br.com.pavi.models.Professor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author narim
 */
public class ProfessorDaoImpl implements IProfessorDAO{

    private Connection conexão;
    
    public ProfessorDaoImpl(Connection conexão){
        this.conexão = conexão;
    }
    
    @Override
    public boolean login(Professor professor) {
        String sql = "SELECT * FROM professor where login=? AND senha=?";
        Professor retorno = null;
        
        PreparedStatement ps;
        try {
            ps = conexão.prepareStatement(sql);
            ps.setString(1, professor.getLogin());
            ps.setString(2, professor.getSenha());
            ResultSet res = ps.executeQuery();
            if(res.next())
            {
                retorno = new Professor();
                retorno.setLogin(res.getString("login"));
                retorno.setSenha(res.getString("senha"));
                retorno.setNome(res.getString("nome"));
            }
               
        } catch (Exception ex) {
            throw new DaoException("Erro ao trazer ao logar no banco de dados");
            
        }
        if(retorno == null){
            return false;
        }else{
            return true;
        }
    }    
    
    
}
