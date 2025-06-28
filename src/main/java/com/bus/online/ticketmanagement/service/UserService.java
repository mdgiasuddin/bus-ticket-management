package com.bus.online.ticketmanagement.service;

import com.bus.online.ticketmanagement.model.dto.request.CounterMasterRequest;
import com.bus.online.ticketmanagement.model.dto.request.UserCreateRequest;
import com.bus.online.ticketmanagement.model.entity.User;
import com.bus.online.ticketmanagement.repository.TicketCounterRepository;
import com.bus.online.ticketmanagement.repository.UserRepository;
import com.bus.online.ticketmanagement.util.RandomStringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.bus.online.ticketmanagement.model.enumeration.Role.COUNTER_MASTER;
import static com.bus.online.ticketmanagement.model.enumeration.Role.DATA_ENTRY;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TicketCounterRepository ticketCounterRepository;
    private final PasswordEncoder passwordEncoder;

    //    @PostConstruct
    public void createAdmin() {
        User user = new User();
        user.setName("Giash Uddin");
        user.setUsername("data");
        user.setPassword(passwordEncoder.encode("data"));
        user.setMobileNumber("012345678");
        user.setNid("012345678");
        user.setRole(DATA_ENTRY);
        userRepository.save(user);

//        Random random = new SecureRandom();
//        byte[] bytes = new byte[64];
//        random.nextBytes(bytes);
//        System.out.println("key: " + String.copyValueOf(Hex.encode(bytes)));
    }

    public User createCounterMaster(CounterMasterRequest request) {
        String randomPassword = RandomStringUtil.randomString(10);

        User counterMaster = new User();
        counterMaster.setName(request.name());
        counterMaster.setUsername(request.username());
        counterMaster.setPassword(passwordEncoder.encode(randomPassword));
        counterMaster.setAddress(request.address());
        counterMaster.setMobileNumber(request.mobileNumber());
        counterMaster.setNid(request.nid());
        counterMaster.setActive(false);
        counterMaster.setPasswordExpiryDate(LocalDateTime.now());
        counterMaster.setRole(COUNTER_MASTER);

        // TODO: send the password to the counter master via email or sms.
        log.info("Password: {}", randomPassword);

        return userRepository.save(counterMaster);
    }

    public void createNewUser(UserCreateRequest request) {
        User user = userFromCreateRequest(request);
        user.setPassword(passwordEncoder.encode(request.password()));
//        if (request.role().equals(COUNTER_MASTER)) {
//            TicketCounter ticketCounter = ticketCounterRepository.findById(request.ticketCounterId())
//                    .orElseThrow(() -> new ResourceNotFoundException(TICKET_COUNTER_NOT_FOUND, ""));
//            user.setTicketCounter(ticketCounter);
//        }
        userRepository.save(user);
    }

    private User userFromCreateRequest(UserCreateRequest userCreateRequest) {
        User user = new User();

        user.setName(userCreateRequest.name());
        user.setUsername(userCreateRequest.username());
        user.setPassword(userCreateRequest.password());
        user.setMobileNumber(userCreateRequest.mobileNumber());
        user.setNid(userCreateRequest.nid());
        user.setRole(userCreateRequest.role());

        return user;
    }
}
