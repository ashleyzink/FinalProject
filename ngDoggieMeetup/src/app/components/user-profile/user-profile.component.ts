import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserProfileService } from 'src/app/services/user-profile.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user: User = new User();
  selected = null;
  editUser: User = null;
  users = [];
  constructor(private userProfileService: UserProfileService) { }

  ngOnInit(): void {
    this.reload();
this.show();
  }

  reload() {
    this.userProfileService.index().subscribe(
      (users) => {
        this.users = users;
      },
      (fail) => {
        console.error('UserProfileComponent.reload(): error getting users')
        console.error(fail);
      }
    );
  }

  show() {
    this.userProfileService.show().subscribe(
      (user) => {
        this.reload();
        this.selected = user;
      },
      (fail) =>
      console.error('UserProfileComponent.show(): error getting user ')
    );
  }

  updateUser(user: User) {
    this.userProfileService.update(user).subscribe(
      (data) => {
        this.editUser = null;
        this.reload();
        if(this.selected != null) {
          this.selected = data;
        }
      },
      (fail) => {
        console.error('UserProfileComponent.updateUser(): error updating user');
        console.error(fail);
      }
    );
  }

  setEditUser() {
    this.editUser = Object.assign({}, this.selected);
  }

}
