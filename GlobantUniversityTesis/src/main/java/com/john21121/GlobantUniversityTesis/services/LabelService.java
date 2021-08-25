package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.dto.LabelDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LabelService {

    LabelDto findLabelById(Long id);

    LabelDto findLabelByLabelName(String labelName);

    LabelDto createNewLabel(LabelDto labelDto);

    LabelDto saveLabelByLabelDto(Long id, LabelDto labelDto);

    List<LabelDto> getAllLabels();

    void deleteById(Long deletionId);

    void deleteLabelByLabelName(String labelName);

}
