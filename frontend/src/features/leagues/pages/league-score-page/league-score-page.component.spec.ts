import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeagueScorePageComponent } from './league-score-page.component';

describe('LeagueScorePageComponent', () => {
  let component: LeagueScorePageComponent;
  let fixture: ComponentFixture<LeagueScorePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LeagueScorePageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LeagueScorePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
