package com.company.fantasyturnedreal.controller;

import com.company.fantasyturnedreal.dto.season.CreateEpisodeRequest;
import com.company.fantasyturnedreal.dto.season.UpdateEpisodeRequest;
import com.company.fantasyturnedreal.exception.MismatchingIdsException;
import com.company.fantasyturnedreal.model.season.Episode;
import com.company.fantasyturnedreal.service.season.EpisodeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.company.fantasyturnedreal.util.RestApiSupport.REST_API_CONTEXT_PATH;

@RestController
@RequestMapping(path = REST_API_CONTEXT_PATH + "/episode")
public class EpisodeController {

    @Autowired
    EpisodeService episodeService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Episode> getEpisodes(@RequestParam(name = "seasonId", required = false) Long seasonId) {
        if (seasonId != null) {
            return episodeService.getAllEpisodesInSeason(seasonId);
        }
        return episodeService.getAllEpisodes();
    }

    @GetMapping("/{episodeId}")
    @ResponseStatus(HttpStatus.OK)
    public Episode getEpisodeById(@PathVariable Long episodeId) {
        return episodeService.getEpisodeById(episodeId);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Episode createEpisode(@Valid @RequestBody CreateEpisodeRequest request) {
        return episodeService.createEpisode(request);
    }

    @PutMapping("/{episodeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEpisode(@PathVariable Long episodeId, @Valid @RequestBody UpdateEpisodeRequest request) {
        if (!request.getEpisodeId().equals(episodeId)) {
            throw new MismatchingIdsException(String.format("The episode id found in the request body (%s) does not match the episode id found in the path (%d).", request.getEpisodeId(), episodeId));
        }
        episodeService.updateEpisodeDetails(request);
    }

    @DeleteMapping("/{episodeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEpisode(@PathVariable Long episodeId) {
        episodeService.deleteEpisode(episodeId);
    }

}
