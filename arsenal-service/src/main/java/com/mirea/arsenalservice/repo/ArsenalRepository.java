package com.mirea.arsenalservice.repo;

import com.mirea.arsenalservice.model.Arsenal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArsenalRepository extends JpaRepository<Arsenal, Long> {
    List<Arsenal> findBygunCodeIn(List<String> gunCode);

}
