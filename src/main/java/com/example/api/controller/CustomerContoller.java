package com.example.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.example.api.domain.Customer;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
// con esto unificamos las rutas a nivel de clase, todos responderan a esta ruta
@RequestMapping("/clients")

public class CustomerContoller {

    private List<Customer> customers = new ArrayList<>(Arrays.asList(

            new Customer(1, "Emiro", "EmiroMunoz014", "056+"),
            new Customer(2, "Carlos", "EmiroMunoz013", "+123"),
            new Customer(3, "Jose", "EmiroMunoz02", "555+"),
            new Customer(4, "Ulises", "EmiroMunoz", "777+")

    ));

    // Metodo que servira como endpoint
    // El endpoint recibira solicitudes de tipo GetMapping, la diferencia es que no
    // Estamos usando GetMapping porque no estamos mapeando nada

    // Se aplico a nivel de metodo
    @RequestMapping(method = RequestMethod.GET)
    // @GetMapping
    public List<Customer> getCustomers() {
        return customers;
    }

    // Encuentra el cliente por su nombre de usuario
    // Se aplico a nivel de metodo
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    // @GetMapping("/{username}")
    public Customer getClient(@PathVariable String username) {
        for (Customer c : customers) {
            if (c.getUsername().equalsIgnoreCase(username)) {
                return c;
            }
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    // @PostMapping
    public Customer postClient(@RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }

    // aqui estamos actualizando un cliente, mediante postman

    @RequestMapping(method = RequestMethod.PUT)
    // @PutMapping
    public Customer putClient(@RequestBody Customer customer) {
        for (Customer c : customers) {
            if (c.getId() == customer.getId()) {
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return c;
            }
        }
        return null;
    }

    // aqui estamos eliminando un cliente
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    // @DeleteMapping("/{id}")
    public Customer deleteClient(@PathVariable int id) {
        for (Customer c : customers) {
            if (c.getId() == id) {
                customers.remove(c);
                System.out.println("Se elimino un cliente");
                return c;
            }
        }
        return null;
    }

    // En este metodo haremos modificaciones especificas
    @RequestMapping(method = RequestMethod.PATCH)
    // @PatchMapping
    public Customer patchClient(@RequestBody Customer customer) {

        for (Customer c : customers) {
            if (c.getId() == customer.getId()) {

                System.out.println("Se encontro usuario por el ID");

                if (customer.getName() != null) {
                    c.setName(customer.getName());
                    System.out.println("Se ha modificado nombre");
                }

                if (customer.getUsername() != null) {
                    c.setUsername(customer.getUsername());
                    System.out.println("Se ha modificado nombre de usuario");

                }

                if (customer.getPassword() != null) {
                    c.setPassword(customer.getPassword());
                    System.out.println("Se ha modificado clave");

                }

                return c;
            }
        }
        System.out.println("No se encontro ninguna coincidencia");
        return null;
    }

}