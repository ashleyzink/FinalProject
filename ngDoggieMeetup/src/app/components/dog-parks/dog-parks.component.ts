import { DogPark } from './../../models/dog-park';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dog-parks',
  templateUrl: './dog-parks.component.html',
  styleUrls: ['./dog-parks.component.css']
})
export class DogParksComponent implements OnInit {

  selected: DogPark = null;
  newDogPark: DogPark = new DogPark();
  dogParks: DogPark[] = [];

  constructor() { }

  ngOnInit(): void {
  }

  deselect() {
    this.selected = null;
  }
  select(item: DogPark) {
    this.selected = item;
  }


}
