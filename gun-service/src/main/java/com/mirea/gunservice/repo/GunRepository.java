package com.mirea.gunservice.repo;

import com.mirea.gunservice.model.Gun;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GunRepository extends MongoRepository<Gun,String> {

}
