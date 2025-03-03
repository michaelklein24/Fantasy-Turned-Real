import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LeaguesComponent } from './leagues-page.component';

describe('LeaguesComponent', () => {
  let component: LeaguesComponent;
  let fixture: ComponentFixture<LeaguesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LeaguesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LeaguesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
