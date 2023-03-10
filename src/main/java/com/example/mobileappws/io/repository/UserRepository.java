package com.example.mobileappws.io.repository;


import com.example.mobileappws.io.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);

    UserEntity findByEmailVerificationToken(String token);

    @Query(value = "SELECT * FROM dbo.users\n" +
            "Where email_verification_status = 1", countQuery = "SELECT COUNT(*) FROM dbo.users\n" +
            "Where email_verification_status = 1", nativeQuery = true)
    Page<UserEntity> findAllUsersWithConfirmedEmailAddress(Pageable pageableRequest);


}
