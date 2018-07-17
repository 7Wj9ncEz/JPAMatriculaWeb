package com.algaworks.curso.jpa2.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import com.algaworks.curso.jpa2.modelo.Professor;
import com.algaworks.curso.jpa2.service.NegocioException;
import com.algaworks.curso.jpa2.util.jpa.Transactional;

public class ProfessorDAO implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Inject
   EntityManager manager;
   
   public Professor buscarPeloCodigo(Long codigo) {
      return manager.find(Professor.class, codigo);
   }
   
   public void salvar() {  
       manager.merge(professor);
     }
   
   public Professor professor;
   
   @SuppressWarnings("unchecked")
   List<Professor> buscarTodos(){
      return manager.createQuery("from Professor").getResultList();
   }
  
   @Transactional
   public void excluir()throws NegocioException{
       professor = buscarPeloCodigo(professor.getCodigo());
       
       try {
           manager.remove(professor);
           manager.flush();
       }catch(PersistenceException e){
           throw new NegocioException("Aluno não pode ser excluido");
       }
   } 
   
}