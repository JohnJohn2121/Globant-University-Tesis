package com.john21121.GlobantUniversityTesis.repository;

import com.john21121.GlobantUniversityTesis.mailingsystem.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {



}
