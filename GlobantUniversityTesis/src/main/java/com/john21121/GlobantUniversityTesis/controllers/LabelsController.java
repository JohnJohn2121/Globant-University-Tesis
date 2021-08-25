package com.john21121.GlobantUniversityTesis.controllers;

import com.john21121.GlobantUniversityTesis.dto.LabelDto;
import com.john21121.GlobantUniversityTesis.dto.MessageDto;
import com.john21121.GlobantUniversityTesis.dto.RecipientDto;
import com.john21121.GlobantUniversityTesis.mailingsystem.Label;
import com.john21121.GlobantUniversityTesis.repository.LabelRepository;
import com.john21121.GlobantUniversityTesis.repository.RecipientRepository;
import com.john21121.GlobantUniversityTesis.services.LabelServiceImpl;
import com.john21121.GlobantUniversityTesis.services.RecipientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user/login/labels")
public class LabelsController {

    private final LabelServiceImpl labelService;
    private final RecipientServiceImpl recipientService;

    public LabelsController(LabelServiceImpl labelService, RecipientServiceImpl recipientService) {
        this.labelService = labelService;
        this.recipientService = recipientService;
    }

    @PostMapping("/label/")
    @ResponseStatus(HttpStatus.CREATED)
    public LabelDto createNewLabel(@RequestBody LabelDto labelDto){
        return labelService.createNewLabel(labelDto);
    }

    @PutMapping("/addLabel/{labelName}/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void addLabelToRecipient(@PathVariable("labelName")String labelName,
                                    @RequestBody RecipientDto recipientDto){
        LabelDto labelDto = labelService.findLabelByLabelName(labelName);
        recipientDto = recipientService.findById(recipientDto.getId());

        List<LabelDto> labelDtos = new ArrayList<>();
        List<RecipientDto> recipientDtos = new ArrayList<>();
        labelDtos.add(labelDto);
        recipientDtos.add(recipientDto);
        labelDto.setRecipients(recipientDtos);
        recipientDto.setLabelDtoSet(labelDtos);

        recipientService.saveRecipientByDto(recipientDto.getId(),recipientDto);
        labelService.saveLabelByLabelDto(labelDto.getId(), labelDto);
    }


    @GetMapping("/allLabels")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<LabelDto> getAllLabels(){
        return labelService.getAllLabels();
    }

    @GetMapping("/label/{labelId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LabelDto getLabelById(@PathVariable("labelId")Long labelId){
        return labelService.findLabelById(labelId);
    }

    @GetMapping("/label/{labelName}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LabelDto getLabelByName(@PathVariable("labelName")String labelName){
        return labelService.findLabelByLabelName(labelName);
    }


    @DeleteMapping("/delete/{labelId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUserById(@PathVariable("labelId")Long labelId){
        labelService.deleteById(labelId);
    }

    @DeleteMapping("/delete/{labelName}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteUserByUsername(@PathVariable("labelName")String labelName){
        labelService.deleteLabelByLabelName(labelName);
    }
}
