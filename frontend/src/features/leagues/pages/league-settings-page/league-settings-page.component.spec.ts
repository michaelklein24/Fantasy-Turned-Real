import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeagueSettingsPageComponent } from './league-settings-page.component';

describe('LeagueSettingsPageComponent', () => {
  let component: LeagueSettingsPageComponent;
  let fixture: ComponentFixture<LeagueSettingsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LeagueSettingsPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LeagueSettingsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
