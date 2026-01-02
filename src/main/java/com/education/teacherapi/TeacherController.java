package com.education.teacherapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping
    public List<Teacher> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Teacher getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Teacher create(@RequestBody Teacher teacher) {
        return service.save(teacher);
    }

    @PutMapping("/{id}")
    public Teacher update(@PathVariable Long id, @RequestBody Teacher teacher) {

    	Teacher existing = service.getById(id);

        if (existing == null) {
            return null; 
        }

        
        existing.setName(teacher.getName());
        existing.setQualification(teacher.getQualification());
        existing.setSubject(teacher.getSubject());
        existing.setExperienceYears(teacher.getExperienceYears());

        return service.save(existing);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Teacher deleted and IDs resequenced.";
    }
}
