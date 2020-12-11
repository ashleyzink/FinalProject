import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DogParkCommentsComponent } from './dog-park-comments.component';

describe('DogParkCommentsComponent', () => {
  let component: DogParkCommentsComponent;
  let fixture: ComponentFixture<DogParkCommentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DogParkCommentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DogParkCommentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
