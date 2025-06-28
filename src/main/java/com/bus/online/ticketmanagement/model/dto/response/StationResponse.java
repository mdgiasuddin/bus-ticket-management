package com.bus.online.ticketmanagement.model.dto.response;

import com.bus.online.ticketmanagement.model.entity.Station;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StationResponse {
    private Integer id;
    private String name;
    private String district;

    public StationResponse(Station station) {
        this.id = station.getId();
        this.name = station.getName();
        this.district = station.getDistrict();
    }
}
