/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Mateusz
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Album extends AbstractEntity{
   
   private String name;
   
   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   List<MyImage> image;
    
}
