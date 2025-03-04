import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InviteUserToLeagueFormComponent } from './invite-user-to-league-form.component';

describe('InviteUserToLeagueFormComponent', () => {
  let component: InviteUserToLeagueFormComponent;
  let fixture: ComponentFixture<InviteUserToLeagueFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InviteUserToLeagueFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InviteUserToLeagueFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
