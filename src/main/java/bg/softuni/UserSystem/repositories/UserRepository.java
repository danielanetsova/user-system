package bg.softuni.UserSystem.repositories;

import bg.softuni.UserSystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Short> {
    List<User> findUsersByEmailContaining(String s);

    @Query("FROM User WHERE DATE(lastTimeLoggedIn) < :date")
    List<User> getByLastTimeLoggedDateBeforeGivenDate(@Param("date") LocalDate date);
    List<User> findUsersByIsDeleted(Boolean b);
}
