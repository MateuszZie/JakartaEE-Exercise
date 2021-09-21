/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.domain;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
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
@Named
@RequestScoped
public class Customer{
       
    private Long id;
    
    @NotNull
    @Size(max = 32)
    private String identyfikator;
    
    private String name, phoneNumber, email;
}
