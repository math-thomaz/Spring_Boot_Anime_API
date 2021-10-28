package com.maththomaz.animeapibydevdojo.repository;

import com.maththomaz.animeapibydevdojo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
