package com.example.bibliotecaApp.repository;

import com.example.bibliotecaApp.entity.UserApp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserApp,Long> {

    @Query("SELECT u FROM UserApp u WHERE u.name LIKE %:name% AND u.lastname LIKE %:lastname%  AND u.dni LIKE %:dni% ")
    Page<UserApp> getUserBy(@Param("name") String name , @Param("lastname") String lastname, @Param("dni") String dni , Pageable pageable);

    @Query(value = "SELECT CASE WHEN EXISTS (" +
            "    SELECT * " +
            "    FROM lending " +
            "    WHERE user_id = :userId " +
            "     AND date_return IS NOT NULL)" +
            "THEN CAST(1 AS BIT)" +
            "ELSE CAST(0 AS BIT) END; " , nativeQuery = true)
    Boolean existLendingByUserId (@Param("userId") Long bookId);


    @Transactional
    @Modifying
    @Query (value = " DELETE FROM lendings WHERE user_id = : userId ; ", nativeQuery = true)
    void deleteLendingByUserId(@Param("userId") Long id);

    @Transactional
    @Modifying
    @Query (value = " DELETE FROM users WHERE id = : userId ; ", nativeQuery = true)
    void deleteUserId(@Param("userId") Long id);

    @Transactional
    @Modifying
    @Query (value = " DELETE FROM user_credentials WHERE id IN (SELECT u.user_credential_id FROM user u WHERE u.id = :userId) ; ", nativeQuery = true)
    void deleteUserCredentialId(@Param("userId") Long id);

    @Query(value = " SELECT u FROM UserApp u " +
            " JOIN FETCH u.userCredential uc " +
            " WHERE uc.username = :username ")
    UserApp getUserByUSername (@Param("username") String username);
}
