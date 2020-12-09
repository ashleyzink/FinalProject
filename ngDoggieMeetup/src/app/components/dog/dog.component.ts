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

  title = 'Dog Profile';

  selected: Dog = null;

  newDog: Dog = new Dog();

  editDog: Dog = null;

  dogs: Dog[] = [];

  newUser: User = new User();

  constructor(private dogService: DogService) { }

  ngOnInit(): void {
    this.reload();

  }
  reload(): void{
    this.dogService.index().subscribe(
      data => this.dogs = data,
      fail => console.error('Error reload() dog')
    )
  }
  select(item: Dog){
    this.selected = item;
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
        this.reload();
        this.selected = data;
      },
      fail => {
        console.error(dog);
        console.error('Error in create() dog');
      }
      )
    }
  update(dog: Dog, user?: User){
    if(user){
      dog.user = user;
    }
    this.dogService.update(dog).subscribe(
      data => {
        this.reload();
        this.selected = data;
      },
      fail => {
        console.error(dog);
        console.error('Error in update()');

      }
    )
  }
  delete(id: number){
    this.dogService.destroy(id).subscribe(
      data =>{
        this.reload();

      },
      fail =>{
        console.error('Error in destroy() of dog');

      }
    )
  }


}
