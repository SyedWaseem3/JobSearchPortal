package com.geekster.JobSearchPortal.controller;

import com.geekster.JobSearchPortal.model.Job;
import com.geekster.JobSearchPortal.model.Type;
import com.geekster.JobSearchPortal.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
public class JobController {

    @Autowired
    JobService jobService;

    //Post job
    @PostMapping("job")
    public String addJob(@RequestBody @Valid Job myjob){
        return jobService.addJob(myjob);
    }

    //Get Jobs
    @GetMapping("jobs")
    public List<Job> getJobs(){
        return jobService.getJobs();
    }

    //Get Job by id :
    @GetMapping("job/id/{id}")
    public Job getJobById(@PathVariable Long id){
        return jobService.getJobById(id);
    }

    //Get jobs by salary
    @GetMapping("jobs/price/{salary}")
    public List<Job> getJobsBySalary(@PathVariable Double salary){
        return jobService.getJobsBySalary(salary);
    }

    //Get Jobs by salary less than and job type
    @GetMapping("jobs/type/{type}/salary/{salary}")
    public List<Job> getJobsBySalaryAndType(@PathVariable Type type, @PathVariable Double salary){
        return jobService.getJobsBySalaryAndType(type, salary);
    }

    //Get Jobs by type and salary less than and order by desc
    @GetMapping("jobs/type/{type}/salary/{salary}/orderBy")
    public List<Job> getJobsBySalaryAndTypeOrderBy(@PathVariable Type type, @PathVariable Double salary){
        return jobService.getJobsBySalaryAndTypeOrderBy(type, salary);
    }

    //Get Jobs by type and localDate after certain date
    @GetMapping("jobs/type/{type}/appliedDateAfter/{date}")
    public List<Job> getJobsByTypeAndLocalAppliedDate(@PathVariable Type type, @PathVariable LocalDate date){
        return jobService.getJobsByTypeAndLocalAppliedDate(type, date);
    }

    //Post List of Jobs
    @PostMapping("jobs")
    public String addJobs(@RequestBody @Valid List<Job> myJobs){
        return jobService.addJobs(myJobs);
    }

    //Update salary by id
    @PutMapping("updateJob/id/{id}/salary/{salary}")
    public String updateSalaryById(@PathVariable Long id, @PathVariable Double salary){
        return jobService.updateSalaryById(id, salary);
    }


    //Update salary for the type
    @PutMapping("updateSalary/type/{type}/hike/{hike}")
    public String updateSalaryByType(@PathVariable Type type, @PathVariable float hike){
        return jobService.updateSalaryByType(type, hike);
    }

    //Update job where salary = salary
    @PutMapping("updateSalary/increment/{increment}/salary/{salary}")
    public String updateSalaryByWhereSalary(@PathVariable Double increment, @PathVariable Double salary){
        return jobService.updateSalaryByWhereSalary(increment, salary);
    }

    //Delete job by id
    @DeleteMapping("job/id/{id}")
    public String deleteJob(@PathVariable Long id){
        return jobService.deleteJob(id);
    }

    //Delete jobs by list
    @DeleteMapping("job/ids")
    public String deleteJobsByList(@RequestBody @Valid List<Long> ids){
        return jobService.deleteJobsByList(ids);
    }


    //Delete job type
    @DeleteMapping("deleteJob/type/{type}")
    public String deleteJobByType(@PathVariable Type type){
        return jobService.deleteJobByType(type);
    }


    //Delete job with salary
    @DeleteMapping("deleteJob/salary/{salary}")
    public String deleteJobBySalary(@PathVariable Double salary){
        return jobService.deleteJobBySalary(salary);
    }
}
