package ApiToDoList.ToDoList.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import ApiToDoList.ToDoList.entitiy.Todo;
import ApiToDoList.ToDoList.service.TodoService;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    // Injeção via construtor (melhor prática)
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public List<Todo> create(@RequestBody Todo todo) {
        return todoService.create(todo);
    }

    @GetMapping
    public List<Todo> list() {
        return todoService.list();
    }

    @PutMapping("/{id}")
    public List<Todo> update(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.update(id, todo);
    }

    @DeleteMapping("/{id}")
    public List<Todo> delete(@PathVariable Long id) {
        return todoService.delete(id);
    }
}
