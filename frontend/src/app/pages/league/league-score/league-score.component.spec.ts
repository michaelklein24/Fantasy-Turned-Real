import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeagueScoreComponent } from './league-score.component';

describe('LeagueScoreComponent', () => {
  let component: LeagueScoreComponent;
  let fixture: ComponentFixture<LeagueScoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LeagueScoreComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LeagueScoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
