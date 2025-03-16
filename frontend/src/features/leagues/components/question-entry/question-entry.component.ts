import { Component, Input, OnInit, OnDestroy } from '@angular/core';
import {
  Question,
  AnswerOption,
} from '../../../../libs/generated/typescript-angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  DropdownComponent,
  DropdownMenuItem,
} from '../../../../shared/components/dropdown/dropdown.component';

@Component({
  selector: 'app-question-entry',
  standalone: true,
  imports: [CommonModule, FormsModule, DropdownComponent],
  templateUrl: './question-entry.component.html',
  styleUrl: './question-entry.component.css',
})
export class QuestionEntryComponent implements OnInit, OnDestroy {
  @Input() question: Question | undefined;
  selectedAnswers: AnswerOption[] = [];
  mappedAnswerOptions: DropdownMenuItem[] = [];

  ngOnInit(): void {
    console.log(this.question);
    this.mapAnswerOptions();
  }

  ngOnDestroy(): void {}

  mapAnswerOptions() {
    if (this.question?.answerOptions) {
      this.mappedAnswerOptions = this.question.answerOptions.map((option) => ({
        label: option.text!,
        value: option.text!,
      }));
    }
  }

  onAnswerSelected(selectedItems: DropdownMenuItem[], multi: boolean = false) {
    if (multi) {
      // Multiple answers selection
      const selectedAnswers = selectedItems
        .map((item) =>
          this.question?.answerOptions?.find((ao) => ao.text === item.value)!
        )
        .filter(Boolean);

      this.selectedAnswers = [...this.selectedAnswers, ...selectedAnswers];
      console.log('Multiple choices selected:', selectedItems);
    } else {
      // Single answer selection
      const selectedAnswer = this.question?.answerOptions?.find(
        (ao) => ao.text === selectedItems[0]?.value
      );

      if (selectedAnswer) {
        this.selectedAnswers = [selectedAnswer];
      }
      console.log('Single choice selected:', selectedItems[0]);
    }
  }
}
