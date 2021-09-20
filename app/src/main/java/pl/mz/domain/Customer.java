/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Mateusz
 */
@Getter
@Setter
@Entity
@NamedQuery(name = Customer.GET_ALL_CUSTOMERS, query = "select c from Customer c")
@NamedQuery(name = Customer.GET_CUSTOMER_BY_IDENTIFIER, query = "select c from Customer c where c.identyfikator = :id")
public class Customer extends AbstractEntity{
    
    public static final String GET_ALL_CUSTOMERS = "Customer.getAll";
    public static final String GET_CUSTOMER_BY_IDENTIFIER = "Customer.getByIdentifier";
    
    @NotNull
    @Size(max = 32)
    @Column(unique=true)
    private String identyfikator;
    
    private String name, phoneNumber, email;
}
