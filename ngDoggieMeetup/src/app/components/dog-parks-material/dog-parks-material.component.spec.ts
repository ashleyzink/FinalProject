import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DogParksMaterialComponent } from './dog-parks-material.component';

describe('DogParksMaterialComponent', () => {
  let component: DogParksMaterialComponent;
  let fixture: ComponentFixture<DogParksMaterialComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DogParksMaterialComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DogParksMaterialComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
