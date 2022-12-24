package com.example.radarappre.model;

import lombok.Data;

@Data
public class ChangeRadarStatusRequest {
    private String radarId;
    private String radarStatus;
}
