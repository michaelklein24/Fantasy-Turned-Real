import { Injectable } from '@angular/core';
import {
  BehaviorSubject,
  catchError,
  map,
  Observable,
  of,
  take,
  tap,
  throwError,
} from 'rxjs';
import { ApiService } from './api.service';
import {
  GetNotificationsResponse,
  MarkNotificationsAsReadOrUnreadRequest,
  Notification,
} from '../../../libs/generated/typescript-angular';

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  private notifications: Notification[] = [];
  private notificationsSubject = new BehaviorSubject<Notification[]>(
    this.notifications
  );

  public notifications$ = this.notificationsSubject.asObservable();

  constructor(private apiService: ApiService) {}

  getNotificationsForUser(): Observable<Notification[]> {
    return this.apiService.notification.getNotificationsForUser().pipe(
      take(1),
      tap((response: GetNotificationsResponse) => {
        this.notifications = response.notifications!;
        this.notificationsSubject.next(this.notifications);
      }),
      map((response: GetNotificationsResponse) => response.notifications!),
      catchError((error: any) => {
        console.error(
          'Error fetching leagues for user:',
          error.response?.data || error.message
        );
        return throwError(() => error);
      })
    );
  }

  // Add notification to the list
  addNotification(notification: Notification): void {
    this.notifications.unshift(notification);
    this.notificationsSubject.next(this.notifications);
  }

  updateNotificationCompletedActionLabel(notificationId: string, completedActionLabel: string) {
    const notification = this.notifications.find(n => n.notificationId === notificationId);
    if (notification) {
      notification.completedActionLabel = completedActionLabel;
      this.notificationsSubject.next([...this.notifications]);
    } else {
      console.error('Notification not found for id:', notificationId);
    }
  }

  // Mark notifications as read (single or multiple)
  markNotificationsAsRead(notificationId?: string): Observable<Notification[]> {
    const notificationIdsToBeMarked = notificationId
      ? this.notifications
          .filter((n) => n.notificationId === notificationId && !n.acknowledged)
          .map((n) => n.notificationId!)
      : this.notifications
          .filter((n) => !n.acknowledged)
          .map((n) => n.notificationId!);

    if (notificationIdsToBeMarked.length === 0) {
      console.error(
        notificationId
          ? `Notification with ID ${notificationId} is already marked as read or does not exist.`
          : 'No unread notifications to mark as read.'
      );
      return of(this.notifications).pipe(take(1));
    }
  
    const request: MarkNotificationsAsReadOrUnreadRequest = {
      notificationIds: notificationIdsToBeMarked,
      acknowledged: true,
    };

    console.log(request);


    console.log(request)
    return this.apiService.notification
      .markNotificationAsReadOrUnread(request)
      .pipe(
        take(1),
        tap(() => {
          this.notifications.forEach((n) => {
            if (notificationIdsToBeMarked.includes(n.notificationId!)) {
              n.acknowledged = true;
            }
          });

          this.notificationsSubject.next(this.notifications);
          console.log(
            notificationId
              ? `Notification with ID ${notificationId} marked as read successfully.`
              : 'Notifications marked as read successfully.'
          );
        }),
        map(() => this.notifications), // Return updated notifications
        catchError((error: any) => {
          console.error(
            notificationId
              ? `Failed to mark notification with ID ${notificationId} as read:`
              : 'Failed to mark notifications as read:',
            error.response?.data || error.message
          );
          return throwError(() => error);
        })
      );
  }
}
