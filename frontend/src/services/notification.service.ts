import { Injectable } from '@angular/core';
import {
  BehaviorSubject,
  catchError,
  map,
  Observable,
  of,
  Subject,
  take,
  tap,
  throwError,
} from 'rxjs';
import { GetNotificationsResponse, MarkNotificationsAsReadOrUnreadRequest, Notification, NotificationReferenceType, SortOrder } from '../libs/generated/typescript-angular';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root',
})
export class NotificationService {
  private notifications: Notification[] = [];
  private notificationsSubject = new BehaviorSubject<Notification[]>(
    this.notifications
  );

  private filteredNotifications: Notification[] = [];
  private filteredNotificationsSubject = new BehaviorSubject<Notification[]>(
    this.filteredNotifications
  );

  public notifications$ = this.notificationsSubject.asObservable();
  public filteredNotifications$ =
    this.filteredNotificationsSubject.asObservable();

  private order: 'ASC' | 'DESC' = 'ASC';
  private notificationTypes: NotificationReferenceType[] = [];

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

  private setNotificationTypes(notificationTypes: NotificationReferenceType[]) {
    this.notificationTypes = notificationTypes;
  }

  private setOrder(order: SortOrder) {
    this.order = order;
  }

  getNotificationsWithSpecForUser(
    order?: SortOrder,
    notificationTypes?: NotificationReferenceType[],
    fetchSize: number = 100
  ): Observable<Notification[]> {
    if (notificationTypes) {
      this.setNotificationTypes(notificationTypes);
    }
    if (order) {
      this.setOrder(order);
    }

    return this.apiService.notification
      .getNotificationsForUser(this.order, fetchSize, this.notificationTypes)
      .pipe(
        take(1),
        tap((response: GetNotificationsResponse) => {
          this.filteredNotifications = response.notifications!;
          this.filteredNotificationsSubject.next(this.filteredNotifications);
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
    if (this.shouldAddToFilteredNotifications(notification)) {
      if (this.order == SortOrder.Desc) {
        this.filteredNotifications.push(notification);
      } else {
        this.filteredNotifications.unshift(notification);
      }
      this.filteredNotificationsSubject.next(this.filteredNotifications);
    }
  }

  private shouldAddToFilteredNotifications(
    notification: Notification
  ): boolean {
    return (
      this.notificationTypes.length === 0 ||
      (notification.referenceType! &&
        this.notificationTypes.includes(notification.referenceType))
    );
  }

  updateNotificationCompletedActionLabel(
    notificationId: string,
    completedActionLabel: string
  ) {
    const updateNotification = (
      list: Notification[],
      subject: Subject<Notification[]>
    ) => {
      const notification = list.find(
        (n) => n.notificationId === notificationId
      );
      if (notification) {
        notification.completedActionLabel = completedActionLabel;
        subject.next([...list]); // Notify subscribers with a new array reference
        return true;
      }
      return false;
    };

    const updatedNotifications = updateNotification(
      this.notifications,
      this.notificationsSubject
    );
    const updatedFilteredNotifications = updateNotification(
      this.filteredNotifications,
      this.filteredNotificationsSubject
    );

    if (!updatedNotifications && !updatedFilteredNotifications) {
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

    console.log(request);
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
