package com.kleintwins.ftr.notification.controller;

import com.kleintwins.ftr.auth.helper.JwtHelper;
import com.kleintwins.ftr.core.dto.CustomErrorResponse;
import com.kleintwins.ftr.notification.dto.GetNotificationsResponse;
import com.kleintwins.ftr.notification.model.NotificationModel;
import com.kleintwins.ftr.notification.service.NotificationService;
import com.kleintwins.ftr.notification.util.NotificationDtoBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;
    private final JwtHelper jwtHelper;

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Fetch notifications for user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notifications successfully fetched",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GetNotificationsResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request: Validation failed or invalid input",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized: Missing or invalid JWT token",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden: Insufficient permissions",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error: Unexpected server issue",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CustomErrorResponse.class)))
    })
    public ResponseEntity<GetNotificationsResponse> getNotificationsForUser(
            HttpServletRequest request
    ) {
        String userId = jwtHelper.extractUserIdFromTokenInRequest(request);
        List<NotificationModel> notificationModels = notificationService.getNotificationsForUser(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(NotificationDtoBuilder.buildGetNotificationsResponse(notificationModels));
    }
}
