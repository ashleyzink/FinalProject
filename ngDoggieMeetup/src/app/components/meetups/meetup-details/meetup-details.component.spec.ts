import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MeetupDetailsComponent } from './meetup-details.component';

describe('MeetupDetailsComponent', () => {
  let component: MeetupDetailsComponent;
  let fixture: ComponentFixture<MeetupDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MeetupDetailsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MeetupDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
