package ApiToDoList.ToDoList.service;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

import ApiToDoList.ToDoList.entitiy.Todo;
import ApiToDoList.ToDoList.repository.TodoRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> list() {
        Sort sort = Sort.by(Direction.DESC, "prioridade")
                        .and(Sort.by(Direction.ASC, "id"));
        return todoRepository.findAll(sort);
    }

    public List<Todo> create(Todo todo) {
        todoRepository.save(todo);
        return list();
    }

    public List<Todo> update(Long id, Todo todo) {
        todoRepository.findById(id).ifPresentOrElse(existingTodo -> {
            todo.setId(id);
            todoRepository.save(todo);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Todo com id " + id + " não existe!");
        });
        return list();
    }

    public List<Todo> delete(Long id) {
        todoRepository.findById(id).ifPresentOrElse(existingTodo -> {
            todoRepository.delete(existingTodo);
        }, () -> {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Todo com id " + id + " não existe!");
        });
        return list();
    }
}
