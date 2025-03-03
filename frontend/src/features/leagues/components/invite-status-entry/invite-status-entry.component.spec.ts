import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InviteStatusEntryComponent } from './invite-status-entry.component';

describe('InviteStatusEntryComponent', () => {
  let component: InviteStatusEntryComponent;
  let fixture: ComponentFixture<InviteStatusEntryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InviteStatusEntryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InviteStatusEntryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
