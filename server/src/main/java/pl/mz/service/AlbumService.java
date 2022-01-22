/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.service;

import javax.ejb.Stateless;
import pl.mz.entities.Album;

/**
 *
 * @author Mateusz
 */
@Stateless
public class AlbumService extends PersistanceService<Album>{
    
    public Album getAblum(Long id){
        return entityManager.find(Album.class, id);
    }
    
}
