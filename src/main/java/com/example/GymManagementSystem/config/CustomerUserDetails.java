package com.example.GymManagementSystem.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.GymManagementSystem.entity.Customer;
import com.example.GymManagementSystem.entity.Staff;

public class CustomerUserDetails implements UserDetails {

    
    public CustomerUserDetails(Customer customer, String username, String password,
            Collection<GrantedAuthority> authorities) {
        this.customer = customer;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public CustomerUserDetails(Staff staff, String username, String password,
            Collection<GrantedAuthority> authorities) {
        this.staff = staff;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    private Customer customer;
    private Staff staff;
    private String username;
    private String password;
    private Collection<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public Staff getStaff() {
        return staff;
    }

}
