package com.john21121.GlobantUniversityTesis.repository;

import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {



}
