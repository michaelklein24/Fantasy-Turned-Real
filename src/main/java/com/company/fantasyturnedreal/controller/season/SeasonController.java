package com.company.fantasyturnedreal.controller.season;

import com.company.fantasyturnedreal.dto.season.CreateSeasonRequest;
import com.company.fantasyturnedreal.dto.season.UpdateSeasonRequest;
import com.company.fantasyturnedreal.enums.Show;
import com.company.fantasyturnedreal.exception.MismatchingIdsException;
import com.company.fantasyturnedreal.model.season.Season;
import com.company.fantasyturnedreal.service.season.SeasonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.company.fantasyturnedreal.util.RestApiSupport.REST_API_CONTEXT_PATH;

@RestController
@RequestMapping(path = REST_API_CONTEXT_PATH + "/season")
public class SeasonController {

    @Autowired
    SeasonService seasonService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Season> getSeasons(@RequestParam(name = "show", required = false) Show show) {
        if (show != null) {
            return seasonService.getAllSeasonByShow(show);
        }
        return seasonService.getAllSeasons();
    }

    @GetMapping("/{seasonId}")
    @ResponseStatus(HttpStatus.OK)
    public Season getSeasonById(@PathVariable Long seasonId) {
        return seasonService.getSeasonById(seasonId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Season createSeason(@Valid @RequestBody CreateSeasonRequest request) {
        return seasonService.createSeason(request);
    }

    @PutMapping("/{seasonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSeason(@PathVariable Long seasonId, @Valid @RequestBody UpdateSeasonRequest request) {
        if (!request.getSeasonId().equals(seasonId)) {
            throw new MismatchingIdsException(String.format("The season id found in the request body (%s) does not match the league id found in the path (%d).", request.getSeasonId(), seasonId));
        }
        seasonService.updateSeason(request);
    }

    @DeleteMapping("/{seasonId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSeason(@PathVariable Long seasonId) {
        seasonService.deleteSeason(seasonId);
    }

}
