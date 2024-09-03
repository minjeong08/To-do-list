package hello.ToDoList.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Todo {

    @Id @GeneratedValue
    private Long id;

    private LocalDateTime createdDate;

    private String task;
    private boolean completed;
}
