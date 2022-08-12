package com.royalfood.user.repository;

import com.royalfood.user.model.entity.*;
import jdk.jfr.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
