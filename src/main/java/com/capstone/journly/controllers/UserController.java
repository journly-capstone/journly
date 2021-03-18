package com.capstone.journly.controllers;

import com.capstone.journly.models.User;
import com.capstone.journly.repositories.UserRepository;
import com.capstone.journly.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
@ControllerAdvice
public class UserController {

    private final UserRepository userDao;
    private final PasswordEncoder encoder;
    private final UserService userService;

    public UserController(UserRepository userDao, PasswordEncoder encoder, UserService userService) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.userService = userService;
    }

    @Value("${file-upload-path}")
    private String uploadPath;

    //Show Profile Settings to User
    @GetMapping("/profile-settings")
    public String showProfileSettings(Model model) {
        model.addAttribute("title", "Profile Settings");

        User sessionUser = userService.getLoggedInUser();
        User user = userDao.getOne(sessionUser.getId());
        model.addAttribute("user", user);

        return "users/profile-settings";
    }

    //Allow Users to Update Profile Information
    @PostMapping("/profile-settings/update-profile-information")
    public String updateProfileInformation(@ModelAttribute User user,
                                           @RequestParam(name = "update-user-profile-picture")MultipartFile uploadedFile,
                                           @RequestParam(name = "current-profile-picture") String imgFilePath,
                                           Model model) {

        User sessionUser = userService.getLoggedInUser();
        User updatedUser = userDao.getOne(sessionUser.getId());
        user.setImgFilePath(imgFilePath);

        if(uploadedFile != null) {
            String fileName = uploadedFile.getOriginalFilename();
            String filePath = Paths.get(uploadPath, fileName).toString();
            File destinationFile = new File(filePath);
            try {
                uploadedFile.transferTo(destinationFile);
                user.setImgFilePath("/uploads/" + fileName);
                model.addAttribute("message", "File successfully uploaded!");
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "Oops! Something went wrong! " + e);
                return "error/4xx";
            }
        }

        user.setId(updatedUser.getId());
        user.setPassword(updatedUser.getPassword());
        userDao.save(user);

        return "redirect:/profile-settings/#account-information";
    }


//    //Show Change Password View to User
//    @GetMapping("profile-settings/change-password")
//    public String showChangePassword(Model model) {
//        model.addAttribute("title", "Profile Settings - Change Password");
//
//        User sessionUser = userService.getLoggedInUser();
//        User user = userDao.getOne(sessionUser.getId());
//        model.addAttribute("user", user);
//
//        return "users/change-password";
//    }


    //Allow Users to Update Password
    @PostMapping("/profile-settings/change-password")
    public String changePassword(@RequestParam(name="password")String password, @RequestParam(name="confirm")String confirm, @RequestParam(name="id")Long id, Model model){
        User user = userDao.getOne(id);

        if (password.equals(confirm)){
            String hash = encoder.encode(password);
            user.setPassword(hash);
            userDao.save(user);
            return "login";
        }
//        return showChangePassword(model);

        return "users/change-password";
    }

//    //Allow Users to Update Password
//    @PostMapping("/profile-settings/change-password")
//    public String changePassword(Model model, @Valid @ModelAttribute User user, Errors validation, @RequestParam(name = "confirm") String confirm, @RequestParam(name = "password")String password){
//
//        model.addAttribute("title", "Profile Settings - Change Password");
////        model.addAttribute("user", user);
//
//        String hash = encoder.encode(user.getPassword());
//
//        if(!user.getPassword().equals(confirm)){
//            validation.rejectValue(
//                    "password",
//                    "user.password",
//                    "Passwords do not match"
//            );
//        }
//
//        if(validation.hasErrors()) {
//            model.addAttribute("errors", validation);
//            model.addAttribute("user", user);
//
//            return "users/profile-settings";
//            //takes me straight to the profile-settings page (with account information tab open) although the url is as follows: profile-settings/change-password#change-password
//            //but, does save changes on #change-password tab. just not the other tabs.
//        }
//
//        if (password.equals(confirm)){
//            user.setId(user.getId());
//            user.setPassword(hash);
//            userDao.save(user);
//
//            return "redirect:/login";
//        }
//
////        if (password.equals(confirm)){
////            user.setId(user.getId());
////            String hash = encoder.encode(password);
////            user.setPassword(hash);
////            userDao.save(user);
////
////            return "redirect:/login";
////        }
//
//        return "redirect:/profile-settings";
//    }



}