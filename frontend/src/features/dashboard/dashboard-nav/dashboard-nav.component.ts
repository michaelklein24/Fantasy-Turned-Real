import { CommonModule } from '@angular/common';
import { Component, HostListener, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-dashboard-nav',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule
  ],
  templateUrl: './dashboard-nav.component.html',
  styleUrl: './dashboard-nav.component.css'
})
export class DashboardNavComponent {

  @Input() isOpen = false;

  // Close the nav when a link inside the aside is clicked or when clicking outside the aside
  @HostListener('document:click', ['$event'])
  handleDocumentClick(event: MouseEvent) {
    const aside = document.querySelector('aside');
    const target = event.target as HTMLElement;

    const clickedInsideAside = aside?.contains(target);
    const clickedOnLink = target.tagName === 'A' && aside?.contains(target);

    // Close the nav if clicked on a link or outside the aside
    if (this.isOpen && (clickedOnLink)) {
      this.closeNav();
    }
  }
  closeNav() {
    this.isOpen = false;
  }

}
