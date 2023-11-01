package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.mapper.UserMapper;
import com.bus.online.ticketmanagement.model.dto.request.UserCreateRequest;
import com.bus.online.ticketmanagement.model.entity.User;
import com.bus.online.ticketmanagement.model.enums.Role;
import com.bus.online.ticketmanagement.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

//    @PostConstruct
    public void createAdmin() {
        User user = new User();
        user.setName("Giash Uddin");
        user.setUsername("admin");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setMobileNumber("012345678");
        user.setNid("012345678");
        user.setRole(Role.AD);
        userRepository.save(user);
    }

    public void createNewUser(UserCreateRequest request) {
        User user = userMapper.userFromCreateRequest(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }
}
