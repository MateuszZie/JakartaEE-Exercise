/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import pl.mz.entities.Customer;

/**
 *
 * @author Mateusz
 */
@Stateless
public class QueryService {

    @Inject
    EntityManager entityManager;

    public List<Customer> getCustomers() {
        return entityManager.createNamedQuery(Customer.GET_ALL_CUSTOMERS, Customer.class).getResultList();
    }

    public Customer getCustomer(String id) {
        List<Customer> customers = entityManager.createNamedQuery(Customer.GET_CUSTOMER_BY_IDENTIFIER, Customer.class).setParameter("id", id).getResultList();
        if (customers.isEmpty()) {
            return null;
        }
        return customers.get(0);
    }

    public List<String> getIdentyfikators() {
        return entityManager.createNamedQuery(Customer.GET_ALL_IDENTIFIER, String.class).getResultList();
    }
}
