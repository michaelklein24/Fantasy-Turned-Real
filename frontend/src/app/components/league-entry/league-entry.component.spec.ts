import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeagueEntryComponent } from './league-entry.component';

describe('LeagueEntryComponent', () => {
  let component: LeagueEntryComponent;
  let fixture: ComponentFixture<LeagueEntryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LeagueEntryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LeagueEntryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
