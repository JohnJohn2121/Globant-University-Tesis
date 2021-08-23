package com.john21121.GlobantUniversityTesis.mappers;

import com.john21121.GlobantUniversityTesis.dto.LabelDto;
import com.john21121.GlobantUniversityTesis.mailingsystem.Label;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabelMapperTest {

    LabelMapper labelMapper = LabelMapper.INSTANCE;

    public static final Long ID = 123L;
    public static final String LABELNAME = "generic Label Name";

    @Test
    void labelToLabelDTO() {
        //Given
        Label label = new Label();
        label.setId(ID);
        label.setLabelName(LABELNAME);
        //When
        LabelDto labelDto = labelMapper.labelToLabelDTO(label);

        //Then
        assertEquals(ID,labelDto.getId());
        assertEquals(LABELNAME, labelDto.getLabelName());

    }
}