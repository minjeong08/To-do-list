package hello.ToDoList.api;

import hello.ToDoList.domain.Todo;
import hello.ToDoList.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
public class TodoApiController {

    private final TodoService todoService;

    @PostMapping("/api/{id}/status")
    public UpdateStatusResponse updateStatus(@PathVariable("id") Long id) {
        todoService.updateStatus(id);
        Todo findTodo = todoService.findOne(id);
        return new UpdateStatusResponse(findTodo.getId(), findTodo.getCompleted());
    }

    @PostMapping("/api/{id}/delete")
    public String deleteTodo(@PathVariable("id") Long id) {
        todoService.deleteTodo(id);
        return "데이터가 성공적으로 삭제되었습니다";
    }

    @Data
    @AllArgsConstructor
    static class UpdateStatusResponse {
        private Long id;
        private Boolean completed;
    }
}
