import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyStatusPartComponent } from './survey-status-part.component';

describe('SurveyStatusPartComponent', () => {
  let component: SurveyStatusPartComponent;
  let fixture: ComponentFixture<SurveyStatusPartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SurveyStatusPartComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SurveyStatusPartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
