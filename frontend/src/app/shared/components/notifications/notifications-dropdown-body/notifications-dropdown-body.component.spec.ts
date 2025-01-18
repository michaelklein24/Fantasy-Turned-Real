import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotificationsDropdownBodyComponent } from './notifications-dropdown-body.component';

describe('NotificationsDropdownBodyComponent', () => {
  let component: NotificationsDropdownBodyComponent;
  let fixture: ComponentFixture<NotificationsDropdownBodyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotificationsDropdownBodyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NotificationsDropdownBodyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
