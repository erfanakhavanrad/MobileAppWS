package com.example.mobileappws.io.repository;

import com.example.mobileappws.io.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Erfan Akhavan Rad
 * @created 04/02/2023
 */
@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
