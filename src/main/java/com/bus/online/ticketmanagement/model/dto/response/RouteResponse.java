package com.bus.online.ticketmanagement.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RouteResponse {
    private Integer id;
    private StationResponse startStation;
    private StationResponse endStation;
    private UUID idKey;
    private String details;
    private Integer distance;
}
