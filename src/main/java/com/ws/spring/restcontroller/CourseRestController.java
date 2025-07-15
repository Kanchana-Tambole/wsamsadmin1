// CourseRestController.java
package com.ws.spring.restcontroller;

import com.ws.spring.dto.CourseDto;
import com.ws.spring.exception.ClientResponseBean;
import com.ws.spring.service.CourseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseRestController {

    @Autowired
    CourseServiceImpl courseService;

    @PostMapping("/v1/create")
    public ResponseEntity<ClientResponseBean> create(@RequestBody CourseDto dto) {
        courseService.createCourse(dto);
        return ResponseEntity.ok(new ClientResponseBean(HttpStatus.CREATED.value(), "SUCCESS", "Course created", ""));
    }

    @PutMapping("/v1/update")
    public ResponseEntity<ClientResponseBean> update(@RequestBody CourseDto dto) {
        courseService.updateCourse(dto);
        return ResponseEntity.ok(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS", "Course updated", ""));
    }

    @DeleteMapping("/v1/delete/{id}")
    public ResponseEntity<ClientResponseBean> delete(@PathVariable long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.ok(new ClientResponseBean(HttpStatus.OK.value(), "SUCCESS", "Course deleted", ""));
    }

    @GetMapping("/v1/get/{id}")
    public ResponseEntity<CourseDto> get(@PathVariable long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @GetMapping("/v1/list")
    public ResponseEntity<List<CourseDto>> list() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/v1/paginated")
    public Page<CourseDto> paginated(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return courseService.getAllCoursesByPagination(pageNumber, pageSize);
    }
}
