import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SortDropdownHeaderComponent } from './sort-dropdown-header.component';

describe('SortDropdownHeaderComponent', () => {
  let component: SortDropdownHeaderComponent;
  let fixture: ComponentFixture<SortDropdownHeaderComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SortDropdownHeaderComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SortDropdownHeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
