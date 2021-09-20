/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.endopoints;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import pl.mz.entities.Customer;
import pl.mz.service.PersistanceService;
import pl.mz.service.QueryService;

/**
 *
 * @author Mateusz
 */
@Path("customer")
public class CustomerEndpoint {
    
    @Inject
    QueryService queryService;
    
    @Inject
    PersistanceService persistanceService;
    
    @GET
    public List<Customer> getCustmers(){
        return queryService.getCustomers();
    }
    
    @GET
    @Path("{identifier}")
    public Customer getCustomer(@PathParam("identifier")String id){
        Customer customer = queryService.getCustomer(id);
        if(customer==null){
            throw new NotFoundException("Customer with this id not available");
        }
        return customer;
    }
    
    @POST
    @Path("new")
    public Customer createCustomer(Customer customer){
       return persistanceService.saveCustomer(customer);
    }
    
    @PUT
    @Path("update")
    public Customer updateCustomer(Customer customer){
        return persistanceService.saveCustomer(customer);
    }
    
    @DELETE
    @Path("delete/{id}")
    public void deleteCustomer(@PathParam("id")Long id){
        
       persistanceService.deleteCustomer(id);
    }
    
    
}
