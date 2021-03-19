package com.capstone.journly.controllers;

import com.capstone.journly.models.User;
import com.capstone.journly.models.UserWithRoles;
import com.capstone.journly.repositories.UserRepository;
import com.capstone.journly.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        User sessionUser = userService.getLoggedInUser();
        User user = userDao.getOne(sessionUser.getId());

        model.addAttribute("title", "Profile Settings");
        model.addAttribute("user", user);

        return "users/profile-settings";
    }

    //Allow Users to Update Profile Information (username, email, profile picture)
    @PostMapping("/profile-settings/update-profile-information")
    public String updateProfileInformation(@ModelAttribute User user,
                                           @RequestParam(name = "update-user-profile-picture", required = false) MultipartFile uploadedFile,
                                           @RequestParam(name = "current-profile-picture") String imgFilePath,
                                           Model model) {

        User sessionUser = userService.getLoggedInUser();
        User updatedUser = userDao.getOne(sessionUser.getId());
        user.setImgFilePath(imgFilePath);

        if(!uploadedFile.isEmpty()) {
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

        UserDetails userDetails = new UserWithRoles(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(auth);

        return "redirect:/profile-settings/#account-information";
    }

    //Allow Users to Update Password
    @PostMapping("/profile-settings/change-password")
    public String changePassword(@RequestParam(name="password")String password,
                                 @RequestParam(name="confirm")String confirm,
                                 @RequestParam(name="id")Long id, Model model,
                                 HttpServletRequest request){
        User user = userDao.getOne(id);

        if (password.equals(confirm)){
            String hash = encoder.encode(password);
            user.setPassword(hash);
            userDao.save(user);

            request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);

            return "redirect:/logout";
        }

        return "users/change-password";
    }
}