import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Route } from 'src/app/models/route';

@Component({
  selector: 'app-map-display',
  templateUrl: './map-display.component.html',
  styleUrls: ['./map-display.component.css']
})
export class MapDisplayComponent implements OnInit {
  user: User;
  zoom = 12
  center: google.maps.LatLngLiteral
  options: google.maps.MapOptions = {
    zoomControl: true,
    scrollwheel: true,
    disableDoubleClickZoom: true
  }
  myPath = new google.maps.Polyline({
    geodesic: true,
    strokeColor: "#FF0000",
    strokeOpacity: 1.0,
    strokeWeight: 2,
    path: []
  });
  myLocArr = []

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    navigator.geolocation.getCurrentPosition(
      position => this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      }
    )
    this.authService.reloadUserInMemory()
    this.user = this.authService.getLoggedInUser();
  }

  drawRoute(route: Route) {
    let locArr: google.maps.LatLngLiteral[] = []
    for (let i = 0; i < route.locations.length; i++) {
      let loc: google.maps.LatLngLiteral = {
        lat: route.locations[i].lat,
        lng: route.locations[i].lng
      }
      locArr.push(loc)
    }
    this.myLocArr = locArr;
    this.myPath.setPath(locArr)
    console.log(this.myPath);
  }



}
