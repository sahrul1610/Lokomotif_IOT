package com.example.Scheduler_BE.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.Scheduler_BE.Entity.Model.Lokomotif;
import com.example.Scheduler_BE.Repository.LokomotifRepository;

import java.sql.Timestamp;
import java.util.Random;
import java.util.UUID;



import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LokomotifService {

    @Autowired
    private LokomotifRepository repository;

    @Autowired
    private KafkaTemplate<String, Lokomotif> kafkaTemplate;

    private static final String[] STATUSES = {"Active", "Non Active", "Maintenance"};
    private final Random random = new Random();

    @Scheduled(fixedRate = 10000)
    public void generateLokomotifData() {
        Lokomotif lokomotif = new Lokomotif();
        lokomotif.setKode_lokomotif(UUID.randomUUID().toString());
        lokomotif.setNama_lokomotif("Lokomotif " + random.nextInt(100));
        lokomotif.setDimensi_lokomotif((10 + random.nextInt(10)) + "m x " + (2 + random.nextInt(2)) + "m x " + (3 + random.nextInt(2)) + "m");
        lokomotif.setStatus(getRandomStatus());
        lokomotif.setTanggal(new Timestamp(System.currentTimeMillis()));

        repository.save(lokomotif);
        log.info("Created Lokomotif: {}", lokomotif);

        kafkaTemplate.send("lokomotif-topic", lokomotif);
        log.info("Sent Lokomotif to Kafka: {}", lokomotif);
    }

    private String getRandomStatus() {
        int randomIndex = random.nextInt(STATUSES.length);
        return STATUSES[randomIndex];
    }
}