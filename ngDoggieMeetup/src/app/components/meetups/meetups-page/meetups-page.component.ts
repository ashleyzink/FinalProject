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

  constructor(private meetupService: MeetupService) { }

  ngOnInit(): void {
    this.index();
  }

  index() {
    this.meetupService.index().subscribe(
      data => this.meetups = data,
      err => console.error('error in meetups page index')
    )
  }

}
