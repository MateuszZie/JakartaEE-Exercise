/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.endopoints;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import pl.mz.domain.ImageDto;
import pl.mz.entities.Album;
import pl.mz.entities.MyImage;
import pl.mz.service.AlbumService;
import pl.mz.service.ImageService;

/**
 *
 * @author Mateusz
 */
@RequestScoped
@Path("image")
public class ImageConroller {
    
    @Inject
    private ImageService imageService;
    
    @Inject
    private AlbumService albumService;
    
    @POST
    public void saveImages(ImageDto image){
        Album album = null;
        if(image.getAlbumId() != null) album = albumService.getAblum(image.getAlbumId());
        
        if(album != null){
            MyImage newImage = new MyImage(image.getName(),image.getImage(), album);
            album.getImage().add(newImage);
            albumService.saveCustomer(album);
        }else{
            album = new Album(image.getAlbumName(), new ArrayList<>());
            imageService.saveCustomer(new MyImage(image.getName(), image.getImage(), album));
        }
    }
    
}
