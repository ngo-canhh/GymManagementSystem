package com.example.GymManagementSystem.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.GymManagementSystem.DTO.StaffDTO;
import com.example.GymManagementSystem.entity.PersonalTrainer;
import com.example.GymManagementSystem.entity.PositionInformation;
import com.example.GymManagementSystem.entity.Staff;
import com.example.GymManagementSystem.entity.StaffRole;
import com.example.GymManagementSystem.repository.PersonalTrainerRepository;
import com.example.GymManagementSystem.repository.StaffRepository;
import com.example.GymManagementSystem.repository.StaffRoleRepository;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffRoleRepository staffRoleRepository;

    @Autowired
    private PersonalTrainerRepository personalTrainerRepository;

    @Autowired
    private PositionInformationService positionInformationService;

    public Map<String, Object> getAllStaff() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Staff> staffs = staffRepository.findAllStaff();
            for (Staff staff : staffs) {
                StaffRole staffRole = staffRoleRepository.findActiveStaffRoleById(staff.getID());
                if (staffRole != null) {
                    staff.setRole(staffRole.getPositionInformation().getRole());
                    staff.setStatus("Active");
                } else {
                    staff.setRole("Nghi viec");
                    staff.setStatus("Unactive");
                }
            }
            response.put("data", staffs);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    public Map<String, Object> getStaffByID(int staffID) {
        Map<String, Object> response = new HashMap<>();
        try {
            Staff staff = staffRepository.findStaffByID(staffID);
            staff.setStaffDTOs(staffRoleRepository.findInforStaff(staffID));
            for (StaffDTO staffDTO : staff.getStaffDTOs()) {
                if (staffDTO.getStatus().equals("Active")) {
                    staff.setRole(staffDTO.getRole());
                    break;
                }
            }
            response.put("data", staff);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    public Map<String, Object> addNewStaff(Staff staff, MultipartFile image) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Lấy thông tin role
            Map<String, Object> roleResponse = positionInformationService.getPositionInformationByRole(staff.getRole());
            if (!(boolean) roleResponse.get("success")) {
                response.put("success", false);
                response.put("message", "Role not found");
                return response;
            }

            // Kiểm tra file ảnh
            if (image != null && !image.isEmpty()) {
                Path uploadPath = Path.of("src/main/resources/static/images/staff");
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Tạo tên file duy nhất
                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path targetLocation = uploadPath.resolve(fileName);

                // Lưu file ảnh vào server
                Files.copy(image.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
                staff.setImage_url("/images/staff/" + fileName);
            } else {
                response.put("success", false);
                response.put("message", "Image file is missing");
                return response;
            }

            

            // Lưu staff vào database
            Staff savedStaff = staffRepository.save(staff);

            // Tạo StaffRole và lưu vào database
            StaffRole staffRole = new StaffRole();
            staffRole.setStatus("Active");
            staffRole.setPositionInformation((PositionInformation) roleResponse.get("data"));
            staffRole.setCreate_date(LocalDate.now());
            staffRole.setStaff(savedStaff);

            staffRoleRepository.save(staffRole);

            if(staffRole.getPositionInformation().getID() == 1){
                PersonalTrainer pt = new PersonalTrainer(savedStaff);
                personalTrainerRepository.save(pt);
            }

            // Trả về kết quả thành công
            response.put("success", true);
            response.put("data", savedStaff);
        } catch (Exception e) {
            // Xử lý lỗi
            response.put("success", false);
            response.put("message", "An error occurred: " + e.getMessage());
        }

        return response;
    }

    public Map<String, Object> updateStaff(Staff staff) {
        Map<String, Object> response = new HashMap<>();
        try {
            staff.setImage_url(staffRepository.findStaffByID(staff.getID()).getImage_url());
            response.put("data", staffRepository.save(staff));
            StaffRole staffRole = staffRoleRepository.findActiveStaffRoleById(staff.getID());//xem hiện tại đang lam chức vụ j
            //nếu thay đổi chức vụ
            if (!staffRole.getPositionInformation().getRole().equals(staff.getRole())) {
                System.out.println("thay đổi chức vụ");
                // unactive vai trò cũ
                staffRoleRepository.unactiveStaffRole(staffRole.getID());

                // lưu vai trò mới
                
                PositionInformation positionInformation = (PositionInformation) positionInformationService.getPositionInformationByRole(staff.getRole()).get("data");
                StaffRole newStaffRole = new StaffRole(staff, positionInformation, LocalDate.now(), "Active");
                staffRoleRepository.save(newStaffRole);

                //nếu chức vụ mới là huấn luyện viên
                if(newStaffRole.getPositionInformation().getID() == 1){
                    PersonalTrainer pt = new PersonalTrainer(newStaffRole.getStaff());
                    System.out.println("Lưu thành công");
                    personalTrainerRepository.save(pt);
                }
            }
            response.put("success", true);
            response.put("message", "Staff updated successfully");

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An error occurred: " + e.getMessage());
        }
        return response;
    }

    public Map<String, Object> getStaffBySexAndRoleAndStatus(String sex, String role, String status) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<StaffRole> staffRoles = staffRoleRepository.findStaffBySexAndRoleAndStatus(sex, role, status);
            List<Staff> staffs = new ArrayList<>();
            for (StaffRole staffRole : staffRoles) {
                Staff staff = staffRole.getStaff();
                staff.setRole(staffRole.getPositionInformation().getRole());
                staff.setStatus(staffRole.getStatus());
                staffs.add(staff);
            }

            response.put("data", staffs);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;

    }

    public Map<String, Object> getStaffByName(String name) {
        Map<String, Object> response = new HashMap<>();
        try {
            List<StaffRole> staffRoles = staffRoleRepository.findAllStaffRolesByName(name);
            List<Staff> staffs = new ArrayList<>();
            for (StaffRole staffRole : staffRoles) {
                Staff staff = staffRole.getStaff();
                staff.setRole(staffRole.getPositionInformation().getRole());
                staff.setStatus(staffRole.getStatus());
                staffs.add(staff);
            }

            response.put("data", staffs);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;

    }

}
