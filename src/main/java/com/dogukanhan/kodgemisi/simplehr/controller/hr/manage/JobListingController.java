package com.dogukanhan.kodgemisi.simplehr.controller.hr.manage;

import com.dogukanhan.kodgemisi.simplehr.entity.JobListing;
import com.dogukanhan.kodgemisi.simplehr.repository.JobApplicationRepository;
import com.dogukanhan.kodgemisi.simplehr.repository.JobListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Controller
@RequestMapping("/hr/manage/job-listing")
public class JobListingController {
    @Autowired
    private JobListingRepository jobListingRepository;


    @GetMapping("/list")
    public String jobListingList(Model model){
        model.addAttribute("jobListings",jobListingRepository.findAll());

        return "hr/manage/job_listing/list";
    }

    @GetMapping("/detail/{id}")
    public String jobListingDetail(@PathVariable("id") long id, Model model){
        return null;
    }

    @GetMapping("/editor")
    public String getJobListingCreateForm(Model model){
        model.addAttribute("jobListing",new JobListing());
        return "hr/manage/job_listing/editor";
    }

    @GetMapping("/editor/{id}")
    public String jobListingEditor(@PathVariable("id") long id, Model model){
        model.addAttribute("jobListing",jobListingRepository.findById(id));
        return "hr/manage/job_listing/editor";
    }

    @PostMapping("/editor")
    public String jobListingCreateFormResult(@ModelAttribute JobListing jobListing, Model model){
        try {
            jobListingRepository.save(jobListing);
            model.addAttribute("error",false);
            return "redirect:/hr/manage/job_listing/list";
        }catch(ConstraintViolationException e){
            model.addAttribute("error",true);
            ConstraintViolation c = e.getConstraintViolations().iterator().next();

            model.addAttribute("errorMessage",c.getInvalidValue()+" is not acceptable, "+c.getMessage());

        }
        return "hr/manage/job_listing/editor";
    }


    @GetMapping("/remove/{id}")
    public String jobListingDelete(@PathVariable("id") long id){
        jobListingRepository.deleteById(id);
        return "redirect:/hr/manage/job-listing/list";
    }
}
