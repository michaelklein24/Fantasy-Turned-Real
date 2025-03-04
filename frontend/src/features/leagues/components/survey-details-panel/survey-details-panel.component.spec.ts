import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyDetailsComponent } from './survey-details-panel.component';

describe('SurveyDetailsComponent', () => {
  let component: SurveyDetailsComponent;
  let fixture: ComponentFixture<SurveyDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SurveyDetailsComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(SurveyDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
