package com.john21121.GlobantUniversityTesis.mailingsystem;

import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.persistence.Entity;

@Data
@Entity
public class Message {

    private String subject;
    private String body;
    private Byte[] attatchment;
}
