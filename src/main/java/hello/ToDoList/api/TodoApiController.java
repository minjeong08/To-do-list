package hello.ToDoList.api;

import hello.ToDoList.domain.Todo;
import hello.ToDoList.service.TodoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TodoApiController {

    private final TodoService todoService;

    @PostMapping("/api/{id}/status")
    public UpdateStatusResponse updateStatus(@PathVariable("id") Long id,
                                             @RequestBody @Valid UpdateStatusRequest request) {
        todoService.updateStatus(id);
        Todo findTodo = todoService.findOne(id);
        return new UpdateStatusResponse(findTodo.getId(), findTodo.getCompleted());
    }


    @Data
    static class UpdateStatusRequest {
        private Boolean completed;
    }

    @Data
    @AllArgsConstructor
    static class UpdateStatusResponse {
        private Long id;
        private Boolean completed;
    }
}
