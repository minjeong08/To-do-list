package hello.ToDoList.service;

import hello.ToDoList.domain.Todo;
import hello.ToDoList.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }

    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    public Todo findOne(Long todoId) {
        return todoRepository.findOne(todoId);
    }

    @Transactional
    public void updateTask(Long id, String task) {

        Todo todo = todoRepository.findOne(id);
        todo.setTask(task);
    }

    @Transactional
    public void updateStatus(Long id) {

        Todo todo = todoRepository.findOne(id);
        todo.setCompleted(!todo.getCompleted());
    }

    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.delete(id);
    }
}
