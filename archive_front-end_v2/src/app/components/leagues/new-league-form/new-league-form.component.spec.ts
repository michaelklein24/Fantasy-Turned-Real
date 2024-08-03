import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewLeagueFormComponent } from './new-league-form.component';

describe('NewLeagueFormComponent', () => {
  let component: NewLeagueFormComponent;
  let fixture: ComponentFixture<NewLeagueFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewLeagueFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewLeagueFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
