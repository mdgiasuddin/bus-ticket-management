package com.bus.online.ticketmanagement.mapper;

import com.bus.online.ticketmanagement.model.dto.response.RouteResponse;
import com.bus.online.ticketmanagement.model.entity.Route;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        builder = @Builder(disableBuilder = true)
)
public interface RouteMapper {

    List<RouteResponse> getResponseFromRoutes(List<Route> routes);
}
