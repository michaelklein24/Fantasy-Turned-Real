package com.kleintwins.ftr.show.service;

import com.kleintwins.ftr.show.model.ShowModel;
import com.kleintwins.ftr.show.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository showRepo;

    public List<ShowModel> getShows() {
        return showRepo.findAll();
    }
}
