package com.kleintwins.ftr.core.service;

import com.kleintwins.ftr.core.model.SequenceId;
import com.kleintwins.ftr.core.model.SequenceModel;
import com.kleintwins.ftr.core.repository.SequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SequenceService {

    private final SequenceRepository sequenceRepository;

    public Integer peekNextSequence(Class<?> clazz, String entityId) {
        SequenceId sequenceId = new SequenceId(clazz.getName(), entityId);
        Optional<SequenceModel> optSequenceModel = sequenceRepository.findById(sequenceId);
        return optSequenceModel.map(SequenceModel::getNextAvailableSequence).orElse(1);
    }

    public Integer getNextSequence(Class<?> clazz, String entityId) {
        SequenceId sequenceId = new SequenceId(clazz.getName(), entityId);
        SequenceModel sequenceModel = sequenceRepository.findById(sequenceId)
                .orElseGet(() -> {
                    SequenceModel newSequence = new SequenceModel();
                    newSequence.setSequenceId(sequenceId);
                    newSequence.setNextAvailableSequence(2);
                    return newSequence;
                });

        Integer availableSequence = sequenceModel.getNextAvailableSequence();
        sequenceModel.setNextAvailableSequence(availableSequence + 1);
        sequenceRepository.save(sequenceModel);
        return availableSequence;
    }

}
