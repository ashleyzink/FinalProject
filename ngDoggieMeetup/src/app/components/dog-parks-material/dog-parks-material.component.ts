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
  dogParksMapToLabels = [];
  dogParks: DogPark[] = [];
  updateDogPark: DogPark = null;
  updateAddress: Address = new Address();
  revGeocodeAddress: Address = null;
  stateAbbrevs: string[] = ['AK', 'AL', 'AR', 'AS', 'AZ', 'CA', 'CO', 'CT',
                            'DC', 'DE', 'FL', 'GA', 'GU', 'HI', 'IA', 'ID',
                            'IL', 'IN', 'KS', 'KY', 'LA', 'MA', 'MD', 'ME',
                            'MI', 'MN', 'MO', 'MP', 'MS', 'MT', 'NC', 'ND',
                            'NE', 'NH', 'NJ', 'NM', 'NV', 'NY', 'OH', 'OK',
                            'OR', 'PA', 'PR', 'RI', 'SC', 'SD', 'TN', 'TX',
                            'UM', 'UT', 'VA', 'VI', 'VT', 'WA', 'WI', 'WV',
                            'WY'];

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
  markers = [];
  dogParkMarkerOptions: google.maps.MarkerOptions = {draggable: false};

  currentLocation: google.maps.Marker = new google.maps.Marker();

  markerOptions: google.maps.MarkerOptions = {draggable: true};
  newLocation = {lat: 0, lng: 0};

  constructor(private dogParkService: DogParkService, private mapsService: MapsService) { }

  ngOnInit(): void {
    this.reload();
    navigator.geolocation.getCurrentPosition((position) => {
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      }
      this.currentLocation.setPosition(this.center);
      this.currentLocation.setOptions(this.markerOptions);
      this.newLocation = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      }

      // this.markers.push(this.currentLocation);
    })
  }

  test(item) {
    console.log(item)
  }
  test2(event: google.maps.MouseEvent) {
    console.log(event.latLng.toJSON());
    this.getRevGeocodeAddress2(event.latLng.toJSON());
  }
  test3(event: google.maps.MouseEvent) {
    console.log(event.latLng.toJSON());
    this.getRevGeocodeAddress3(event.latLng.toJSON());
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
  selectFromMap(item) {
    this.selected = this.dogParks[Number.parseInt(item['label']) - 1];
    this.center = item['position'];
  }
  toggleUpdate() {
    if (this.updateDogPark) {
      this.updateDogPark = null;
      this.dogParkMarkerOptions = {draggable: false};
    } else {
      this.updateDogPark = this.selected;
      this.updateAddress = this.selected.address;
      this.dogParkMarkerOptions = {draggable: true};
    }
  }


  reload():void {
    this.dogParkService.index().subscribe(
      data => {
        this.dogParks = data;
        for (let i = 0; i < this.dogParks.length; i++) {
          this.geocodeDogPark(this.dogParks[i], i+1+'');

        }
      },
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

  geocodeDogPark(dogPark: DogPark, label: string) {

    let addrStr = `${dogPark.address.street} ${dogPark.address.city}, ${dogPark.address.stateAbbrv}`;
    this.mapsService.getGeocode(addrStr).subscribe(
      data => {
        let marker = {
          position: data['results'][0]['geometry']['location'],
          label: label,
          title: dogPark.name
        }
        this.markers.push(marker);

      }
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

  getRevGeocodeAddress2(latLng: google.maps.LatLngLiteral) {
    this.mapsService.getReverseGeocode(latLng.lat, latLng.lng).subscribe(
      data => {
        this.revGeocodeAddress = this.mapsService.parseReverseGeocode(data);
        this.newAddress = this.revGeocodeAddress;
        console.log(this.revGeocodeAddress);
      },
      err => console.error('Error in getRevGeocodeAddress')
    )
  }

  getRevGeocodeAddress3(latLng: google.maps.LatLngLiteral) {
    this.mapsService.getReverseGeocode(latLng.lat, latLng.lng).subscribe(
      data => {
        this.revGeocodeAddress = this.mapsService.parseReverseGeocode(data);
        this.updateDogPark.address = this.revGeocodeAddress;
        console.log(this.revGeocodeAddress);
      },
      err => console.error('Error in getRevGeocodeAddress')
    )
  }

}
