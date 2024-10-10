package com.example.sample.user.repository;
import com.example.sample.user.model.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long>, JpaSpecificationExecutor<ProfileEntity> {

    @Query("SELECT p from ProfileEntity p where p.id = ?1")
    Optional<ProfileEntity> findByUserId(Long id);

}
