import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ParticipantInviteStatusEntryComponent } from './participant-invite-status-entry.component';

describe('ParticipantInviteStatusEntryComponent', () => {
  let component: ParticipantInviteStatusEntryComponent;
  let fixture: ComponentFixture<ParticipantInviteStatusEntryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ParticipantInviteStatusEntryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ParticipantInviteStatusEntryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
