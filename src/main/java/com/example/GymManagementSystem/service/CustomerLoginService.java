package com.example.GymManagementSystem.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.GymManagementSystem.config.CustomerUserDetails;
import com.example.GymManagementSystem.entity.Customer;
import com.example.GymManagementSystem.entity.CustomerLogin;
import com.example.GymManagementSystem.entity.StaffLogin;
import com.example.GymManagementSystem.repository.CustomerLoginRepository;
import com.example.GymManagementSystem.repository.StaffLoginRepository;

@Service
public class CustomerLoginService implements UserDetailsService {

    @Autowired
    private CustomerLoginRepository customerLoginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private StaffLoginRepository staffLoginRepository;

    @Override
    @Transactional(readOnly = true)
    public CustomerUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomerLogin customerLogin = customerLoginRepository.findByUsername(username).orElse(null);

        if(customerLogin != null){
            return new CustomerUserDetails(customerLogin.getCustomer(), customerLogin.getUsername(),
                customerLogin.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(customerLogin.getRole()));
        }

        StaffLogin staffLogin = staffLoginRepository.findByUsername(username).orElse(null);
        if(staffLogin != null){
            return new CustomerUserDetails(staffLogin.getStaff(), staffLogin.getUsername(), staffLogin.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("ADMIN"));
        }

        throw new UsernameNotFoundException("User not found: " + username);
        
    }

    public Map<String, Object> addNewCustomer(CustomerLogin customerLogin) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Attempt to add the new customer
            Customer customer = customerLogin.getCustomer();
            Map<String, Object> response2 = customerService.addNewCustomer(customer);

            // Ensure that the customer is saved and has a valid ID
            if ((boolean) response2.get("success")) {
                Customer savedCustomer = (Customer) response2.get("data");
                if (savedCustomer != null && savedCustomer.getID() != null) {
                    customerLogin.setCustomer(savedCustomer);
                    customerLogin.setPassword(passwordEncoder.encode(customerLogin.getPassword()));
                    CustomerLogin savedCustomerLogin = customerLoginRepository.save(customerLogin);

                    // Prepare the response
                    response.put("success", true);
                    response.put("data", savedCustomerLogin);
                } else {
                    response.put("success", false);
                    response.put("message", "Customer ID is null. Customer may not have been saved properly.");
                }
            } else {
                response.put("success", false);
                response.put("message", response2.getOrDefault("message", "Unknown error occurred"));
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error: " + e.getMessage());
        }
        return response;
    }

}
