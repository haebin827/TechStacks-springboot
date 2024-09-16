package edu.library.libraryspringboot.repository;

import edu.library.libraryspringboot.domain.User;
import edu.library.libraryspringboot.dto.UserDTO;
import edu.library.libraryspringboot.repository.search.UserSearch;
import edu.library.libraryspringboot.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface UserRepository extends JpaRepository<User, Integer>, UserSearch {

    @Query("select u from User u where u.uId = :uId and u.uPw = :uPw")
    User authenticate(@Param("uId") String uId, @Param("uPw") String uPw);

    @Modifying
    @Transactional
    @Query("update User set uUuid = :uUuid where uNo = :uNo")
    void updateUuid(@Param("uNo") int uNo, @Param("uUuid") String uUuid);

    User findByuUuid(String uUuid);

    long countByuPhone(String uPhone);

    @Query(value = "SELECT (SELECT COUNT(*) FROM User u WHERE u.u_id = :uId) + (SELECT COUNT(*) FROM deleted_user d WHERE d.u_id = :uId)", nativeQuery = true)
    int checkId(String uId);

    /*@Query("select u.uRegDate from User u left join RentalRequest r on u.uId = r.uId where u.uId = :uId and r.rId IS NULL")
    LocalDateTime deleteUserAfterSixMonths(String uId);*/
    @Query("select u.uRegDate from User u " +
            "left join RentalRequest rr on u.uId = rr.uId " +
            "left join Rental r on u.uId = r.uId " +
            "where u.uId = :uId " +
            "and rr.rId IS NULL " +
            "and r.rId IS NULL")
    LocalDateTime deleteUserAfterSixMonths(String uId);

    @Modifying
    @Transactional
    @Query("update User set uName = :uName, uPhone = :uPhone where uNo = :uNo")
    void updateAcct(@Param("uNo") int uNo, @Param("uName") String uName, @Param("uPhone") String uPhone);

    @Modifying
    @Transactional
    @Query("update User set uPw = :uPw where uNo = :uNo")
    void updatePw(@Param("uNo") int uNo, @Param("uPw") String uPw);

    @Modifying
    @Transactional
    @Query("update User set uNoOfLateReturn = uNoOfLateReturn + 1, uIsBlacklist = true where uId = :uId")
    void updateLateRtn(@Param("uId") String uId);

    @Modifying
    @Transactional
    @Query("update User set uLevel = :uLevel where uId = :uId")
    void updateLevel(@Param("uLevel") String uLevel, @Param("uId") String uId);

    @Modifying
    @Transactional
    @Query("update User set uPoint = uPoint + :uPoint where uId = :uId")
    void updatePoint(@Param("uPoint") int uPoint, @Param("uId") String uId);

    @Modifying
    @Transactional
    @Query("update User u set u.uIsLevelUp = case when u.uIsLevelUp = true then false else true end where u.uId = :uId")
    void updateLevelUp(String uId);

}