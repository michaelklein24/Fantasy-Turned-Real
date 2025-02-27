import { CommonModule } from '@angular/common';
import { Component, EventEmitter, forwardRef, Input, Output } from '@angular/core';
import { NG_VALUE_ACCESSOR } from '@angular/forms';

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
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => DropdownComponent),
      multi: true,
    }
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
    this.label = item.label;
    this.isOpen = false;
    this.onChange(item.value)
  }

  onChange: (value: any) => void = () => {};
  onTouched: () => void = () => {};

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  writeValue(value: any): void {
    if (value !== undefined) {
      this.label = value;
    }
  }
}
