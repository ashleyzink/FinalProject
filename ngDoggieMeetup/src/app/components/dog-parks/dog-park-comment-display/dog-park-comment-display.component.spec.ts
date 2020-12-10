import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DogParkCommentDisplayComponent } from './dog-park-comment-display.component';

describe('DogParkCommentDisplayComponent', () => {
  let component: DogParkCommentDisplayComponent;
  let fixture: ComponentFixture<DogParkCommentDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DogParkCommentDisplayComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DogParkCommentDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
