package com.example.GymManagementSystem.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.GymManagementSystem.entity.Bill;
import com.example.GymManagementSystem.entity.Customer;
import com.example.GymManagementSystem.entity.IteamProductBill;
import com.example.GymManagementSystem.entity.Product;
import com.example.GymManagementSystem.entity.Staff;
import com.example.GymManagementSystem.repository.BillRepository;
import com.example.GymManagementSystem.repository.CustomerRepository;
import com.example.GymManagementSystem.repository.ItemProductBillRepository;
import com.example.GymManagementSystem.repository.ProductRepository;
import com.example.GymManagementSystem.repository.StaffRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
public class Page_StaffController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ItemProductBillRepository itemProductBillRepository;

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private StaffRepository staffRepository;
    @GetMapping("/makeOrder")
    public String makeOrder(Model model){
        LocalDate date = LocalDate.now();
        model.addAttribute("date", date);
        return "StaffViews/order";
    }

    @GetMapping("/api/user_info")
    public ResponseEntity<?> getUserInfo(@RequestParam("id") int id){
        Customer customer = customerRepository.findCustomerByID(id);
        if(customer == null){
            return ResponseEntity.status(Response.SC_NOT_FOUND).body("Customer not found");
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/api/item_info")
    public ResponseEntity<?> getItemInfo(@RequestParam("id") int id){
        Product product = productRepository.findProductById(id);
        if(product == null){
            return ResponseEntity.status(Response.SC_NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.ok(product);
    }
    
    @PostMapping("/api/make_new_order")
    public ResponseEntity<?> postMethodName(@RequestBody Map<String, Object> orderRequest) {
        try {
            // Extract customer data
            Map<String, Object> customerdata = (Map<String, Object>) orderRequest.get("customer");
            double total = ((Number) orderRequest.get("total")).doubleValue();
            
            // Extract staff data
            Map<String, Object> staff_Map = (Map<String, Object>) orderRequest.get("staff");
            int staffID = (int) staff_Map.get("id");
            
            // Find customer and staff
            Customer customer = customerRepository.findCustomerByID((int) customerdata.get("id"));
            Staff staff = staffRepository.findStaffByID(staffID);
            
            // Create and save bill
            Bill bill = new Bill();
            bill.setCustomer(customer);
            bill.setStaff(staff);
            bill.setTotal_amount(total);
            bill.setCreate_date(LocalDateTime.now());
            bill.setPayment_method("Cash");
            bill.setStatus("Pending");
            billRepository.save(bill);
    
            // Find the newly created bill
            Bill newBill = billRepository.findBillByCustomerAndStatus(customer, "Pending");
    
            // Process and save bill items
            List<Map<String, Object>> items = (List<Map<String, Object>>) orderRequest.get("items");
            for(Map<String, Object> itemData : items){
                Product product = productRepository.findProductById((int) itemData.get("code"));
                IteamProductBill itemProductBill = new IteamProductBill();
                itemProductBill.setBill(newBill);
                itemProductBill.setProduct(product);
                itemProductBill.setQuantity((int) itemData.get("quantity"));
                itemProductBill.setPurchase_price(product.getSale_price());
                itemProductBill.setTotal(product.getSale_price() * (int) itemData.get("quantity"));
                itemProductBillRepository.save(itemProductBill);
            }
    
            // Update bill status
            newBill.setStatus("Completed");
            billRepository.save(newBill);
    
            // Return successful response
            return ResponseEntity.ok().body(Map.of(
                "message", "Order created successfully",
                "billId", newBill.getID()
            ));
        } catch (Exception e) {
            // Handle any errors
            return ResponseEntity.badRequest().body(Map.of(
                "error", "Failed to create order",
                "details", e.getMessage()
            ));
        }
    }
    
}
