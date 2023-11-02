package com.company.fantasyturnedreal.controller;

import com.company.fantasyturnedreal.dto.contestant.CreateContestantStatusRequest;
import com.company.fantasyturnedreal.dto.contestant.UpdateContestantStatusRequest;
import com.company.fantasyturnedreal.enums.Status;
import com.company.fantasyturnedreal.exception.MismatchingIdsException;
import com.company.fantasyturnedreal.model.contestant.ContestantStatus;
import com.company.fantasyturnedreal.service.contestant.ContestantStatusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.company.fantasyturnedreal.util.RestApiSupport.REST_API_CONTEXT_PATH;

@RestController
@RequestMapping(path = REST_API_CONTEXT_PATH + "/contestant/status")
public class ContestantStatusController {
    @Autowired
    ContestantStatusService contestantStatusService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ContestantStatus> getContestantStatuses(
            @RequestParam(name = "status", required = false) Status status,
            @RequestParam(name = "seasonId", required = false) Long seasonId,
            @RequestParam(name = "episodeId", required = false) Long episodeId,
            @RequestParam(name = "contestantId", required = false) Long contestantId) {

        return contestantStatusService.getContestantStatuses(status, seasonId, episodeId, contestantId);
    }

    @GetMapping("/{contestantStatusId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ContestantStatus getContestantStatusById(@PathVariable Long contestantStatusId) {
        return contestantStatusService.getContestantStatusById(contestantStatusId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ContestantStatus createContestantStatus(@Valid @RequestBody CreateContestantStatusRequest request) {
        return contestantStatusService.createContestantStatus(request);
    }

    @PutMapping("/{contestantStatusId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateContestantStatus(@PathVariable Long contestantStatusId, @Valid @RequestBody UpdateContestantStatusRequest request) {
        if (!request.getContestantStatusId().equals(contestantStatusId)) {
            throw new MismatchingIdsException(String.format("The contestantStatus id found in the request body (%s) does not match the contestantStatus id found in the path (%d).", request.getContestantStatusId(), contestantStatusId));
        }
        contestantStatusService.updateContestantStatus(request);
    }

    @DeleteMapping("/{contestantStatusId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContestantStatus(@PathVariable Long contestantStatusId) {
        contestantStatusService.deleteContestantStatus(contestantStatusId);
    }
}
