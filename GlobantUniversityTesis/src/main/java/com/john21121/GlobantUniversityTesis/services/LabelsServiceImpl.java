package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.mailingsystem.Label;
import com.john21121.GlobantUniversityTesis.repository.LabelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LabelsServiceImpl implements LabelsService {

    private final LabelRepository labelRepository;

    public LabelsServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label findById(Long l) {

        Optional<Label> labelOptional = labelRepository.findById(l);

        if (!labelOptional.isPresent()){
            throw new NotFoundException("This Label Does not exist");
        }

        return labelOptional.get();
    }

    @Override
    public Label createLabel(Label label) {
        labelRepository.save(label);
        return label;
    }

    @Override
    public Optional<Label> updateLabelById(Long id, Label label) {
       Optional<Label> labelOptional = labelRepository.findById(id);

        return labelOptional;
    }

    @Override
    public void deleteById(Long deletionId) {
            labelRepository.deleteById(deletionId);
    }
}
