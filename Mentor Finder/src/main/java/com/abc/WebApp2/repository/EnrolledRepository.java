/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.WebApp2.repository;

import com.abc.WebApp2.entity.Enrolled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EnrolledRepository extends JpaRepository<Enrolled, Long> {
    
}
