package com.example.radarappre.model;

import lombok.Data;

@Data
public class Radar {
    private String radarId;
    private String name;
    private int maxSpeed;
    private int longitude,latitude, altitude;
    private String radarStatus;
}
