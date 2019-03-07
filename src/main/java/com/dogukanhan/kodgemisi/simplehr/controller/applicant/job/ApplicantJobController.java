package com.dogukanhan.kodgemisi.simplehr.controller.applicant.job;

import com.dogukanhan.kodgemisi.simplehr.entity.JobApplication;
import com.dogukanhan.kodgemisi.simplehr.entity.JobListing;
import com.dogukanhan.kodgemisi.simplehr.repository.JobApplicationRepository;
import com.dogukanhan.kodgemisi.simplehr.repository.JobListingRepository;
import com.dogukanhan.kodgemisi.simplehr.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@Controller
@RequestMapping("/applicant/job")
public class ApplicantJobController {
    @Autowired
    private JobListingRepository jobListingRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;
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

    @PostMapping("/detail/{id}")
    public String apply(@PathVariable("id") long id
                        ,@ModelAttribute JobApplication jobApplication
                        ,Model model){
        model.addAttribute("jobListing",jobListingRepository.findById(id).get());
        model.addAttribute("jobApplication",jobApplication);

        try {
            JobListing jobListing = jobListingRepository.findById(id).get();
            jobApplication.setJobListing(jobListing);
            jobApplicationRepository.save(jobApplication);
            jobListing.getApplicationList().add(jobApplication);
            jobListing.setTotalApply(jobListing.getTotalApply()+1);
            jobListingRepository.save(jobListing);
            model.addAttribute("error",false);
            model.addAttribute("complete",true);
            return "/applicant/job/detail";
        }catch(ConstraintViolationException e){
            model.addAttribute("error",true);
            ConstraintViolation c = e.getConstraintViolations().iterator().next();
            model.addAttribute("errorMessage",c.getInvalidValue()+" is not acceptable, "+c.getMessage());
             model.addAttribute("complete",false);
            return "/applicant/job/detail";
        }
    }

    @PostMapping("/upload-cv")
    @ResponseBody
    public String uploadCv(@RequestParam("file") MultipartFile file) throws IOException {
        return fileStorageService.storeFile(file);
    }

}
