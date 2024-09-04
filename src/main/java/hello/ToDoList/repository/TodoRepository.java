package hello.ToDoList.repository;

import hello.ToDoList.domain.Todo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final EntityManager em;

    public void save(Todo todo) {
        if (todo.getId() == null) {
            em.persist(todo);
        } else {
            em.merge(todo);
        }
    }

    public Todo findOne(Long id) {
        return em.find(Todo.class, id);
    }

    public List<Todo> findAll() {
        return em.createQuery(
                "select t from Todo t", Todo.class)
                .getResultList();
    }
}
