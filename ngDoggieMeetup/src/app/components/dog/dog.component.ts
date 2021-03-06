import { AuthService } from 'src/app/services/auth.service';
import { UserProfileService } from './../../services/user-profile.service';
import { User } from './../../models/user';
import { DogService } from './../../services/dog.service';
import { Dog } from './../../models/dog';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dog',
  templateUrl: './dog.component.html',
  styleUrls: ['./dog.component.css']
})
export class DogComponent implements OnInit {

  title = 'Meet the Doggies';

  selected: Dog = null;
  newDog: Dog = null;
  editDog: Dog = null;
  dogs: Dog[] = [];
  newUser: User = new User();
  constructor(private dogService: DogService, private userProfileService: UserProfileService, private authService: AuthService) { }

  ngOnInit(): void {
    this.reload();

  }
  reload(): void{
    this.dogService.index().subscribe(
      data => this.dogs = data,
      fail => console.error('Error reload() dog')
    )
  }
  select(dog: Dog){
    this.selected = dog;
    this.editDog = null;
  }
  displayDogDetails(dog: Dog){
    this.selected = dog;
  }
  show(id: number){
    this.dogService.show(id).subscribe(
      data => {
      this.reload();
      this.selected = data;
      },
      fail => console.error('Error in show() dog')

      )
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
  update(dog: Dog){
    console.log(dog);
    this.dogService.update(dog).subscribe(
      data => {
        this.editDog = null;
        this.reload();
        this.selected = data;
      },
      fail => {
        console.error(dog);
        console.error('Error in update()');

      }
    )
  }

  addDog() {
    this.newDog = Object.assign({});
  }
  setEditDog() {
    this.editDog = Object.assign({}, this.selected);
  }

  delete(id: number){
    this.dogService.destroy(id).subscribe(
      data =>{
        this.reload();

      },
      fail =>{
        console.error('Error in destroy() of dog');
      }
    );
  }

  checkLoginMatchesUser(user: User): boolean {
    return this.authService.checkUserLoggedIn(user);
  }
  checkLoginIsAdmin(): boolean {
    return this.authService.checkLoggedInUserIsAdmin();
  }
  getLoggedInUser(): User {
    return this.authService.getLoggedInUser();
  }


}
