package com.education.teacherapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository repo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TeacherService(TeacherRepository repo) {
        this.repo = repo;
    }

    public List<Teacher> getAll() {
        return repo.findAll();
    }

    public Teacher getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Teacher save(Teacher teacher) {
        return repo.save(teacher);
    }

    public void delete(Long id) {
        repo.deleteById(id);

        
       jdbcTemplate.execute("EXEC reset_teacher_ids");
    }
}
