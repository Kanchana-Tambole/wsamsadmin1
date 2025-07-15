// CourseSubjectRestController.java
package com.ws.spring.restcontroller;

import com.ws.spring.dto.CourseSubjectDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.service.CourseSubjectServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseSubject")
public class CourseSubjectRestController {

    @Autowired
    CourseSubjectServiceImpl courseSubjectService;

    @PostMapping("/v1/create")
    public ResponseEntity<ClientResponseBean> create(@RequestBody CourseSubjectDto dto) {
        courseSubjectService.createCourseSubject(dto);
        return ResponseEntity.ok(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS", "Course subject created", ""));
    }

    @PutMapping("/v1/update")
    public ResponseEntity<ClientResponseBean> update(@RequestBody CourseSubjectDto dto) {
        courseSubjectService.updateCourseSubject(dto);
        return ResponseEntity.ok(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS", "Course subject updated", ""));
    }

    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<ClientResponseBean> delete(@PathVariable long id) {
        courseSubjectService.deleteCourseSubjectById(id);
        return ResponseEntity.ok(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS", "Course subject deleted", ""));
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<CourseSubjectDto> get(@PathVariable long id) {
        return ResponseEntity.ok(courseSubjectService.getCourseSubjectById(id));
    }

    @GetMapping("/v1/list")
    public ResponseEntity<List<CourseSubjectDto>> list() {
        return ResponseEntity.ok(courseSubjectService.getAllCourseSubjects());
    }

    @GetMapping("/v1/paginated")
    public Page<CourseSubjectDto> paginated(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return courseSubjectService.getAllCourseSubjectsByPagination(pageNumber, pageSize);
    }
}