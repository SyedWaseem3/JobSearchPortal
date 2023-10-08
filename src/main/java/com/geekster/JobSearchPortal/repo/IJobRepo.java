package com.geekster.JobSearchPortal.repo;

import com.geekster.JobSearchPortal.model.Job;
import com.geekster.JobSearchPortal.model.Type;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IJobRepo extends CrudRepository<Job, Long> {
    List<Job> findByJobSalary(Double salary);

    List<Job> findByJobTypeAndJobSalaryLessThanEqual(Type type, Double salary);

    List<Job> findByJobTypeAndJobSalaryLessThanEqualOrderByJobSalaryDesc(Type type, Double salary);

    List<Job> findByJobTypeAndJobAppliedDateAfter(Type type, LocalDate date);

    @Modifying
    @Query(value = "UPDATE JOB SET JOB_SALARY = (JOB_SALARY + JOB_SALARY * (:hike) WHERE JOB_TYPE = :name", nativeQuery = true)
    void updateSalaryByType(String name, float hike);

    @Modifying
    @Query(value = "UPDATE JOB SET JOB_SALARY = JOB_SALARY + :increment WHERE JOB_SALARY = :salary", nativeQuery = true)
    void updateSalaryByWhereSalary(Double increment, Double salary);

    @Modifying
    @Query(value = "DELETE FROM JOBS WHERE JOB_TYPE = :type", nativeQuery = true)
    void deleteJobByType(String type);

    @Modifying
    @Query(value = "DELETE FROM JOBS WHERE JOB_SALARY = :salary", nativeQuery = true)
    void deleteJobBySalary(Double salary);
}
