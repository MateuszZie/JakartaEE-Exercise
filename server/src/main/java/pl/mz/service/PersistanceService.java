/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import pl.mz.entities.Customer;

/**
 *
 * @author Mateusz
 */
@Stateless
public class PersistanceService {
   
   @Inject
   EntityManager entityManager;
   
   
   public Customer saveCustomer(Customer customer){
       if(customer.getId()==null) entityManager.persist(customer);
       else entityManager.merge(customer);
       return customer;
   }
   public void deleteCustomer(Long id){
       Customer customer = entityManager.find(Customer.class, id);
       entityManager.remove(customer);
   }
}
