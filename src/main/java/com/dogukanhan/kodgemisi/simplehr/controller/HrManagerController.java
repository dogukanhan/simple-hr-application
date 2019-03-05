package com.dogukanhan.kodgemisi.simplehr.controller;


import com.dogukanhan.kodgemisi.simplehr.entity.JobListing;
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

    @GetMapping
    public String index(Model model){
        model.addAttribute("jobListings",jobListingRepository.findAll());
        return "hr/manage/index";
    }

    @GetMapping("job-listing/detail/{id}")
    public String jobListingDetail(@PathVariable("id") long id, Model model){
        return null;
    }

    @GetMapping("/job-listing/editor")
    public String getJobListingCreateForm(Model model){
        model.addAttribute("jobListing",new JobListing());
        return "hr/manage/job_listing/editor.html";
    }

    @GetMapping("/job-listing/editor/{id}")
    public String jobListingEditor(@PathVariable("id") long id, Model model){
        model.addAttribute("jobListing",jobListingRepository.findById(id));
        return "hr/manage/job_listing/editor.html";
    }

    // FIXME: 3/5/19 Changed Url Must be adapted to new one!
    @PostMapping("/job-listing/create")
    public String jobListingCreateFormResult(@ModelAttribute JobListing jobListing){
        return "result";
    }


    @DeleteMapping("/job-listing/delete/{id}")
    @ResponseBody
    public boolean jobListingDelete(@PathVariable("id") long id){
        jobListingRepository.deleteById(id);
        return true;
    }

}
