package com.geekster.JobSearchPortal.service;

import com.geekster.JobSearchPortal.model.Job;
import com.geekster.JobSearchPortal.model.Type;
import com.geekster.JobSearchPortal.repo.IJobRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JobService {

    @Autowired
    IJobRepo jobRepo;

    public String addJob(Job myjob) {
        jobRepo.save(myjob);
        return myjob.getJobTitle() + " job added!";
    }


    public List<Job> getJobs() {
        return (List<Job>) jobRepo.findAll();
    }

    public String addJobs(List<Job> myJobs) {
        jobRepo.saveAll(myJobs);
        return myJobs.size() + " jobs were added!";
    }

    public String deleteJob(Long id) {
        jobRepo.deleteById(id);
        return id + " job deleted!";
    }

    public String deleteJobsByList(List<Long> ids) {
        jobRepo.deleteAllById(ids);
        return ids.size() + " jobs were deleted!";
    }

    public Job getJobById(Long id) {
        return jobRepo.findById(id).get();
    }

    public List<Job> getJobsBySalary(Double salary) {
        return jobRepo.findByJobSalary(salary);
    }

    public List<Job> getJobsBySalaryAndType(Type type, Double salary) {
        return jobRepo.findByJobTypeAndJobSalaryLessThanEqual(type, salary);
    }

    public List<Job> getJobsBySalaryAndTypeOrderBy(Type type, Double salary) {
        return jobRepo.findByJobTypeAndJobSalaryLessThanEqualOrderByJobSalaryDesc(type, salary);
    }

    public List<Job> getJobsByTypeAndLocalAppliedDate(Type type, LocalDate date) {
        return jobRepo.findByJobTypeAndJobAppliedDateAfter(type, date);
    }

    public String updateSalaryById(Long id, Double salary) {
        Job myJob = jobRepo.findById(id).orElse(null);
        if(myJob != null){
            myJob.setJobSalary(salary);
            jobRepo.save(myJob);
            return myJob.getJobId() + "'s salary updated!";
        }
        return "Job not found...";
    }

    @Transactional
    public String updateSalaryByType(Type type, float hike) {
        jobRepo.updateSalaryByType(type.name(), hike);
        return type +"s hike was updated!";
    }

    @Transactional
    public String updateSalaryByWhereSalary(Double increment, Double salary) {
        jobRepo.updateSalaryByWhereSalary(increment, salary);
        return "Incremented!";
    }

    @Transactional
    public String deleteJobByType(Type type) {
        jobRepo.deleteJobByType(type.name());
        return "Jobs deleted by type!";
    }

    @Transactional
    public String deleteJobBySalary(Double salary) {
        jobRepo.deleteJobBySalary(salary);
        return "Jobs Deleted by salary!";
    }
}
