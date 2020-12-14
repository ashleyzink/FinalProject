import { Component, OnInit } from '@angular/core';
import { Dog } from 'src/app/models/dog';
import { User } from 'src/app/models/user';
import { AdminService } from 'src/app/services/admin.service';
import { AuthService } from 'src/app/services/auth.service';
import { DogService } from 'src/app/services/dog.service';
import { UserProfileService } from 'src/app/services/user-profile.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  user = null;
  dog = null;
  selected = null;
  selectedDog = null;
  editUser: User = null;
  editDog: Dog = null;
  users = [];
  dogs = [];

  constructor(
    private adminService: AdminService,
    private userProfileService: UserProfileService,
    private dogService: DogService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.reload();
  }

  reload() {
    this.authService.reloadUserInMemory();
    this.adminService.indexUsers().subscribe(
      (user) => {
        this.users = user;
      },
      (fail) => {
        console.error('AdminComponent.reload(): error getting users');
        console.error(fail);
      }
    );
    this.adminService.indexDogs().subscribe(
      (dog) => {
        this.dogs = dog;
      },
      (fail) => {
        console.error('AdminComponent.reload(): error getting dogs');
        console.error(fail);
      }
    );
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
        if (this.selected != null) {
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
    );
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
    );
  }

  setEditUser(user: User) {
    console.log(user);

    this.editUser = new User();
    Object.assign(this.editUser, user);
    console.log(this.editUser);
  }

  deleteDog(id: number){
    this.adminService.destroyDog(id).subscribe(
      data =>{
        this.reload();
      },
      fail =>{
        console.error('Error in destroy() of dog');
      }
    );
  }


  displayDog(dog: Dog) {
    this.selectedDog = dog;
  }

  displayDogTable(dog: Dog): void {
    this.selectedDog = null;
  }

  checkLoginIsAdmin(): boolean {
    return this.authService.checkLoggedInUserIsAdmin();
  }
}
