import { TestBed } from '@angular/core/testing';

import { DogParkCommentService } from './dog-park-comment.service';

describe('DogParkCommentService', () => {
  let service: DogParkCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DogParkCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
