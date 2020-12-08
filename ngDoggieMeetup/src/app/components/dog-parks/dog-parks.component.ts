import { Address } from './../../models/address';
import { DogParkService } from './../../services/dog-park.service';
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
  newAddress: Address = new Address();
  dogParks: DogPark[] = [];
  updateDogPark: DogPark = null;
  updateAddress: Address = new Address();

  constructor(private dogParkService: DogParkService) { }

  ngOnInit(): void {
    this.reload();
  }

  deselect() {
    this.selected = null;
    this.updateDogPark = null;
    this.updateAddress = null;
  }
  select(item: DogPark) {
    this.selected = item;
    this.updateDogPark = null;
    this.updateAddress = null;
  }
  toggleUpdate() {
    if (this.updateDogPark) {
      this.updateDogPark = null;
    } else {
      this.updateDogPark = this.selected;
      this.updateAddress = this.selected.address;
    }
  }


  reload():void {
    this.dogParkService.index().subscribe(
      data => this.dogParks = data,
      err => console.error('Error reloading dogParks: ')
    )
  }

  show(id: number) {
    this.dogParkService.show(id).subscribe(
      data => {
        this.reload();
        this.selected = data;
      },
      err => console.error('Error getting dogPark: ' + id)
    )
  }

  create(dogPark: DogPark, address: Address) {
    dogPark.address = address;
    this.dogParkService.create(dogPark).subscribe(
      data => {
        this.reload();
        this.selected = data;
      },
      err => {
        console.error('Error creating dogPark: ');
        console.error(dogPark);
      }
    )
  }

  update(dogPark: DogPark, address?: Address) {
    if (address) {
      dogPark.address = address;
    }
    this.dogParkService.update(dogPark).subscribe(
      data => {
        this.reload();
        this.selected = data;
      },
      err => {
        console.error('Error creating dogPark: ');
        console.error(dogPark);
      }
    )
  }

  delete(id: number) {
    this.dogParkService.delete(id).subscribe(
      good => {this.reload(); this.deselect();},
      err => console.error('Error deleting dogPark with id: ' + id)
    )
  }




}
