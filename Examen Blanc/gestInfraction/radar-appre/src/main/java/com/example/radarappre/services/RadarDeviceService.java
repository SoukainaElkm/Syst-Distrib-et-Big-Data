package com.example.radarappre.services;

import com.example.radarappre.model.Radar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.radarappre.model.OverSpeedDetection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@EnableScheduling
public class RadarDeviceService {
    private RestTemplate restTemplate;
    private List<Radar> radarList=new ArrayList<>();
    private List<Character> registrationCharacters;
    private Random random=new Random();

    public RadarDeviceService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public void loadRadars(){
        ResponseEntity<Radar[]> exchange = restTemplate.exchange("http://localhost:8083/query/radars/all", HttpMethod.GET, null, Radar[].class);
        this.radarList= Arrays.asList(exchange.getBody());
        this.registrationCharacters=Arrays.asList('A','B','C','D','E','F');
        log.info("=>{}",radarList.size());
    }

    @Scheduled(fixedRate = 1000)
    public void submitOverSpeedDetection(){
        loadRadars();
        int index = random.nextInt(radarList.size()>=4?4:radarList.size());
        Radar radar=radarList.get(index);
        if(!radar.getRadarStatus().equals("OFF")){
            int registrationNumber=(int)(Math.random()*10);
            Character m2=registrationCharacters.get(random.nextInt(registrationCharacters.size()));
            int region=1+(int)(Math.random()*3);
            String vehicleRegistrationNumber=registrationNumber+"-"+m2+"-"+region;
            OverSpeedDetection overSpeedDetection=new OverSpeedDetection();
            overSpeedDetection.setRadarId(radar.getRadarId());
            overSpeedDetection.setVehicleRegistrationNumber(vehicleRegistrationNumber);
            overSpeedDetection.setVehicleSpeed(radar.getMaxSpeed()+(int)(5+Math.random()*60));
            this.restTemplate.exchange(
                    "http://localhost:8888/RADAR-COMMAND-SIDE-SERVICE/commands/radar/overSpeed",
                    HttpMethod.POST,
                    new HttpEntity<>(overSpeedDetection),
                    String.class
            );
        }
    }
}
