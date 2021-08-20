package com.john21121.GlobantUniversityTesis.dto;

import com.john21121.GlobantUniversityTesis.mailingsystem.Recipient;
import lombok.Data;

import java.util.Set;

@Data
public class LabelDto {

    private Long id;
    private String labelName;
    private Set<Recipient> recipients;

}
