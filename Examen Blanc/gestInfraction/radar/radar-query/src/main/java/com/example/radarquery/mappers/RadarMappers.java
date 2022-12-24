package com.example.radarquery.mappers;

import com.example.radarquery.entities.Radar;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RadarMappers {
    Radar from(RadarDTO radarDTO);
    RadarResponseDTO from(Radar radar);
    RadarOverSpeedsDTO fromRadar(Radar radar);
    OverSpeedDetection from(OverSpeedRequestDTO overSpeedRequestDTO);
    OverSpeedResponseDTO fromOS(OverSpeedDetection overSpeedRequest);
}
