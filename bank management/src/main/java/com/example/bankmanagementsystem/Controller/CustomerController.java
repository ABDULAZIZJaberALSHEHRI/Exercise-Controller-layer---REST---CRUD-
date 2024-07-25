package com.example.bankmanagementsystem.Controller;

import com.example.bankmanagementsystem.ApiResponse.ApiResponse;
import com.example.bankmanagementsystem.Model.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    ArrayList<Customer> customers = new ArrayList<>();

    @GetMapping("/get-customers")
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    @PostMapping("/add-customer")
    public ApiResponse addCustomer(@RequestBody Customer customer){
        customers.add(customer);
        return new ApiResponse("Successfully added customer");
    }

    @PutMapping("/update-cus-info/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody Customer customer){
        customers.set(index, customer);
        return new ApiResponse("Successfully updated customer");
    }

    @DeleteMapping("/remove-cus/{index}")
    public ApiResponse removeCustomer(@PathVariable int index){
        customers.remove(index);
        return new ApiResponse("Successfully removed customer");
    }

    @PutMapping("/deposit/{index}/{amount}")
    public ApiResponse depositCustomer(@PathVariable int index, @PathVariable double amount){
        for (Customer d : customers) {
            customers.get(index).setBalance(d.getBalance()+amount);
            break;
        }
        return new ApiResponse("Successfully deposited customer");

    }

    @PutMapping("/withdraw/{index}/{amount}")
    public ApiResponse withdrawCustomer(@PathVariable int index, @PathVariable double amount){
            for (Customer w : customers) {
                if (customers.get(index).getBalance() < amount || amount ==0) {
                    return new ApiResponse("Not enough balance");

            }customers.get(index).setBalance(w.getBalance() - amount);
                break;
        }
        return new ApiResponse("Successfully withdrawn customer");


    }


}
