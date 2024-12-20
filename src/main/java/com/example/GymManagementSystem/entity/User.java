 package com.example.GymManagementSystem.entity;

     import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
      import lombok.Data;
    import lombok.NoArgsConstructor;

     @Entity
        @Table(name="users")

         @Data
      @NoArgsConstructor
    @AllArgsConstructor
    public class User {

         @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

    @Column(name = "user_name")

           private String username;

    @Column(name="user_password")
    private String password;
