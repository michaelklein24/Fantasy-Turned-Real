import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotificationsDropdownHeaderComponent } from './notifications-dropdown-header.component';

describe('NotificationsDropdownHeaderComponent', () => {
  let component: NotificationsDropdownHeaderComponent;
  let fixture: ComponentFixture<NotificationsDropdownHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotificationsDropdownHeaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NotificationsDropdownHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
