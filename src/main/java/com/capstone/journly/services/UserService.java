//package com.codeup.springblog.services;
//
//import com.codeup.springblog.models.User;
//import com.codeup.springblog.repositories.UserRepository;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//@Service("userService")
//public class UserService {
//    private final UserRepository usersDao;
//
//    public UserService(UserRepository usersDao) {
//        this.usersDao = usersDao;
//    }
//
//    // returns user in our database
//    public User getLoggedInUser() {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        long userId = user.getId();
//        return usersDao.findById(userId).get();
//    }
//
//}
//
