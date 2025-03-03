import { CommonModule } from '@angular/common';
import { Component, HostListener } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NotificationReferenceType } from '../../../../libs/generated/typescript-angular';
import { NotificationService } from '../../../../services/notification.service';

@Component({
  selector: 'app-filter-notifications-part',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './filter-notifications-part.component.html',
  styleUrl: './filter-notifications-part.component.css'
})
export class FilterNotificationsPartComponent {
  isFilterDropdownVisible = false;

  constructor(
    private notificationService: NotificationService
  ) { }

  // List of filters
  availableNotificationTypes = [
    { label: 'League Invites', value: NotificationReferenceType.LeagueInvite, checked: true },
  ];

  toggleFilterDropdown(): void {
    this.isFilterDropdownVisible = !this.isFilterDropdownVisible;
  }

  onFilterChange(): void {
    // Log or process the updated filters
    const activeFilters = this.availableNotificationTypes.filter(filter => filter.checked).map(filter => filter.value)
    this.notificationService.getNotificationsWithSpecForUser(undefined, activeFilters).subscribe();
  }

  @HostListener('document:click', ['$event'])
  handleDocumentClick(event: MouseEvent) {
    const target = event.target as HTMLElement;
  
    // Get the Filter button element
    const filterButton = document.getElementById('filter-notifications-menu-button');
  
    // Check if the click happened outside the dropdown, filter button, and checkboxes
    if (
      this.isFilterDropdownVisible &&
      target !== filterButton && // Not the Filter button itself
      !filterButton?.contains(target) && // Not within the Filter button's children
      !target.closest('.absolute') // Not within the dropdown
    ) {
      this.isFilterDropdownVisible = false; // Close the dropdown
    }
  }
  
}
