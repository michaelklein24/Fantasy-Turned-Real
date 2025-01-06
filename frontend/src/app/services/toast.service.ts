import { Injectable } from '@angular/core';
import { AxiosError } from 'axios';
import { Subject } from 'rxjs';

export interface Toast {
  message: string;
  type: 'success' | 'error' | 'info';
  duration: number; // duration in milliseconds
}

@Injectable({
  providedIn: 'root'
})
export class ToastService {

  constructor() { }

  private toastSubject = new Subject<Toast>();
  toastState$ = this.toastSubject.asObservable();

  // Show a toast
  toast(message: string, type: 'success' | 'error' | 'info' = 'info', duration: number = 3000) {
    this.toastSubject.next({
      message,
      type,
      duration,
    });
  }

  toastError(message: string, duration: number): void {
    this.toast(message, 'error', duration);
  }

  toastAxiosError(operation: string, error: any, duration: number): void {
    let toastMessage : string;
    if (error instanceof AxiosError) {
      if (error.code === 'ERR_NETWORK') {
        toastMessage = `Unable to connect to the server. Please try again later.`;
      } else {
        const errorMsg = error?.response?.data?.errorMsg ?? 'Unknown Error';
        const status = error?.response?.status ?? 'Unknown';
        toastMessage = `Unable to ${operation}: ${errorMsg}\nStatus Code: ${status}`;
      }
    } else {
      // Handle non-Axios errors
      toastMessage = `Unable to Register due to an unhandled error: ${error.message ?? error}`;
    }
    this.toastError(toastMessage, duration);
  }

  toastInfo(message: string, duration: number): void {
    this.toast(message, 'info', duration);
  }

  toastSuccess(message: string, duration: number): void {
    this.toast(message, 'success', duration);
  }
}
