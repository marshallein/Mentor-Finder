/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.service;

import com.abc.WebApp2.repository.EnrolledRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.abc.WebApp2.entity.Enrolled;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class EnrollService {

    @Autowired
    EnrolledRepository repo;

    public List<Enrolled> getMyEnrolled(Integer uId) {
        return repo.findByMentorId(uId);
    }

}
