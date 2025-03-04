import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotificationsPageHeaderComponent } from './notifications-page-header.component';

describe('NotificationsPageHeaderComponent', () => {
  let component: NotificationsPageHeaderComponent;
  let fixture: ComponentFixture<NotificationsPageHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotificationsPageHeaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NotificationsPageHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
