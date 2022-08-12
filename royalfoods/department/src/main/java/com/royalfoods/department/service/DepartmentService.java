package com.royalfoods.department.service;

import com.fasterxml.jackson.databind.*;
import com.royalfoods.department.model.dto.*;
import com.royalfoods.department.model.entity.*;
import com.royalfoods.department.repository.*;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.stereotype.*;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.stream.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {
    final DepartmentRepository departmentRepository;

    public DepartmentDto createNewDepartment(DepartmentDto departmentDto) {
        AtomicReference<Department> departmentRef = new AtomicReference<>();
        departmentRepository
                .findByDepartName(departmentDto.getDepartName())
                .ifPresentOrElse((e)->{throw new EntityExistsException(String.format("Department by name %S already exists in system and id is %S",e.getDepartName(),e.getId()));},
                        ()->convertValues(departmentRef,departmentDto,Department.class) );
        Department department = departmentRepository.save(departmentRef.get());
        return departmentDto;
    }

    public List<DepartmentDto> getAllDepartments() {
        List<DepartmentDto> departments = new ArrayList<>();
        departmentRepository.findAll().stream().forEach(e->{
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

            DepartmentDto department = mapper.convertValue(e, DepartmentDto.class);
            departments.add(department);
        });
        return departments;
    }

    public DepartmentDto getDepartment(Long id) {
        AtomicReference<DepartmentDto> department = new AtomicReference<>();
       departmentRepository.findById(id).ifPresentOrElse(
               e->convertValues(department,e,DepartmentDto.class),
               ()->{
                    log.info("Department not found for given department id {}",id);
                     throw new EntityNotFoundException(String.format("Entity not found corresponding to given id %S",id));
                });
       return department.get();
    }

    public List<DepartmentDto> getAllDepartments(List<String> deptNames) {
      return departmentRepository
              .findByDepartNameIn(deptNames)
              .stream().map(e->convertValues(e,DepartmentDto.class))
              .collect(Collectors.toList());
    }

    private <T,S> void convertValues(AtomicReference<T> aRef, S src, Class<T> dest) {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        aRef.set(mapper.convertValue(src,dest));

    }

    private <T,S> T convertValues(S src, Class<T> dest) {
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
         return mapper.convertValue(src,dest);
    }

}
