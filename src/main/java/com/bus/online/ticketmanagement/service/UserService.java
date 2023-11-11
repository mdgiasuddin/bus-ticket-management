package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.exception.ResourceNotFoundException;
import com.bus.online.ticketmanagement.mapper.UserMapper;
import com.bus.online.ticketmanagement.model.dto.request.UserCreateRequest;
import com.bus.online.ticketmanagement.model.entity.TicketCounter;
import com.bus.online.ticketmanagement.model.entity.User;
import com.bus.online.ticketmanagement.model.enums.Role;
import com.bus.online.ticketmanagement.repository.TicketCounterRepository;
import com.bus.online.ticketmanagement.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

import static com.bus.online.ticketmanagement.constant.ExceptionConstant.TICKET_COUNTER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TicketCounterRepository ticketCounterRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    //    @PostConstruct
    public void createAdmin() {
//        User user = new User();
//        user.setName("Giash Uddin");
//        user.setUsername("admin");
//        user.setPassword(passwordEncoder.encode("admin"));
//        user.setMobileNumber("012345678");
//        user.setNid("012345678");
//        user.setRole(Role.AD);
//        userRepository.save(user);

        Random random = new SecureRandom();
        byte[] bytes = new byte[64];
        random.nextBytes(bytes);
        System.out.println("key: " + String.copyValueOf(Hex.encode(bytes)));
    }

    public void createNewUser(UserCreateRequest request) {
        User user = userMapper.userFromCreateRequest(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        if (request.role().equals(Role.CM)) {
            TicketCounter ticketCounter = ticketCounterRepository.findById(request.ticketCounterId())
                    .orElseThrow(() -> new ResourceNotFoundException(TICKET_COUNTER_NOT_FOUND));
            user.setTicketCounter(ticketCounter);
        }
        userRepository.save(user);
    }
}
