package com.bkizilkaya.culturelbackend.repo;

import com.bkizilkaya.culturelbackend.model.Artwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

}
