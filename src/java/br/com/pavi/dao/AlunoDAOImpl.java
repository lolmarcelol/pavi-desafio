/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pavi.dao;

import br.com.pavi.models.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author narim
 */
public class AlunoDAOImpl implements IAlunoDAO{

    
    private Connection conexão;
    
    public AlunoDAOImpl(Connection conexão){
        this.conexão = conexão;
    }
    
    @Override
    public Aluno inserir(Aluno aluno) {
        String sql;
        sql = "INSERT INTO aluno("
                + "login,"
                + "senha,"
                + "nome)"
                + " VALUES (?,?,?)";
        PreparedStatement ps;
        try{
            ps = conexão.prepareStatement(sql);
            ps.setString(1, aluno.getLogin());
            ps.setString(2, aluno.getSenha());
            ps.setString(3, aluno.getNome());
            
            ps.executeUpdate();
            return aluno;
        } catch(Exception ex){
            throw new DaoException("Erro ao inserir aluno no banco de dados");
            
        }
    }

    @Override
    public List<Aluno> listar() {
        String sql = "SELECT * FROM aluno";
        List<Aluno> retorno = new ArrayList<Aluno>();
        PreparedStatement ps;
        try {
            ps = conexão.prepareStatement(sql);
            ResultSet res = ps.executeQuery();
            while(res.next())
            {
                Aluno item = new Aluno();
                item.setId(res.getInt("id"));
                item.setLogin(res.getString("login"));
                item.setSenha(res.getString("senha"));
                item.setNome(res.getString("nome"));                
                retorno.add(item);
            }
        } catch (Exception ex) {
            throw new DaoException("Erro ao trazer alunos do banco de dados");
            
        }
        return retorno;
    }

    @Override
    public boolean deletar(int id) {
        String sql = "DELETE FROM aluno where id=?";
        Boolean retorno = false;
        try {
            PreparedStatement ps = conexão.prepareStatement(sql);
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                retorno = true;
            }
        } catch (Exception ex) {
            throw new DaoException("Erro ao deletar aluno do banco de dados");
        }
        
        return retorno;
    }

    @Override
    public Aluno findById(int id) {
        String sql = "SELECT * FROM aluno where id=?";
        Aluno retorno = null;
        try {
            PreparedStatement pst = conexão.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet res = pst.executeQuery();
            if(res.next())
            {
                retorno = new Aluno();
                retorno.setLogin(res.getString("login"));
                retorno.setSenha(res.getString("senha"));
                retorno.setNome(res.getString("nome"));
                retorno.setId(res.getInt("id"));
            }
        } catch (Exception ex) {
            throw new DaoException("Erro ao alterar aluno do banco de dados");
        }
        return retorno;
    }

    @Override
    public boolean alterar(Aluno aluno) {
        String sql = "UPDATE aluno set login=?,senha=?,nome=? where id=?";
        Boolean retorno = false;
        try {
            PreparedStatement pst = conexão.prepareStatement(sql);
            pst.setString(1, aluno.getLogin());
            pst.setString(2, aluno.getSenha());
            pst.setString(3, aluno.getNome());
            pst.setInt(4, aluno.getId());
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }    
        } catch (Exception ex) {
            throw new DaoException("Erro ao alterar aluno do banco de dados");
        }
        
        return retorno;
    }
    
}
