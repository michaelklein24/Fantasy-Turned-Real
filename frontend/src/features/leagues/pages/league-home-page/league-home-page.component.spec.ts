import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeagueHomePageComponent } from './league-home-page.component';

describe('LeagueHomePageComponent', () => {
  let component: LeagueHomePageComponent;
  let fixture: ComponentFixture<LeagueHomePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LeagueHomePageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LeagueHomePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
