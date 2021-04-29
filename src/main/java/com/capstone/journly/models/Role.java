//package com.capstone.journly.models;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "roles")
//public class Role {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(nullable = false, length = 10)
//    private String role;
//
//    public Role() {}
//
//    public Role(long id, String role) {
//        this.id = id;
//        this.role = role;
//    }
//
//    public long getRoleId() {
//        return id;
//    }
//
//    public void setRoleId(long id) {
//        this.id = id;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//}