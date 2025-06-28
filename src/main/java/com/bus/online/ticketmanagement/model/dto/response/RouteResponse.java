package com.bus.online.ticketmanagement.model.dto.response;

import com.bus.online.ticketmanagement.model.entity.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RouteResponse {
    private Integer id;
    private StationResponse startStation;
    private StationResponse endStation;
    private String idKey;
    private String details;
    private Integer distance;

    public RouteResponse(Route route) {
        this.id = route.getId();
        this.startStation = new StationResponse(route.getStartStation());
        this.endStation = new StationResponse(route.getEndStation());
        this.idKey = route.getIdKey();
        this.details = route.getDetails();
        this.distance = route.getDistance();
    }
}
