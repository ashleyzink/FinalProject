import { TestBed } from '@angular/core/testing';

import { DogParkService } from './dog-park.service';

describe('DogParkService', () => {
  let service: DogParkService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DogParkService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
