import { MapsService } from './../../services/maps.service';
import { Component, OnInit } from '@angular/core';
import { Address } from 'src/app/models/address';
import { DogPark } from 'src/app/models/dog-park';
import { DogParkService } from 'src/app/services/dog-park.service';

@Component({
  selector: 'app-dog-parks-material',
  templateUrl: './dog-parks-material.component.html',
  styleUrls: ['./dog-parks-material.component.css']
})
export class DogParksMaterialComponent implements OnInit {

  selected: DogPark = null;
  newDogPark: DogPark = new DogPark();
  newAddress: Address = new Address();
  dogParks: DogPark[] = [];
  updateDogPark: DogPark = null;
  updateAddress: Address = new Address();
  revGeocodeAddress: Address = null;

  // maps
  zoom = 12
  center: google.maps.LatLngLiteral
  options: google.maps.MapOptions = {
    zoomControl: false,
    scrollwheel: false,
    disableDoubleClickZoom: true,
    maxZoom: 15,
    minZoom: 8,
  }
  markers: google.maps.Marker[] = [];

  constructor(private dogParkService: DogParkService, private mapsService: MapsService) { }

  ngOnInit(): void {
    this.reload();
    navigator.geolocation.getCurrentPosition((position) => {
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      }
    })
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

  getRevGeocodeAddress(lat: number, lng: number) {
    this.mapsService.getReverseGeocode(lat, lng).subscribe(
      data => {
        this.revGeocodeAddress = this.mapsService.parseReverseGeocode(data);
        console.log(this.revGeocodeAddress);
      },
      err => console.error('Error in getRevGeocodeAddress')
    )
  }

}
