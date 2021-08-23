package com.john21121.GlobantUniversityTesis.services;

import com.john21121.GlobantUniversityTesis.dto.LabelDto;
import com.john21121.GlobantUniversityTesis.mailingsystem.Label;
import com.john21121.GlobantUniversityTesis.mappers.LabelMapper;
import com.john21121.GlobantUniversityTesis.repository.LabelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LabelServiceImplTest {

    LabelService labelService;

    @Mock
    LabelRepository labelRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    labelService = new LabelServiceImpl(labelRepository,LabelMapper.INSTANCE);
    }

    @Test
    void findLabelById() {
        //Given
        String labelName = "GenericLabel";
        Label label = new Label();
        label.setId(2L);
        label.setLabelName(labelName);

        //When
        when(labelRepository.findById(anyLong())).thenReturn(Optional.of(label));

        LabelDto labelDto =labelService.findLabelById(2L);

        //Then
        assertEquals(labelName,labelDto.getLabelName());

    }

    @Test
    void createNewLabel() {
        //Given
        LabelDto labelDto = new LabelDto();
        labelDto.setLabelName("Generic Label Name");
        Label label = new Label();
        label.setLabelName(labelDto.getLabelName());
        label.setId(2L);
        //When
        when(labelRepository.save(any(Label.class))).thenReturn(label);
        LabelDto savedLabel = labelService.createNewLabel(labelDto);

        //Then
        assertEquals(labelDto.getLabelName(),savedLabel.getLabelName());
    }

    @Test
    void getAllLabels() {

        //Given
        List<Label> labels = Arrays.asList(new Label(),new Label());
        when(labelRepository.findAll()).thenReturn(labels);

        //When
        List<LabelDto> labelDtos = labelService.getAllLabels();

        //Then
        assertEquals(2,labelDtos.size());
    }

    @Test
    void deleteById() {
        //Given
        Long id = 33L;
        //When
        labelRepository.deleteById(id);
        //Then
        verify(labelRepository,times(1)).deleteById(anyLong());
    }
}