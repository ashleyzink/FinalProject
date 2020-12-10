import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MeetupsPageComponent } from './meetups-page.component';

describe('MeetupsPageComponent', () => {
  let component: MeetupsPageComponent;
  let fixture: ComponentFixture<MeetupsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MeetupsPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MeetupsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
