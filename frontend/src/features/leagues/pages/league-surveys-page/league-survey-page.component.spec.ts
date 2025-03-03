import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeagueSurveyPageComponent } from './league-survey-page.component';

describe('LeagueSurveyComponent', () => {
  let component: LeagueSurveyPageComponent;
  let fixture: ComponentFixture<LeagueSurveyPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LeagueSurveyPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LeagueSurveyPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
