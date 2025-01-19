import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FilterNotificationsPartComponent } from './filter-notifications-part.component';

describe('FilterNotificationsPartComponent', () => {
  let component: FilterNotificationsPartComponent;
  let fixture: ComponentFixture<FilterNotificationsPartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FilterNotificationsPartComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FilterNotificationsPartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
