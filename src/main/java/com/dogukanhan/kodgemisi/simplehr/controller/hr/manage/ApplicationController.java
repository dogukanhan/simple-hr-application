package com.dogukanhan.kodgemisi.simplehr.controller.hr.manage;

import com.dogukanhan.kodgemisi.simplehr.repository.JobApplicationRepository;
import com.dogukanhan.kodgemisi.simplehr.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;

@Controller
@RequestMapping("/hr/manage/application")
public class ApplicationController {

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/detail/{id}")
    public String applicationDetail(@PathVariable("id") long id, Model model){
        model.addAttribute("jobApplication",jobApplicationRepository.findById(id).get());
        return "/hr/manage/application/detail";
    }

    @GetMapping("/list")
    public String listApplications(Model model){
        model.addAttribute("jobApplications",jobApplicationRepository.findAll());
        return "/hr/manage/application/list";
    }

    @GetMapping("/cv/{fileName}")
    @ResponseBody
    public Resource getCv(@PathVariable("fileName") String fileName, HttpServletResponse httpServletResponse) throws MalformedURLException {
            httpServletResponse.addHeader("Content-Disposition","attachment;filename="+fileName);
            httpServletResponse.setHeader("Content-Type","application");
            return fileStorageService.loadFileAsResource(fileName);
    }
}
