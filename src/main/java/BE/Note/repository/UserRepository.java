package BE.Note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import BE.Note.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByAccount(String account);

}
