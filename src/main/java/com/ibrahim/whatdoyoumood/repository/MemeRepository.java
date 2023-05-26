package com.ibrahim.whatdoyoumood.repository;

import com.ibrahim.whatdoyoumood.model.Meme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemeRepository extends JpaRepository<Meme, Long> {
    Optional<Meme> findByName(String name);

}
