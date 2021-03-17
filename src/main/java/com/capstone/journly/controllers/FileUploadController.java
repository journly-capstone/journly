package com.capstone.journly.controllers;

import com.capstone.journly.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    @Value("${file-upload-path}")
    private String uploadPath;

    @GetMapping("profile-settings/upload-profile-picture")
    public String showUploadProfilePictureForm() {
        return "users/upload-profile-picture";
    }

    @PostMapping("profile-settings/upload-profile-picture")
    public String saveProfilePicture(
            @RequestParam(name = "file") MultipartFile uploadedFile,
            Model model
    ) {
        String filename = uploadedFile.getOriginalFilename();
        String filepath = Paths.get(uploadPath, filename).toString();
        File destinationFile = new File(filepath);

        try {
            uploadedFile.transferTo(destinationFile);
            model.addAttribute("message", "File successfully uploaded!");
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("message", "Oops! Something went wrong! " + e);
        }
        return "users/upload-profile-picture";
    }
}