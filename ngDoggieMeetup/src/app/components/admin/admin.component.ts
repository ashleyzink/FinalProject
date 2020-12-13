import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Dog } from 'src/app/models/dog';
import { User } from 'src/app/models/user';
import { AdminService } from 'src/app/services/admin.service';
import { AuthService } from 'src/app/services/auth.service';
import { DogService } from 'src/app/services/dog.service';
import { UserProfileService } from 'src/app/services/user-profile.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  user = null;
  selected = null;
  editUser: User = null;
  users = [];
  dogs = [];

  constructor(private adminService: AdminService,
              private userProfileService: UserProfileService,
              private dogService: DogService,
              private router: Router,
              private authService: AuthService) { }

  ngOnInit(): void {
    this.reload();
  }

  reload() {
    this.authService.reloadUserInMemory();
    this.adminService.indexUsers().subscribe(
      (user) => {
        this.users = user;

        // this.dogService.showAllUserDogs();
      },
      (fail) => {
        console.error('AdminComponent.reload(): error getting users')
        console.error(fail);
      });
  }

  displayUser(user: User) {
    this.selected = user;
  }

  displayUserTable(user: User): void {
    this.selected = null;
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

  disableUser(id: number): void {
    this.adminService.disable(id).subscribe(
      (data) => {
        this.reload();
      },
      (fail) => {
        console.error('AdminComponent.disableUser(): error disabling user');
        console.error(fail);
      }
    )
  }
  enableUser(id: number): void {
    this.adminService.enable(id).subscribe(
      (data) => {
        this.reload();
      },
      (fail) => {
        console.error('AdminComponent.enableUser(): error enabling user');
        console.error(fail);
      }
    )
  }

  setEditUser(user: User) {
    console.log(user);

    this.editUser = new User();
    Object.assign(this.editUser, user);
    console.log(this.editUser);

  }

  checkLoginIsAdmin(): boolean {
    return this.authService.checkLoggedInUserIsAdmin();
  }


}
