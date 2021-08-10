package com.john21121.GlobantUniversityTesis.mailingsystem;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Inbox {

    private Labels labels;

}
