package com.kleintwins.ftr.notification.controller;

import com.kleintwins.ftr.auth.helper.JwtHelper;
import com.kleintwins.ftr.core.dto.CustomErrorResponse;
import com.kleintwins.ftr.notification.code.NotificationReferenceType;
import com.kleintwins.ftr.notification.dto.CreateNotificationRequest;
import com.kleintwins.ftr.notification.dto.CreateNotificationResponse;
import com.kleintwins.ftr.notification.dto.GetNotificationsResponse;
import com.kleintwins.ftr.notification.dto.MarkNotificationsAsReadOrUnreadRequest;
import com.kleintwins.ftr.notification.model.NotificationModel;
import com.kleintwins.ftr.notification.service.NotificationService;
import com.kleintwins.ftr.notification.util.NotificationDtoBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Fetch notifications for user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notifications successfully fetched",
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
        return ResponseEntity.status(HttpStatus.OK).body(NotificationDtoBuilder.buildGetNotificationsResponse(notificationModels));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create and send Notification to user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notifications successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CreateNotificationResponse.class))),
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
    public ResponseEntity<CreateNotificationResponse> createNotification(
        @RequestBody @Valid CreateNotificationRequest createNotificationRequest
    ) {
        String userId = createNotificationRequest.getUserId();
        NotificationReferenceType referenceType = createNotificationRequest.getReferenceType();
        String referenceId = createNotificationRequest.getReferenceId();
        String[] args = createNotificationRequest.getArgs();
        NotificationModel notificationModel = notificationService.createNotification(userId, referenceId, referenceType, args);
        return ResponseEntity.status(HttpStatus.CREATED).body(NotificationDtoBuilder.buildCreateNotificationResponse(notificationModel));
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Mark Notifications as Read or Unread")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notifications successfully marked as acknowledged or unacknowledged"),
            @ApiResponse(responseCode = "404", description = "Not Found: Notification does not exist",
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
    public void markNotificationAsReadOrUnread(
            @RequestBody @Valid MarkNotificationsAsReadOrUnreadRequest markNotificationsAsReadOrUnreadRequest,
            HttpServletRequest request
    ) {
        String userId = jwtHelper.extractUserIdFromTokenInRequest(request);
        List<String> notificationIds = markNotificationsAsReadOrUnreadRequest.getNotificationIds();
        boolean acknowledged = markNotificationsAsReadOrUnreadRequest.isAcknowledged();

        notificationService.markUserNotificationsAsAcknowledged(userId, notificationIds, acknowledged);
    }

}
