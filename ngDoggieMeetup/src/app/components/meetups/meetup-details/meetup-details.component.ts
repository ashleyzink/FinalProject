import { AuthService } from 'src/app/services/auth.service';
import { MeetupService } from './../../../services/meetup.service';
import { Meetup } from 'src/app/models/meetup';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
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
  editMeetup: Meetup = null;
  @Output() contentChange = new EventEmitter<boolean>();

  constructor(private meetupService: MeetupService, private authService: AuthService) { }

  ngOnInit(): void {
    this.loadDogs();
  }

  setEdit() {
    this.editMeetup = Object.assign({}, this.meetup);
  }

  sendContentChange(change: boolean) {
    this.contentChange.emit(change);
  }

  update(meetup: Meetup) {
    this.meetupService.update(meetup).subscribe(
      data => {
        this.meetup = data;
        this.editMeetup = null;
        this.sendContentChange(true);
      },
      err => console.error('error in update')
    )
  }

  loadDogs() {
    this.meetupService.getDogs(this.meetup).subscribe(
      data => this.dogs = data,
      err => console.error('error getting dogs for meetup')
    )
  }

  checkLoginMatchesUser(user: User): boolean {
    return this.authService.checkUserLoggedIn(user);
  }

  checkLoginIsAdmin(): boolean {
    return this.authService.checkLoggedInUserIsAdmin();
  }

}
