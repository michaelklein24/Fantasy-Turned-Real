import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, of, take, tap, throwError } from 'rxjs';
import { ApiService } from './api.service';
import { GetNotificationsResponse, MarkNotificationsAsReadOrUnreadRequest, Notification } from '../../../libs/generated/typescript-angular';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private notifications: Notification[] = [];
  private notificationsSubject = new BehaviorSubject<Notification[]>(this.notifications);

  public notifications$ = this.notificationsSubject.asObservable();

  constructor(
    private apiService: ApiService
  ) {}

  getNotificationsForUser(): Observable<Notification[]> {
    return this.apiService.notification.getNotificationsForUser().pipe(
      take(1),
      tap((response: GetNotificationsResponse) => {
        this.notifications = response.notifications!
        this.notificationsSubject.next(this.notifications);
      }),
      map((response: GetNotificationsResponse) => response.notifications!),
      catchError((error: any) => {
        console.error('Error fetching leagues for user:', error.response?.data || error.message);
        return throwError(() => error);
      })
    );
  }

  // Add notification to the list
  addNotification(notification: Notification): void {
      this.notifications.unshift(notification);
      this.notificationsSubject.next(this.notifications);
  }

  // Mark a notification as read
  markNotificationsAsRead(): Observable<Notification[]> {
    const notificationIdsToBeMarked = this.notifications
      .filter(n => !n.acknowledged)
      .map(n => n.notificationId!);

    if (notificationIdsToBeMarked.length === 0) {
      console.log("No unread notifications to mark as read.");
      return of(this.notifications);
    }

    const request: MarkNotificationsAsReadOrUnreadRequest = {
      notificationIds: notificationIdsToBeMarked,
      acknowledged: true
    };

    return this.apiService.notification.markNotificationAsReadOrUnread(request).pipe(
      take(1),
      tap(() => {
        this.notifications.forEach(n => {
          if (notificationIdsToBeMarked.includes(n.notificationId!)) {
            n.acknowledged = true;
          }
        });

        this.notificationsSubject.next(this.notifications);
        console.log("Notifications marked as read successfully.");
      }),
      map(() => this.notifications), // Return updated notifications
      catchError((error: any) => {
        console.error("Failed to mark notifications as read:", error.response?.data || error.message);
        return throwError(() => error);
      })
    );
  }



}
