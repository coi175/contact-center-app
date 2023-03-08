package com.coi.contactcenterapp.controller.employee;

import com.coi.contactcenterapp.service.person.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DirectorController {
    private final DirectorService directorService;


}
