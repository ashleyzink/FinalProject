import { AuthService } from 'src/app/services/auth.service';
import { MeetupService } from './../../../services/meetup.service';
import { Meetup } from 'src/app/models/meetup';
import { Component, Input, OnInit } from '@angular/core';
import { Dog } from 'src/app/models/dog';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-meetup-details',
  templateUrl: './meetup-details.component.html',
  styleUrls: ['./meetup-details.component.css']
})
export class MeetupDetailsComponent implements OnInit {

  @Input() meetup: Meetup;
  dogs: Dog[];

  constructor(private meetupService: MeetupService, private authService: AuthService) { }

  ngOnInit(): void {
    this.loadDogs();
  }

  loadDogs() {
    this.meetupService.getDogs(this.meetup).subscribe(
      data => this.dogs = data,
      err => console.error('error getting dogs for meetup')
    )
  }

  checkLogin(user: User): boolean {
    return this.authService.checkUserLoggedIn(user);
  }

}
