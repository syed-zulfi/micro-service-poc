package com.royalfoods.department.repository;

import com.royalfoods.department.model.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Optional<Department> findByDepartName(String name);

    List<Department> findByDepartNameIn(List<String> deptNames);
}
