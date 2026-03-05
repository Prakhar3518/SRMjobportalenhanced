package com.jobportal.srm.specification;

import com.jobportal.srm.entity.Job;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecification {

    // filter by location
    public static Specification<Job> hasLocation(String location) {

        return (root, query, criteriaBuilder) ->
                location == null ? null :
                        criteriaBuilder.equal(root.get("location"), location);
    }

    // filter by required skills
    public static Specification<Job> hasSkill(String skill) {

        return (root, query, criteriaBuilder) ->
                skill == null ? null :
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("requiredSkills")),
                                "%" + skill.toLowerCase() + "%"
                        );
    }

    // filter by cgpa requirement
    public static Specification<Job> hasCgpa(Double cgpa) {

        return (root, query, criteriaBuilder) ->
                cgpa == null ? null :
                        criteriaBuilder.lessThanOrEqualTo(
                                root.get("minCgpa"),
                                cgpa
                        );
    }
}