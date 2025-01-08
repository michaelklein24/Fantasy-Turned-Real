import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeagueSurveyComponent } from './league-survey.component';

describe('LeagueSurveyComponent', () => {
  let component: LeagueSurveyComponent;
  let fixture: ComponentFixture<LeagueSurveyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LeagueSurveyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LeagueSurveyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
