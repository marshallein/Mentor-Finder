package com.abc.WebApp2.repository;

import com.abc.WebApp2.entity.Comment;
import com.abc.WebApp2.entity.UserInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    
    List<Comment> findByUserReceived(UserInfo userReceived);
}
