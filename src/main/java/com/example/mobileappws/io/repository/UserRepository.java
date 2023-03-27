package com.example.mobileappws.io.repository;


import com.example.mobileappws.io.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);

    UserEntity findByEmailVerificationToken(String token);

    @Query(value = "SELECT * FROM dbo.users\n" +
            "Where email_verification_status = 1", countQuery = "SELECT COUNT(*) FROM dbo.users\n" +
            "Where email_verification_status = 1", nativeQuery = true)
    Page<UserEntity> findAllUsersWithConfirmedEmailAddress(Pageable pageableRequest);

    // Positional Parameter
    @Query(value = "SELECT * FROM dbo.users AS u WHERE u.first_name = ?1", nativeQuery = true)
    List<UserEntity> findUserByFirstName(String firstName);

    // Named Parameter
    @Query(value = "SELECT * FROM dbo.users AS u WHERE u.last_name = :lastName", nativeQuery = true)
    List<UserEntity> findUserByLastName(@Param("lastName") String lastName);

    @Query(value = "SELECT * FROM dbo.users AS u WHERE u.first_name LIKE %:keyword% OR u.last_name LIKE %:keyword%", nativeQuery = true)
    List<UserEntity> findUserByKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT u.first_name, u.last_name FROM dbo.users AS u WHERE u.first_name LIKE %:keyword% OR u.last_name LIKE %:keyword%", nativeQuery = true)
    List<Object[]> findUserFullNameByKeyword(@Param("keyword") String keyword);


    @Transactional
    @Modifying
    @Query(value = "UPDATE dbo.users SET email_verification_status = :emailVerificationStatus WHERE user_id = :userID", nativeQuery = true)
    void updateUserEmailVerificationStatus(@Param("emailVerificationStatus") boolean emailVerificationStatus,
                                           @Param("userID") String userID);

}
