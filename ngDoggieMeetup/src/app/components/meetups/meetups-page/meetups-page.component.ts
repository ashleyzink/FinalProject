import { MeetupService } from './../../../services/meetup.service';
import { Component, Input, OnInit } from '@angular/core';
import { Meetup } from 'src/app/models/meetup';
import { DogPark } from 'src/app/models/dog-park';

@Component({
  selector: 'app-meetups-page',
  templateUrl: './meetups-page.component.html',
  styleUrls: ['./meetups-page.component.css']
})
export class MeetupsPageComponent implements OnInit {

  meetups: Meetup[];
  @Input() dogPark: DogPark;
  newMeetup: Meetup;

  constructor(private meetupService: MeetupService) { }

  ngOnInit(): void {
    this.index();
  }

  setNewMeetup() {
    this.newMeetup = new Meetup();
    if (this.dogPark) {
      this.newMeetup.dogPark = this.dogPark;
    }
  }

  index() {
    this.meetupService.index().subscribe(
      data => this.meetups = data,
      err => console.error('error in meetups page index')
    )
  }

  create(meetup: Meetup) {
    this.meetupService.create(meetup).subscribe(
      data => {
        this.index();
      },
      err => console.error('error creating meetup')
    )
  }

}
