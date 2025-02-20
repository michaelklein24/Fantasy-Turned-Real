import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';

export interface DropdownMenuItem {
  label: string,
  value: string,
}

@Component({
  selector: 'app-dropdown',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './dropdown.component.html',
  styleUrl: './dropdown.component.css'
})
export class DropdownComponent {
  @Input() label: string = 'Options';
  @Input() menuItems: DropdownMenuItem[] = [];

  @Output() itemSelected = new EventEmitter<DropdownMenuItem>();

  isOpen = false;

  toggleMenu() {
    this.isOpen = !this.isOpen;
  }

  selectItem(item: DropdownMenuItem) {
    this.itemSelected.emit(item);
    this.isOpen = false;
  }
}
