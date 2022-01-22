/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.service;

import java.lang.Class;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.mz.entities.AbstractEntity;

/**
 *
 * @author Mateusz
 * @param <T>
 */
@Getter
@Log
public abstract class PersistanceService<T extends AbstractEntity> {
   
   @Inject
   EntityManager entityManager;
   
   
   public T saveCustomer(T entity){
       if(entity.getId()==null) entityManager.persist(entity);
       else entityManager.merge(entity);
       return entity;
   }
}
