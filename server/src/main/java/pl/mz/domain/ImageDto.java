/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.domain;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Mateusz
 */
@RequestScoped
@Getter
@Setter
@Named
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
         
   private String name;
   
   private Byte[] image;
    private String albumName;
    private Long albumId;
}
