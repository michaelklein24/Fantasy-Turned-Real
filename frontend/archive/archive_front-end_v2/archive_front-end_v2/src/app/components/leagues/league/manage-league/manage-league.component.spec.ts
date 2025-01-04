import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageLeagueComponent } from './manage-league.component';

describe('ManageLeagueComponent', () => {
  let component: ManageLeagueComponent;
  let fixture: ComponentFixture<ManageLeagueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManageLeagueComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ManageLeagueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
