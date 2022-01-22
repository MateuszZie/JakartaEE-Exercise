/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Mateusz
 */
@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class MyImage extends AbstractEntity {
       
   private String name;
   
   @Lob
   private Byte[] image;
   
   @ManyToOne(cascade = CascadeType.ALL)
   private Album album;
   
       
    
}
