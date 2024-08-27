package com.aslam.masterspringboot.school;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/v1/school-controller")
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }


    @PostMapping("/schools")
    public SchoolDto create(@RequestBody SchoolDto dto) {
        return schoolService.create(dto);
    }

    @GetMapping("/schools")
    public Collection<SchoolDto> findAllSchools() {
        return schoolService.findAllSchools();
    }
}
