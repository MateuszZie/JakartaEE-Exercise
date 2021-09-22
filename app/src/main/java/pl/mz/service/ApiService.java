/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.mz.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;
import pl.mz.domain.Customer;

/**
 *
 * @author Mateusz
 */
@Getter
@Setter
@RequestScoped
@Named
public class ApiService {

    private boolean serverBusy;

    private String buttonValue, titleValue;

    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target("http://localhost:8080/server/api/v1");

    public Customer getCustomer(String ident) {

        WebTarget customerWebTarget = webTarget.path("customer/" + ident);
        try {
            Customer customer = customerWebTarget.request(MediaType.APPLICATION_JSON).async().get(Customer.class).get(1, TimeUnit.SECONDS);
            setServerBusy(false);
            return customer;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println(e.getMessage());
            setServerBusy(true);
            return null;
        }

    }

    public List<Customer> getAllCustomer() {
        WebTarget customerWebTarget = webTarget.path("customer");
        try{
             return customerWebTarget.request(MediaType.APPLICATION_JSON).get(List.class);
        }catch(Exception e){
            return null;
        }
           
        }
    

    public String addUpdateCustomer(Customer customer) {
        Customer baseCustomer = getCustomer(customer.getIdentyfikator());
        if (serverBusy) {
            return serverError();
        }
        if (customer.getId() == null && baseCustomer == null) {
            WebTarget customerWebTarget = webTarget.path("customer/new");
            try {
                customerWebTarget.request(MediaType.APPLICATION_JSON).async().post(Entity.entity(customer, MediaType.APPLICATION_JSON)).get(1, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                System.out.println(e.getMessage());
                return serverError();
            }
            return "index";
        } else if (customer.getId() == null && baseCustomer != null) {
            return displayError(customer.getIdentyfikator());
        } else if ((customer.getId() != null && baseCustomer == null) || customer.getId().equals(baseCustomer.getId())) {
            WebTarget customerWebTarget = webTarget.path("customer/update");
            try {
                customerWebTarget.request(MediaType.APPLICATION_JSON).async().put(Entity.entity(customer, MediaType.APPLICATION_JSON)).get(1, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                System.out.println(e.getMessage());
                return serverError();
            }
            return "index";
        }
        return displayError(customer.getIdentyfikator());
    }

    public String deleteCustomer(Long id) {
        WebTarget customerWebTarget = webTarget.path("customer/delete/" + id.toString());
        try {
            customerWebTarget.request(MediaType.APPLICATION_JSON).async().delete().get(1, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.println(e.getMessage());
            return serverError();
        }
        return "index";
    }

    public String formCustomer(String identyfikator) {
        Map<String, Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if ("".equals(identyfikator)) {
            setButtonValue("Add");
            setTitleValue("Add Customer");
            sessionMapObj.put("editCustomer", new Customer());
        } else {
            setButtonValue("Update");
            setTitleValue("Update Customer");
            Customer customer = getCustomer(identyfikator);
            if (serverBusy) {
                return serverError();
            }
            sessionMapObj.put("editCustomer", customer);
        }
        return "customerForm";
    }

    private String displayError(String identyfikator) {
        FacesMessage myMessage = new FacesMessage("Customer identyfikator " + identyfikator + " already exist", "");
        myMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, myMessage);
        return "customerForm";
    }

    private String serverError() {
        FacesMessage myMessage = new FacesMessage("Server is busy or not avaiable please try again letter", "");
        myMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, myMessage);        
        System.out.println(FacesContext.getCurrentInstance().getViewRoot().getViewId());
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();

    }
}
