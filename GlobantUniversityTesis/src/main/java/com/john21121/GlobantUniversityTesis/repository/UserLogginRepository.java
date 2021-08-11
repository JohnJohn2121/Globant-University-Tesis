package com.john21121.GlobantUniversityTesis.repository;

import com.john21121.GlobantUniversityTesis.userloggin.UserLogin;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UserLogginRepository extends CrudRepository<UserLogin,Long> {

}
