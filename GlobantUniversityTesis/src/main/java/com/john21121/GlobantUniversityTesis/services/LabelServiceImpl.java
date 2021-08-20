package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.dto.LabelDto;
import com.john21121.GlobantUniversityTesis.dto.MessageDto;
import com.john21121.GlobantUniversityTesis.mailingsystem.Label;
import com.john21121.GlobantUniversityTesis.mailingsystem.Message;
import com.john21121.GlobantUniversityTesis.mappers.LabelMapper;
import com.john21121.GlobantUniversityTesis.repository.LabelRepository;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class LabelServiceImpl implements LabelService {


    private final LabelRepository labelRepository;
    private final LabelMapper labelMapper;

    public LabelServiceImpl(LabelRepository labelRepository, LabelMapper labelMapper) {
        this.labelRepository = labelRepository;
        this.labelMapper = labelMapper;
    }

    private LabelDto saveAndReturn(Label label){
        Label savedLabel = labelRepository.save(label);
        return labelMapper.labelToLabelDTO(savedLabel);
    }

    @Override
    public LabelDto findLabelById(Long id) {
        return  labelRepository.findById(id).map(labelMapper::labelToLabelDTO)
                .map(labelDto -> {labelDto.setId(id);
                return labelDto;}).orElseThrow(ResolutionException::new);
    }

    @Override
    public LabelDto createNewLabel(LabelDto labelDto) {
        return saveAndReturn(labelMapper.labelDtoToLabel(labelDto));
    }

    @Override
    public LabelDto saveLabelByLabelDto(Long id, LabelDto labelDto) {
        Label label = labelMapper.labelDtoToLabel(labelDto);
        label.setId(id);
        return saveAndReturn(label);
    }

    @Override
    public List<LabelDto> getAllLabels() {
        return labelRepository.findAll().stream()
                .map(labelMapper::labelToLabelDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long deletionId) {
        labelRepository.deleteById(deletionId);
    }
}
