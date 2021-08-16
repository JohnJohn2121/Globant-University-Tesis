package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.mailingsystem.Label;

import java.util.Optional;

public interface LabelsService {


   Label findById(Long l);

   Label createLabel(Label label);

   Optional<Label> updateLabelById(Long id, Label label);

    void deleteById(Long deletionId);
}
