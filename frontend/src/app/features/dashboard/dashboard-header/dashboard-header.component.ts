import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-dashboard-header',
  standalone: true,
  imports: [],
  templateUrl: './dashboard-header.component.html',
  styleUrl: './dashboard-header.component.css'
})
export class DashboardHeaderComponent {

  constructor() {

  }

  headerText: string = "Dashboard"

  @Output() toggleMenu = new EventEmitter<void>();

  toggleAside() {
    this.toggleMenu.emit();
  }

}
