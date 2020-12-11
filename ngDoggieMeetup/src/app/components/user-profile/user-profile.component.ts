import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Dog } from 'src/app/models/dog';
import { User } from 'src/app/models/user';
import { DogService } from 'src/app/services/dog.service';
import { UserProfileService } from 'src/app/services/user-profile.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  user = null;
  selected = null;
  editUser: User = null;
  users = [];
  dogs = [];
  newDog: Dog = null;
  selectedDog = null;
  editDogProfile: Dog = null;
  constructor(private userProfileService: UserProfileService, private dogService: DogService, private router: Router) { }

  ngOnInit(): void {
    this.reload();
this.show();
  }

  reload() {
    this.userProfileService.index().subscribe(
      (user) => {
        this.user = user;
        this.showAllUserDogs();
      },
      (fail) => {
        console.error('UserProfileComponent.reload(): error getting users')
        console.error(fail);
      }
    );
  }


  create(dog: Dog, user: User){
    dog.user = user;
    this.dogService.create(dog).subscribe(
      data => {
        this.newDog = new Dog();
        this.selected = data;
        this.reload();
      },
      fail => {
        console.error(dog);
        console.error('Error in create() dog');
      });
    }

    addDog() {
      this.newDog = Object.assign({});
    }

  show() {
    this.userProfileService.show().subscribe(
      (user) => {
        this.reload();
        this.selected = user;
      },
      (fail) => {
      console.error('UserProfileComponent.show(): error getting user ')
      });
  }

  showUserDog(id: number) {
    console.log('user profile component - show use dog - user dogid ' + id);

    this.dogService.showUserDog(id).subscribe(
      data => {
        this.router.navigateByUrl('/dogUserProfile');
        this.reload();
        this.selected = data;
        },
        fail => {console.error('Error in showUserDog() dog')
      });
  }

  goToDogProfile(id: number){
    this.router.navigateByUrl('/dogUserProfile/' + id);
  }
  // goToDogEditProfile(id: number){
  //   this.router.navigateByUrl('/dogEditProfile/' + id);
  // }

  showAllUserDogs() {
    this.dogService.showAllUserDogs().subscribe(
      data => this.dogs = data,
      fail => console.error('Error showAllUserDogs() dog')
    )
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

  update(dog: Dog){
    console.log(dog);
    this.dogService.update(dog).subscribe(
      data => {
        this.editDogProfile = null;
        this.reload();
        this.selected = data;
      },
      fail => {
        console.error(dog);
        console.error('Error in update()');

      }
    )
  }
  setEditUser() {
    this.editUser = Object.assign({}, this.selected);
  }

  setEditDog(dog: Dog) {
    this.editDogProfile = new Dog();
    Object.assign(this.editDogProfile, dog);
  }


}
