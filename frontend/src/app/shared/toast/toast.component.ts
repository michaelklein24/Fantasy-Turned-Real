import { Component, OnDestroy, OnInit } from '@angular/core';
import { Toast, ToastService } from '../../services/toast.service';
import { Subscription } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-toast',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './toast.component.html',
  styleUrl: './toast.component.css'
})
export class ToastComponent implements OnInit, OnDestroy {
  toast: Toast | null = null;
  toastSubscription: Subscription | null = null;
  isSmallScreen: boolean = false;

  constructor(private toastService: ToastService) {
    this.checkScreenSize();
    window.addEventListener('resize', () => this.checkScreenSize());
  }

  ngOnInit(): void {
    this.toastSubscription = this.toastService.toastState$.subscribe((toast) => {
      this.toast = toast;
      setTimeout(() => this.toast = null, toast.duration); // Auto-dismiss after duration
    });
  }

  ngOnDestroy(): void {
    if (this.toastSubscription) {
      this.toastSubscription.unsubscribe();
    }
  }

  checkScreenSize() {
    this.isSmallScreen = window.innerWidth < 768; // Adjust this breakpoint as needed
  }

  get toastClass() {
    if (!this.toast) return '';
    return {
      'bg-green-500 text-white': this.toast.type === 'success',
      'bg-red-500 text-white': this.toast.type === 'error',
      'bg-blue-500 text-white': this.toast.type === 'info',
    };
  }
}
