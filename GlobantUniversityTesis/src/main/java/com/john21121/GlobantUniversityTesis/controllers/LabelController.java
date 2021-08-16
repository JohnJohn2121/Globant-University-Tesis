package com.john21121.GlobantUniversityTesis.controllers;

import com.john21121.GlobantUniversityTesis.exceptions.NotFoundException;
import com.john21121.GlobantUniversityTesis.mailingsystem.Label;
import com.john21121.GlobantUniversityTesis.mailingsystem.User;
import com.john21121.GlobantUniversityTesis.repository.LabelRepository;
import com.john21121.GlobantUniversityTesis.services.LabelsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/{userId}/labels")
public class LabelController {

    private final LabelRepository labelRepository;
    private final LabelsServiceImpl labelsService;

    public LabelController(LabelRepository labelRepository, LabelsServiceImpl labelsService) {
        this.labelRepository = labelRepository;
        this.labelsService = labelsService;
    }

    @GetMapping("/{labelid}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Label getLabelById(@PathVariable("labelid")Long labelId){
        return labelsService.findById(labelId);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Label createUser(@RequestBody Label label){
        labelRepository.save(label);
        labelsService.createLabel(label);
        return label;
    }

    @PutMapping("/update/{labelid}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Label> updateLabel(@PathVariable("labelid")Long labelId, @RequestBody Label label){
        Optional<Label> labelOptional = labelRepository.findById(labelId);
        if (!labelOptional.isPresent()){
            throw new NotFoundException("This label does not exist");
        }

        return labelsService.updateLabelById(labelId,label);

    }

    @DeleteMapping("delete/{labelid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteLabel(@PathVariable("labelid")Long labelId){
        labelRepository.deleteById(labelId);
        labelsService.deleteById(labelId);
    }


}
