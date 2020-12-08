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
  dogParks: DogPark[] = [];

  constructor(private dogParkService: DogParkService) { }

  ngOnInit(): void {
    this.reload();
  }

  deselect() {
    this.selected = null;
  }
  select(item: DogPark) {
    this.selected = item;
  }

  reload():void {
    this.dogParkService.index().subscribe(
      data => this.dogParks = data,
      err => console.error('Observer got an error from reload: ' + err)
    )
  }


}
