import { CommonModule } from '@angular/common';
import { Component, ElementRef, HostListener, Renderer2 } from '@angular/core';
import { SortOrder } from '../../../../../libs/generated/typescript-angular';
import { NotificationService } from '../../../../core/services/notification.service';

interface SortOption {
  label: string;
  value: SortOrder;
}

@Component({
  selector: 'app-sort-dropdown',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './sort-dropdown.component.html',
  styleUrls: ['./sort-dropdown.component.css'],
})
export class SortDropdownComponent {
  isSortDropdownVisible = false;
  sortOptions: SortOption[] = [
    { label: 'Newest to Oldest', value: SortOrder.Asc },
    { label: 'Oldest to Newest', value: SortOrder.Desc },
  ];
  selectedSortOption: SortOption = this.sortOptions[0];

  constructor(
    private notificationService: NotificationService,
    private renderer: Renderer2,
    private elementRef: ElementRef
  ) {}

  toggleSortDropdown(): void {
    this.isSortDropdownVisible = !this.isSortDropdownVisible;
  }

  selectSort(option: SortOption): void {
    this.selectedSortOption = option;
    this.isSortDropdownVisible = false;
    this.notificationService.getNotificationsWithSpecForUser(option.value).subscribe();
  }

  @HostListener('document:click', ['$event'])
  handleDocumentClick(event: MouseEvent): void {
    if (
      this.isSortDropdownVisible &&
      !this.elementRef.nativeElement.contains(event.target)
    ) {
      this.isSortDropdownVisible = false;
    }
  }
}
