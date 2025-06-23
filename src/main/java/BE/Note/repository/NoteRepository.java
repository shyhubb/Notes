package BE.Note.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import BE.Note.entities.Note;
import BE.Note.entities.User;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    public List<Note> findByUser(User user);

    public void deleteByUser(User user);
}
