package com.royalfoods.department.controller;

import com.royalfoods.department.model.dto.*;
import com.royalfoods.department.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentApi {
   private final DepartmentService departmentService;
    @PostMapping
    public ResponseEntity<?> createDepart(@RequestBody DepartmentDto payload) {
     return ResponseEntity
             .status(HttpStatus.CREATED)
             .body(departmentService.createNewDepartment(payload));
    }

    @GetMapping
    public ResponseEntity<?> getDepartments(){
         return ResponseEntity.status(HttpStatus.OK).body(departmentService.getAllDepartments());
    }
    

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> departmentById(@PathVariable("id") Long id) {
     return ResponseEntity.status(HttpStatus.OK).body(departmentService.getDepartment(id));
    }

    @PostMapping("/byNames")
   public ResponseEntity<List<DepartmentDto>> departmentsByNames(@RequestBody List<String> deptNames) {
     return ResponseEntity.ok(departmentService.getAllDepartments(deptNames));
    }

}
