package com.dogukanhan.kodgemisi.simplehr.controller;

import com.dogukanhan.kodgemisi.simplehr.entity.JobApplication;
import com.dogukanhan.kodgemisi.simplehr.repository.JobListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/applicant/job")
public class ApplicantJobController {
    @Autowired
    private JobListingRepository jobListingRepository;

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

    @PostMapping("apply")
    public String apply(Model model){
        return null;
    }

}
