package hello.ToDoList.web;

import hello.ToDoList.domain.Todo;
import hello.ToDoList.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping(value = "/")
    public String createForm(Model model) {

        List<Todo> todos = todoService.findTodos();

        model.addAttribute("todos", todos);
        model.addAttribute("todoForm", new TodoForm());
        return "home";
    }

    @PostMapping(value = "/new")
    public String create(@Valid TodoForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            List<Todo> todos = todoService.findTodos();

            model.addAttribute("todos", todos);
            return "home";
        }

        Todo todo = new Todo();
        todo.setTask(form.getTask());
        todo.setCreatedDate(LocalDateTime.now());
        todoService.saveTodo(todo);

        return "redirect:/";
    }

    @GetMapping(value = "/{id}")
    public String UpdateTaskForm(@PathVariable("id") Long id, Model model) {
        Todo todo = todoService.findOne(id);

        TodoForm form = new TodoForm();

        form.setTask(todo.getTask());

        model.addAttribute("form", form);
        model.addAttribute("todoId", id);
        return "to-do";
    }

    @PostMapping(value = "/{id}")
    public String UpdateTask(@PathVariable("id") Long id, @ModelAttribute("form") @Valid TodoForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "to-do";
        }

        todoService.updateTask(id, form.getTask());
        return "redirect:/";
    }
}
