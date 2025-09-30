package ApiToDoList.ToDoList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ApiToDoList.ToDoList.entitiy.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> { // interface do Spring data jpa (conexao ao banco de dados)
    
}
