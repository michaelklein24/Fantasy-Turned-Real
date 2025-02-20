package com.kleintwins.ftr.show.util;

import com.kleintwins.ftr.show.dto.Contestant;
import com.kleintwins.ftr.show.dto.ContestantSocial;
import com.kleintwins.ftr.show.dto.GetContestantsResponse;
import com.kleintwins.ftr.show.model.ContestantModel;
import com.kleintwins.ftr.show.model.ContestantSocialModel;

import java.util.List;

public class ContestantDtoBuilder {

    public static GetContestantsResponse buildGetContestantsResponse(List<ContestantModel> contestantModels) {
        List<Contestant> contestants = contestantModels.stream()
                .map(ContestantDtoBuilder::buildContestant)
                .toList();

        GetContestantsResponse response = new GetContestantsResponse();
        response.setContestants(contestants);
        return response;
    }

    public static Contestant buildContestant(ContestantModel contestantModel) {
        List<ContestantSocial> socials = contestantModel.getSocials().stream()
                .map(ContestantDtoBuilder::buildContestantSocial)
                .toList();

        return Contestant.builder()
                .contestantId(contestantModel.getContestantId())
                .firstName(contestantModel.getFirstName())
                .lastName(contestantModel.getLastName())
                .socials(socials)
                .build();
    }

    public static ContestantSocial buildContestantSocial(ContestantSocialModel contestantSocialModel) {
        return ContestantSocial.builder()
                .platform(contestantSocialModel.getContestantSocialId().getPlatform())
                .handle(contestantSocialModel.getHandle())
                .build();
    }

}
