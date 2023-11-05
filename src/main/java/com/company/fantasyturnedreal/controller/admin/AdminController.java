package com.company.fantasyturnedreal.controller.admin;

import com.company.fantasyturnedreal.service.admin.SeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.company.fantasyturnedreal.util.RestApiSupport.REST_API_CONTEXT_PATH;

@Slf4j
@RestController
@RequestMapping(REST_API_CONTEXT_PATH + "/admin")
public class AdminController {

    private final SeedService seedService;

    @Autowired
    public AdminController(SeedService seedService) {
        this.seedService = seedService;
    }

    @PostMapping("/seed-database")
    @ResponseStatus(HttpStatus.CREATED)
    public void seedDataBase() throws Exception {
        seedService.seedDatabase();
    }
}
