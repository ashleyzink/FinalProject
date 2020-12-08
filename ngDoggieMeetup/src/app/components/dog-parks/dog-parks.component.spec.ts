import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DogParksComponent } from './dog-parks.component';

describe('DogParksComponent', () => {
  let component: DogParksComponent;
  let fixture: ComponentFixture<DogParksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DogParksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DogParksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
