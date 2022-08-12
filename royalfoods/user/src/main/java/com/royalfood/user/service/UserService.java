package com.royalfood.user.service;

import com.fasterxml.jackson.databind.*;
import com.royalfood.user.model.dto.*;
import com.royalfood.user.model.entity.*;
import com.royalfood.user.model.vo.*;
import com.royalfood.user.repository.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.web.client.*;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.atomic.*;

@RequiredArgsConstructor
@Service
public class UserService {
    final private UserRepository userRepository;
    final private RestTemplate restTemplate;
    final private String HOST = "localhost";
    final private String DEPT_PORT = "1010";
    final private String DEPT_URL = "/api/department";


    public UserDto createUser(UserDto userDto) {
        AtomicReference<User> userAtomic = new AtomicReference<>();
        userRepository.findByEmail(userDto.getEmail()).ifPresentOrElse((e)->{throw new EntityExistsException("Invalid");},
        ()->{
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            userAtomic.set(mapper.convertValue(userDto,User.class));
        });
        User user = userRepository.save(userAtomic.get());
        return userDto;
    }

    public UserDto filterUserByEmail(String email) {
        AtomicReference<UserDto> userAtomic = new AtomicReference<>();
        userRepository.findByEmail(email).ifPresentOrElse((e)->{
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
            UserDto userDto = mapper.convertValue(e,UserDto.class);
            userAtomic.set(userDto);
        },()->{
            throw new EntityNotFoundException(String
                    .format("User not found for given email %S"
                            ,email));
        });
        UserDto<DepartmentVo> userDto = userAtomic.get();
        String departmentService = String.format("http://%s:%s%s/byNames",HOST,DEPT_PORT,DEPT_URL);
        List<DepartmentVo> departments = restTemplate
                .postForObject(departmentService,userDto.getDepartments(),
                        List.class);
        userDto.setDepartments(departments);
        return userDto;
    }
}
