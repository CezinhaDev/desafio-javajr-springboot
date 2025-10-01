package ApiToDoList.ToDoList;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import ApiToDoList.ToDoList.entitiy.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("/remove.sql")
class TodolistApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    // mocks para representar dados carregados via import.sql
    private static final Todo TODO = new Todo(1L, "todo 1", "desc todo 1", false, 1);
    private static final List<Todo> TODOS = List.of(
            new Todo(1L, "todo 1", "desc todo 1", false, 1),
            new Todo(2L, "todo 2", "desc todo 2", true, 2),
            new Todo(3L, "todo 3", "desc todo 3", false, 3),
            new Todo(4L, "todo 4", "desc todo 4", true, 4),
            new Todo(5L, "todo 5", "desc todo 5", false, 5));

    @Test
    void testCreateTodoSuccess() {
        var todo = new Todo("todo 1", "desc todo 1", false, 1);

        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].nome").isEqualTo(todo.getNome())
                .jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
                .jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
                .jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
    }

    @Test
    public void testCreateTodoFailure() {
        webTestClient
                .post()
                .uri("/todos")
                .bodyValue(new Todo("", "", false, 0))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Sql("/import.sql")
    @Test
    public void testUpdateTodoSuccess() {
        var todo = new Todo(
                TODO.getId(),
                TODO.getNome() + " Up",
                TODO.getDescricao() + " Up",
                !TODO.isRealizado(),
                TODO.getPrioridade() + 1);

        webTestClient
                .put()
                .uri("/todos/" + TODO.getId())
                .bodyValue(todo)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(5)
                .jsonPath("$[0].nome").isEqualTo(todo.getNome())
                .jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
                .jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
                .jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
    }

    @Test
    public void testUpdateTodoFailure() {
        var unexinstingId = 1L;

        webTestClient
                .put()
                .uri("/todos/" + unexinstingId)
                .bodyValue(new Todo(unexinstingId, "", "", false, 0))
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Sql("/import.sql")
    @Test
    public void testDeleteTodoSuccess() {
        webTestClient
                .delete()
                .uri("/todos/" + TODOS.get(0).getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(4)
                .jsonPath("$[0].nome").isEqualTo(TODOS.get(1).getNome())
                .jsonPath("$[0].descricao").isEqualTo(TODOS.get(1).getDescricao())
                .jsonPath("$[0].realizado").isEqualTo(TODOS.get(1).isRealizado())
                .jsonPath("$[0].prioridade").isEqualTo(TODOS.get(1).getPrioridade());
    }

    @Test
    public void testDeleteTodoFailure() {
        var unexinstingId = 1L;

        webTestClient
                .delete()
                .uri("/todos/" + unexinstingId)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Sql("/import.sql")
    @Test
    public void testListTodos() throws Exception {
        webTestClient
                .get()
                .uri("/todos")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(5)
                .jsonPath("$[0].nome").isEqualTo(TODOS.get(0).getNome())
                .jsonPath("$[1].nome").isEqualTo(TODOS.get(1).getNome())
                .jsonPath("$[2].nome").isEqualTo(TODOS.get(2).getNome())
                .jsonPath("$[3].nome").isEqualTo(TODOS.get(3).getNome())
                .jsonPath("$[4].nome").isEqualTo(TODOS.get(4).getNome());
    }
}
