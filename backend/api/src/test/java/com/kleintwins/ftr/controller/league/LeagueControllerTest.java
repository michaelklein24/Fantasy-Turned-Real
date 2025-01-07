package com.kleintwins.ftr.controller.league;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kleintwins.ftr.auth.helper.JwtHelper;
import com.kleintwins.ftr.controller.exception.GlobalExceptionHandler;
import com.kleintwins.ftr.controller.league.dto.CreateLeagueRequest;
import com.kleintwins.ftr.controller.league.dto.CreateLeagueResponse;
import com.kleintwins.ftr.core.model.LeagueModel;
import com.kleintwins.ftr.core.service.LeagueService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {LeagueController.class, GlobalExceptionHandler.class})
@AutoConfigureMockMvc
@EnableWebMvc
public class LeagueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockitoBean
    private LeagueService leagueService;

    @MockitoBean
    private JwtHelper jwtHelper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void testCreateLeague_Success() throws Exception {
        CreateLeagueRequest createLeagueRequest = new CreateLeagueRequest("League Name");

        // Mock the service call and JWT helper
        LeagueModel mockLeague = new LeagueModel();
        mockLeague.setLeagueId("12345");
        mockLeague.setName("League Name");

        CreateLeagueResponse response = LeagueResponseBuilder.buildCreateLeagueResponse(mockLeague);

        when(leagueService.createLeague(anyString(), anyString())).thenReturn(mockLeague);
        when(jwtHelper.extractUserIdFromTokenInRequest(any(HttpServletRequest.class))).thenReturn("user123");

        // Perform the request and assert
        mockMvc.perform(post("/league")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createLeagueRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.league.leagueId").value("12345"))
                .andExpect(jsonPath("$.league..name").value("League Name"))
                .andExpect(jsonPath("$.league..participants").isArray());
    }

    @Test
    public void testCreateLeague_Unauthorized() throws Exception {
        CreateLeagueRequest createLeagueRequest = new CreateLeagueRequest("Valid League");

        // Mock the service call to simulate missing or invalid JWT
        when(jwtHelper.extractUserIdFromTokenInRequest(any(HttpServletRequest.class))).thenThrow(new IllegalArgumentException("Missing or invalid JWT token"));

        mockMvc.perform(post("/league")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createLeagueRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.errorMsg").value("Missing or invalid JWT token"));
    }

    @Test
    public void testCreateLeague_InternalServerError() throws Exception {
        CreateLeagueRequest createLeagueRequest = new CreateLeagueRequest("Valid League");

        // Mock the service to simulate a server error
        when(jwtHelper.extractUserIdFromTokenInRequest(any(HttpServletRequest.class))).thenReturn("user123");
        when(leagueService.createLeague(anyString(), anyString())).thenThrow(new RuntimeException("Unexpected server issue"));

        mockMvc.perform(post("/league")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(createLeagueRequest)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errorMsg").value("Unexpected server issue"));
    }
}
