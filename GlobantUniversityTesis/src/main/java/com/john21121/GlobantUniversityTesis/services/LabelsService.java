package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.mailingsystem.Label;

public interface LabelsService {


   Label findById(Long l);

    void deleteById(Long deletionId);
}
