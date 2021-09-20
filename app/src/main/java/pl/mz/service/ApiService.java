/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.service;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import pl.mz.domain.Customer;

/**
 *
 * @author Mateusz
 */
@Stateless
@Named
public class ApiService {

    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target("http://localhost:8080/server/api/v1");

    public Customer getCustomer(String ident) {
        WebTarget customerWebTarget = webTarget.path("customer/" + ident);
        return customerWebTarget.request(MediaType.APPLICATION_JSON).get(Customer.class);
    }

    public List<Customer> getAllCustomer() {
        WebTarget customerWebTarget = webTarget.path("customer");
        return customerWebTarget.request(MediaType.APPLICATION_JSON).get(List.class);
    }

    public String addUpdateCustomer(Customer customer) {
        if (customer.getId() == null) {
            WebTarget customerWebTarget = webTarget.path("customer/new");
            customerWebTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(customer, MediaType.APPLICATION_JSON));
        } else {
            WebTarget customerWebTarget = webTarget.path("customer/update");
            customerWebTarget.request(MediaType.APPLICATION_JSON).put(Entity.entity(customer, MediaType.APPLICATION_JSON));
        }
        return "index";
    }

    public String deleteCustomer(Long id) {
        WebTarget customerWebTarget = webTarget.path("customer/delete/" + id.toString());
        customerWebTarget.request(MediaType.APPLICATION_JSON).delete();
        return "index";
    }

    public String formCustomer(String identyfikator) {
        Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if("".equals(identyfikator)) sessionMapObj.put("editCustomer", new Customer());
        else {
            Customer customer = getCustomer(identyfikator);
            sessionMapObj.put("editCustomer", customer);
        }
        return "customerForm";
    }

}
