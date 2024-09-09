package com.example.Scheduler_BE.Entity.Model;


import java.sql.Timestamp;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Table(name = "list_lokomotif")
public class Lokomotif {
    @Id
    private String kode_lokomotif;  
    private String nama_lokomotif;
    private String dimensi_lokomotif;
    private String status;
    private Timestamp tanggal;
}