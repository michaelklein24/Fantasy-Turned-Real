import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { AnswerOption, Question } from '../../../../libs/generated/typescript-angular';
import { QuestionEntryComponent } from '../question-entry/question-entry.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-questions-panel',
  standalone: true,
  imports: [CommonModule, QuestionEntryComponent],
  templateUrl: './questions-panel.component.html',
  styleUrl: './questions-panel.component.css',
})
export class QuestionsPanelComponent implements OnInit, OnDestroy {
  @Input()
  questions: Question[] | undefined;

  ngOnInit(): void {
    console.log(this.questions);
  }

  ngOnDestroy(): void {}

  onAnswerSelected(answerOption: AnswerOption) {

  }

}
