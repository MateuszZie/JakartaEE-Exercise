/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.service;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import pl.mz.entities.Customer;

/**
 *
 * @author Mateusz
 */
@Stateless
public class SuportTestService {
    
    @Inject
    EntityManager entityManager;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateEmail(Customer customer, String email){
        customer.setEmail(email);
        entityManager.merge(customer);
    }
    
    public void updateName(Customer customer, String name) {
        customer.setName(name);
        entityManager.merge(customer);
    }
    
}
