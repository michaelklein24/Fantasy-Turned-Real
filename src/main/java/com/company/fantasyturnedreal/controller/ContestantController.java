package com.company.fantasyturnedreal.controller;

import com.company.fantasyturnedreal.dto.contestant.CreateContestantRequest;
import com.company.fantasyturnedreal.dto.contestant.UpdateContestantRequest;
import com.company.fantasyturnedreal.enums.Show;
import com.company.fantasyturnedreal.exception.MismatchingIdsException;
import com.company.fantasyturnedreal.model.contestant.Contestant;
import com.company.fantasyturnedreal.service.contestant.ContestantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.company.fantasyturnedreal.util.RestApiSupport.REST_API_CONTEXT_PATH;

@RestController
@RequestMapping(path = REST_API_CONTEXT_PATH + "/contestant" )
public class ContestantController {

    @Autowired
    ContestantService contestantService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Contestant> getContestants(@RequestParam(name = "seasonId", required = false) Long seasonId, @RequestParam(name = "show", required = false) Show show) {
        if (seasonId != null || show != null) {
            return contestantService.getContestants(show, seasonId);
        } else {
            return contestantService.getAllContestants();
        }
    }

    @GetMapping("/{contestantId}")
    @ResponseStatus(HttpStatus.OK)
    public Contestant getContestant(@PathVariable Long contestantId) {
        return contestantService.getContestantById(contestantId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Contestant createContestant(@Valid @RequestBody CreateContestantRequest request) {
        return contestantService.createContestant(request);
    }

    @PutMapping("/{contestantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateContestant(@PathVariable Long contestantId, @Valid @RequestBody UpdateContestantRequest request) {
        if (!request.getContestantId().equals(contestantId)) {
            throw new MismatchingIdsException(String.format("The contestant id found in the request body (%s) does not match the contestant id found in the path (%d).", request.getContestantId(), contestantId));
        }
        contestantService.updateContestant(request);
    }

    @DeleteMapping("/{contestantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContestant(@PathVariable Long contestantId) {
        contestantService.deleteContestant(contestantId);
    }
}
