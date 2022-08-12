package com.royalfood.user.controller;

import com.royalfood.user.model.dto.*;
import com.royalfood.user.service.*;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserApi {

    final private UserService userService;
    @GetMapping("welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to user services");
    }


    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto<String> userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> filterByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(userService.filterUserByEmail(email));
    }
}
