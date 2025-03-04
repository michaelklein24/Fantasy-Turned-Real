import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

export interface Toast {
  message: string;
  type: 'success' | 'error' | 'info';
  duration: number; // duration in milliseconds
}

@Injectable({
  providedIn: 'root',
})
export class ToastService {
  private toastSubject = new Subject<Toast>();
  toastState$ = this.toastSubject.asObservable();

  // Show a toast with the specified message, type, and duration
  private showToast(message: string, type: 'success' | 'error' | 'info', duration: number): void {
    this.toastSubject.next({ message, type, duration });
  }

  // Toast helper methods
  toast(message: string, type: 'success' | 'error' | 'info' = 'info', duration: number = 3000): void {
    this.showToast(message, type, duration);
  }

  toastError(message: string, duration: number): void {
    this.showToast(message, 'error', duration);
  }

  toastInfo(message: string, duration: number): void {
    this.showToast(message, 'info', duration);
  }

  toastSuccess(message: string, duration: number): void {
    this.showToast(message, 'success', duration);
  }

  toastApiError(operation: string, error: any, duration: number): void {
    const toastMessage = error.code === 'ERR_NETWORK'
      ? `Unable to connect to the server. Please try again later.`
      : `Unable to ${operation} due to an unhandled error: ${error.message ?? error}`;
    
    this.toastError(toastMessage, duration);
  }
}
