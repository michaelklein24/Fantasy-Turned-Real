import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyStatusBadgeComponent } from './survey-status-badge.component';

describe('SurveyStatusBadgeComponent', () => {
  let component: SurveyStatusBadgeComponent;
  let fixture: ComponentFixture<SurveyStatusBadgeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SurveyStatusBadgeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SurveyStatusBadgeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
