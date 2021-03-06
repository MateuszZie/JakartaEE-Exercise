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
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import pl.mz.entities.Customer;
import pl.mz.service.CutomerRepo;
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
    CutomerRepo persistanceService;

    @GET
    public List<Customer> getCustmers() {
        return queryService.getCustomers();
    }

    @GET
    @Path("identy")
    public List<String> getIdentyfikators() {
        return queryService.getIdentifiers();
    }

    @GET
    @Path("{identifier}")
    public Customer getCustomer(@PathParam("identifier") String id) {

        Customer customer = queryService.getCustomer(id);
        return customer;
    }

    @POST
    @Path("new")
    public Customer createCustomer(Customer customer) {

        return persistanceService.saveCustomer(customer);
    }

    @PUT
    @Path("update")
    public void updateCustomer(pl.mz.DTO.Customer customer) {
        queryService.geCustomerById(customer);
    }

    @DELETE
    @Path("delete/{id}")
    public void deleteCustomer(@PathParam("id") Long id) {

        persistanceService.deleteCustomer(id);
    }



}
