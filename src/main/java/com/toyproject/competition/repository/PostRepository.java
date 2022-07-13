package com.toyproject.competition.repository;

import com.toyproject.competition.domain.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);

    @Query("SELECT p from Post p where p.title LIKE %:title%")
    Page<Post> findByTitleLike(@Param("title") String title, Pageable pageable);
}
