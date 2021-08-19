package com.john21121.GlobantUniversityTesis.repository;

import com.john21121.GlobantUniversityTesis.mailingsystem.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface RecipientRepository extends JpaRepository<Recipient,Long> {

}
