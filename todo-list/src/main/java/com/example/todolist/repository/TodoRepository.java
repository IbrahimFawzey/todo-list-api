package com.example.todolist.repository;

import com.example.todolist.domain.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    @Query(value = "SELECT * FROM todos WHERE is_deleted = false ORDER BY created_at DESC",
            nativeQuery = true)
    List<Todo> findAllActiveTodos();


    @Query(value = "SELECT * FROM todos WHERE status = :status AND is_deleted = false",
            nativeQuery = true)
    List<Todo> findByStatus(@Param("status") String status);


    @Query(value = "SELECT * FROM todos WHERE priority = :priority AND is_deleted = false ORDER BY due_date ASC",
            nativeQuery = true)
    List<Todo> findByPriority(@Param("priority") String priority);
}
