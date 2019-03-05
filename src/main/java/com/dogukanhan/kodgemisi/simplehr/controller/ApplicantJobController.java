package com.dogukanhan.kodgemisi.simplehr.controller;

import com.dogukanhan.kodgemisi.simplehr.entity.JobApplication;
import com.dogukanhan.kodgemisi.simplehr.repository.JobListingRepository;
import com.dogukanhan.kodgemisi.simplehr.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/applicant/job")
public class ApplicantJobController {
    @Autowired
    private JobListingRepository jobListingRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping
    public String jobs(Model model){
        model.addAttribute("jobListings",jobListingRepository.findAll());
        return "/applicant/job/index";
    }

    @GetMapping("/detail/{id}")
    public String jobDetail(@PathVariable("id") long id, Model model){
        model.addAttribute("jobApplication",new JobApplication());
        model.addAttribute("jobListing",jobListingRepository.findById(id).get());
        return "/applicant/job/detail";
    }

    @PostMapping("/apply")
    public String apply(Model model){
        return null;
    }

    @PostMapping("/upload-cv")
    @ResponseBody
    public String uploadCv(@RequestParam("file") MultipartFile file) throws IOException {
        return fileStorageService.storeFile(file);
    }

}
