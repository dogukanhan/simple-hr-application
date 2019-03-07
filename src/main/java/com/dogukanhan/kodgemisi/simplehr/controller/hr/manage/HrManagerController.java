package com.dogukanhan.kodgemisi.simplehr.controller.hr.manage;


import com.dogukanhan.kodgemisi.simplehr.repository.JobApplicationRepository;
import com.dogukanhan.kodgemisi.simplehr.repository.JobListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/hr/manage")
public class HrManagerController {

    @Autowired
    private JobListingRepository jobListingRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @GetMapping
    public String index(Model model){
        model.addAttribute("totalJobListing",jobListingRepository.count());
        model.addAttribute("totalJobApplication",jobApplicationRepository.count());
        return "hr/manage/index";
    }

}
