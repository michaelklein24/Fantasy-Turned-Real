import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { BaseModalComponent } from '../base-modal/base-modal.component';

@Component({
  selector: 'app-form-modal',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './form-modal.component.html',
  styleUrl: './form-modal.component.css'
})
export class FormModalComponent extends BaseModalComponent {
  @Input() formComponent: any;

  constructor() {
    super();
  }


}
