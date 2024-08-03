import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-base-modal',
  templateUrl: './base-modal.component.html',
  styleUrls: ['./base-modal.component.css'],
  imports: [],
  standalone: true
})
export class BaseModalComponent {
  @Input() title: string = 'Modal Title';

  constructor() { }

  close() {
  }
}
