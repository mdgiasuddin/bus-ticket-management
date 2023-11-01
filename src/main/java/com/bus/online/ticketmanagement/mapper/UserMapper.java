package com.bus.online.ticketmanagement.mapper;

import com.bus.online.ticketmanagement.model.dto.request.UserCreateRequest;
import com.bus.online.ticketmanagement.model.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public interface UserMapper {

    User userFromCreateRequest(UserCreateRequest userCreateRequest);
}
