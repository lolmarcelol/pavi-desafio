/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pavi.ws;

import br.com.pavi.controllers.AlunoManagerImpl;
import br.com.pavi.controllers.IAlunoManager;
import br.com.pavi.models.Aluno;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author narim
 */
@Path("aluno")
public class AlunoResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AlunoResource
     */
    public AlunoResource() {
    }

    /**
     * Retrieves representation of an instance of br.com.pavi.ws.AlunoResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @POST
    @Consumes({"application/json"})
    @Path("inserir")
    public String inserir(String content){
       Gson g = new Gson();
       Aluno a;
       a = (Aluno) g.fromJson(content, Aluno.class);
       IAlunoManager manager;
       manager = new AlunoManagerImpl();
       Aluno aux = manager.cadastrar(a);
       if(aux.getNome().isEmpty()){
           return "0";
       }else{
           return "1";
       }
    }
    
    @GET
    @Produces({"application/json"})
    @Path("listar")
    public String listar(){
       Gson g = new Gson();
       IAlunoManager manager;
       manager = new AlunoManagerImpl();
       List<Aluno> list = manager.listar();
       return g.toJson(list);
    }
    
    @GET
    @Path("deletar/{id}")
    public String deletar(@PathParam("id")int id){
       Gson g = new Gson();
       IAlunoManager manager;
       manager = new AlunoManagerImpl();
       if(manager.deletar(id)){
           return "1";
       }else{
           return "0";
       }
    }
    
    @GET
    @Path("find/{id}")
    public String find(@PathParam("id")int id){
       Gson g = new Gson();
       IAlunoManager manager;
       manager = new AlunoManagerImpl();
       Aluno retorno =  manager.findById(id);
       return g.toJson(retorno);
    }
    
    @PUT
    @Consumes({"application/json"})
    @Path("alterar")
    public String alterar(String content){
       Gson g = new Gson();
       Aluno aluno;
       aluno = (Aluno) g.fromJson(content, Aluno.class);
       IAlunoManager manager;
       manager = new AlunoManagerImpl();
       if(manager.alterar(aluno)){
           return "1";
       }else{
           return "0";
       }
    }
    

    /**
     * PUT method for updating or creating an instance of AlunoResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
