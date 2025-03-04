import { Component, EventEmitter, Output } from '@angular/core';
import { SortDropdownComponent } from "../sort-dropdown/sort-dropdown.component";
import { FilterNotificationsPartComponent } from '../filter-notifications-part/filter-notifications-part.component';

@Component({
  selector: 'app-notifications-page-header',
  standalone: true,
  imports: [SortDropdownComponent, FilterNotificationsPartComponent],
  templateUrl: './notifications-page-header.component.html',
  styleUrl: './notifications-page-header.component.css'
})
export class NotificationsPageHeaderComponent {
  
}
