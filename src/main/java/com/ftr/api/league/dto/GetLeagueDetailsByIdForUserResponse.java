package com.ftr.api.league.dto;

import java.util.List;

public class GetLeagueDetailsByIdForUserResponse {
    private String name;
    private byte[] image;
    private byte[] bannerImage;
    private List<ParticipantSummary> participants;
}
