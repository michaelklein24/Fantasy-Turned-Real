import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-select-control',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './select-control.component.html',
  styleUrl: './select-control.component.css'
})
export class SelectControlComponent {

  @Input() public correct: boolean = false;
  @Input() public editable: boolean = true;
  @Input() public answers: string[] = [];

}
