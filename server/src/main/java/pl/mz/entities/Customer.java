/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Customer extends AbstractEntity{
    
    @NotNull
    @Size(max = 32)
    @Column(unique=true)
    private String identyfikator;
    
    private String name, phoneNumber, email;
}