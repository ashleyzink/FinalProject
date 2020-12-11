import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DogParkReviewComponent } from './dog-park-review.component';

describe('DogParkReviewComponent', () => {
  let component: DogParkReviewComponent;
  let fixture: ComponentFixture<DogParkReviewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DogParkReviewComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DogParkReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
