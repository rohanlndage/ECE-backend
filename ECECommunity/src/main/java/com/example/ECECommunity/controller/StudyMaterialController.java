package com.example.ECECommunity.controller;

import com.example.ECECommunity.service.StudyMaterialService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/study-material")
@CrossOrigin(origins = "*")
public class StudyMaterialController {

    private final StudyMaterialService studyMaterialService;

    public StudyMaterialController(StudyMaterialService studyMaterialService) {
        this.studyMaterialService = studyMaterialService;
    }

    @GetMapping("/vlsi")
    public List<Map<String, String>> getVlsiMaterial() {
        return studyMaterialService.getVlsiMaterial();
    }
}