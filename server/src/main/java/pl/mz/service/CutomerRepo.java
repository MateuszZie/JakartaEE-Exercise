/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.service;

import javax.ejb.Stateless;
import pl.mz.entities.Customer;

/**
 *
 * @author Mateusz
 */
@Stateless
public class CutomerRepo extends PersistanceService<Customer>{
         
    public void deleteCustomer(Long id){
       Customer customer = entityManager.find(Customer.class , id);
       entityManager.remove(customer);
   }    
}
