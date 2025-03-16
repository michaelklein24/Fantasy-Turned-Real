import { CommonModule } from '@angular/common';
import { Component, EventEmitter, forwardRef, Input, Output } from '@angular/core';
import { NG_VALUE_ACCESSOR } from '@angular/forms';

export interface DropdownMenuItem {
  label: string;
  value: string;
}

@Component({
  selector: 'app-dropdown',
  standalone: true,
  imports: [CommonModule],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => DropdownComponent),
      multi: true,
    },
  ],
  templateUrl: './dropdown.component.html',
  styleUrl: './dropdown.component.css',
})
export class DropdownComponent {
  @Input() label: string = 'Options';
  @Input() menuItems: DropdownMenuItem[] = [];
  @Input() multiSelect: boolean = false;
  @Output() itemSelected = new EventEmitter<DropdownMenuItem[]>();

  isOpen = false;
  selectedItems: DropdownMenuItem[] = [];

  toggleMenu() {
    this.isOpen = !this.isOpen;
  }

  selectItem(item: DropdownMenuItem) {
    if (this.multiSelect) {
      const index = this.selectedItems.findIndex((selected) => selected.value === item.value);
      if (index === -1) {
        this.selectedItems.push(item);
      } else {
        this.selectedItems.splice(index, 1);
      }
    } else {
      this.selectedItems = [item];
      this.isOpen = false;
    }

    this.itemSelected.emit(this.selectedItems);
    this.onChange(this.selectedItems.map((i) => i.value));
  }

  isSelected(item: DropdownMenuItem): boolean {
    return this.selectedItems.some((selected) => selected.value === item.value);
  }

  onChange: (value: any) => void = () => {};
  onTouched: () => void = () => {};

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  getSelectedLabels(): string {
    return this.selectedItems.length > 0 ? this.selectedItems.map(i => i.label).join(', ') : this.label;
  }

  writeValue(value: any): void {
    if (Array.isArray(value)) {
      this.selectedItems = this.menuItems.filter((item) => value.includes(item.value));
    }
  }
}
