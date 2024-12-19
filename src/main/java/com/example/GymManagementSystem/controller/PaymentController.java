package com.example.GymManagementSystem.controller;

import java.math.BigDecimal;
import java.security.Provider.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.GymManagementSystem.DTO.SessionDTO;
import com.example.GymManagementSystem.entity.Bill;
import com.example.GymManagementSystem.entity.Customer;
import com.example.GymManagementSystem.entity.FitnessSession;
import com.example.GymManagementSystem.entity.IteamProductBill;
import com.example.GymManagementSystem.entity.Product;
import com.example.GymManagementSystem.entity.Staff;
import com.example.GymManagementSystem.repository.BillRepository;
import com.example.GymManagementSystem.repository.CustomerRepository;
import com.example.GymManagementSystem.repository.ItemProductBillRepository;
import com.example.GymManagementSystem.repository.ProductRepository;
import com.example.GymManagementSystem.repository.StaffRepository;
import com.example.GymManagementSystem.service.CustomerServiceService;
import com.example.GymManagementSystem.service.FitnessSessionService;
import com.example.GymManagementSystem.service.ServiceService;
import com.example.GymManagementSystem.service.TimeSlotService;
import com.example.GymManagementSystem.service.VNPAYService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
    @Autowired
    private VNPAYService vnPayService;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ItemProductBillRepository itemProductBillRepository;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private CustomerServiceService customerServiceService;
    @Autowired
    private FitnessSessionService fitnessSessionService;
    @Autowired
    private TimeSlotService timeSlotService;
    // @GetMapping({"", "/"})
    // public String home(){
    // return "createOrder";
    // }

    // Chuyển hướng người dùng đến cổng thanh toán VNPAY
    @PostMapping("/api/make_new_order_card")
    public ResponseEntity<?> paybycard(@RequestBody Map<String, Object> orderRequest, HttpServletRequest request,
            HttpSession session) {

        session.setAttribute("orderRequest", orderRequest);
        // Extract customer data
        Map<String, Object> customerdata = (Map<String, Object>) orderRequest.get("customer");
        double total = ((Number) orderRequest.get("total")).doubleValue();

        // Extract staff data
        Map<String, Object> staff_Map = (Map<String, Object>) orderRequest.get("staff");
        int staffID = (int) staff_Map.get("id");

        // Find customer and staff
        Customer customer = customerRepository.findCustomerByID((int) customerdata.get("id"));
        Staff staff = staffRepository.findStaffByID(staffID);

        // Tạo URL VNPAY
        String orderInfo = "Thanh toan don hang cua user " + customer.getFull_name();
        int orderTotal = (int) total * 1000;
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(request, orderTotal, orderInfo, baseUrl);

        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", vnpayUrl);
        return ResponseEntity.ok(response);

        // return "redirect:" + vnpayUrl;
    }

    @PostMapping("/make-order-for-course")
    public ResponseEntity<?> payForCourse(@RequestBody Map<String, Object> orderRequest, HttpServletRequest request,
            HttpSession session) {

        session.setAttribute("orderRequest", orderRequest);
        // Extract customer data
        com.example.GymManagementSystem.entity.Service service = serviceService
                .getServiceById(Integer.parseInt(orderRequest.get("serviceId").toString()));
        int total = (int) service.getSale_price() * 1000;
        String orderInfo = "Thanh toan don goi tap: " + service.getName();

        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(request, total, orderInfo, baseUrl);

        Map<String, String> response = new HashMap<>();
        response.put("redirectUrl", vnpayUrl);
        return ResponseEntity.ok(response);

        // return "redirect:" + vnpayUrl;
    }

    // Sau khi hoàn tất thanh toán, VNPAY sẽ chuyển hướng trình duyệt về URL này
    @GetMapping("/vnpay-payment-return")
    public String paymentCompleted(HttpServletRequest request, Model model) {

        int paymentStatus = vnPayService.orderReturn(request);
        Map<String, Object> orderRequest = (Map<String, Object>) request.getSession().getAttribute("orderRequest");
        if (orderRequest.get("serviceId") != null) {
            System.out.println("Service ID: " + orderRequest.get("serviceId"));
            List<LinkedHashMap> rawSessions = (List<LinkedHashMap>) orderRequest.get("sessions");

            List<SessionDTO> sessions = rawSessions.stream()
                .map(map -> new SessionDTO(
                        (Integer) map.get("timeSlotId"), 
                        LocalDate.parse((String) map.get("date")) // Chuyển đổi nếu `date` là chuỗi
                ))
                .collect(Collectors.toList());

            System.out.println("Session size: " + sessions.size());
            int ID_customer_service =  Integer.parseInt(orderRequest.get("serviceId").toString());
            List<FitnessSession> toSave = new ArrayList<>();
            for (int i = 0; i < sessions.size(); ++i) {
                SessionDTO session = sessions.get(i);

                // System.out.println(session.getDate());
                // System.out.println(session.getTimeSlotId());
                // System.out.println("-----------");
                FitnessSession fitnessSession = new FitnessSession(
                        i + 1,
                        customerServiceService.getCustomerServiceById(ID_customer_service),
                        session.getDate(),
                        timeSlotService.getTimeSlotById(session.getTimeSlotId()).get(),
                        "Ha Noi");
                toSave.add(fitnessSession);

            }

            fitnessSessionService.createFitnessSessions(toSave);
        }

        else {
            

           // Map<String, Object> orderRequest = (Map<String, Object>) request.getSession().getAttribute("orderRequest");

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
            bill.setPayment_method("Credit Card");
            bill.setStatus("Pending");
            billRepository.save(bill);

            // Find the newly created bill
            Bill newBill = billRepository.findBillByCustomerAndStatus(customer, "Pending");

            // Process and save bill items
            List<Map<String, Object>> items = (List<Map<String, Object>>) orderRequest.get("items");
            for (Map<String, Object> itemData : items) {
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
        }

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    }
}