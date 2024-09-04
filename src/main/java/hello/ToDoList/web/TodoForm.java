package hello.ToDoList.web;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TodoForm {

    @NotEmpty(message = "필수 입력 사항입니다.")
    private String task;
}
