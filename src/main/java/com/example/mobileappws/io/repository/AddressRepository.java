package com.example.mobileappws.io.repository;

import com.example.mobileappws.io.entity.AddressEntity;
import com.example.mobileappws.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Long> {
    List<AddressEntity> findAllByUserDetails(UserEntity userEntity);

    AddressEntity findAllByAddressID(String addressID);
}
