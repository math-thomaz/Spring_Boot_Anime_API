package com.maththomaz.animeapibydevdojo.repository;

import com.maththomaz.animeapibydevdojo.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

    List<Anime> findByName(String name);
}
