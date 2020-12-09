import { TestBed } from '@angular/core/testing';

import { DogParkReviewService } from './dog-park-review.service';

describe('DogParkReviewService', () => {
  let service: DogParkReviewService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DogParkReviewService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
