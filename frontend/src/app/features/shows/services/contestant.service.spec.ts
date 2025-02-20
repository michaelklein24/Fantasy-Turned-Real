import { TestBed } from '@angular/core/testing';

import { ContestantService } from './contestant.service';

describe('ShowService', () => {
  let service: ContestantService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ContestantService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
