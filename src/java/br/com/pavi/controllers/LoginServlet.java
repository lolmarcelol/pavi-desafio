/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pavi.controllers;

import br.com.pavi.dao.DaoManagerImpl;
import br.com.pavi.dao.IDaoManager;
import br.com.pavi.dao.IProfessorDAO;
import br.com.pavi.models.Professor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author narim
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/Login.html");
        rd.forward(request, response);
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out=response.getWriter();  
        Professor professor = new Professor();
        Enumeration e = request.getParameterNames();
        professor.setLogin(request.getParameter("login")); // parametro que esta no campo name
        professor.setSenha(request.getParameter("senha")); 
         
        IDaoManager manager;
        manager = new DaoManagerImpl();
       
        try{
            manager.iniciar();
            IProfessorDAO dao = manager.getProfessorDao();
            boolean retorno ;
            retorno = dao.login(professor);
            manager.confirmarTransação();
            manager.encerrar();
            if(retorno){
                Cookie cookie = new Cookie("website", "login");
                cookie.setMaxAge(60 * 60);
                response.addCookie(cookie);
                response.sendRedirect(request.getContextPath() + "/");
            }else{
           out.print("Senha ou usuário incorreto, tente novamente");  
            response.sendRedirect(request.getContextPath() + "/Login");
            }  
        }catch(Exception ex){
            manager.abortarTransação();
            throw ex;
        }              
        out.close();  
       
    }
    
}
