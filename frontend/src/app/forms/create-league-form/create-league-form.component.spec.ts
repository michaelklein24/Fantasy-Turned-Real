import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateLeagueFormComponent } from './create-league-form.component';

describe('CreateLeagueFormComponent', () => {
  let component: CreateLeagueFormComponent;
  let fixture: ComponentFixture<CreateLeagueFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateLeagueFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateLeagueFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
