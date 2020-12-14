import { RouteService } from './../../../services/route.service';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Route } from 'src/app/models/route';
import { Location } from 'src/app/models/location';

@Component({
  selector: 'app-map-display',
  templateUrl: './map-display.component.html',
  styleUrls: ['./map-display.component.css']
})
export class MapDisplayComponent implements OnInit {
  user: User;
  zoom = 15
  center: google.maps.LatLngLiteral
  options: google.maps.MapOptions = {
    zoomControl: true,
    scrollwheel: true,
    disableDoubleClickZoom: true
  }
  myLocArr = []
  myPathOptions: google.maps.PolylineOptions = {
    geodesic: true,
    strokeColor: "#FF0000",
    strokeOpacity: 1.0,
    strokeWeight: 2,
    path: this.myLocArr,
  };
  testCoords = [
    { lat: 37.772, lng: -122.214 },
    { lat: 21.291, lng: -157.821 },
    { lat: -18.142, lng: 178.431 },
    { lat: -27.467, lng: 153.027 },
  ];
  myRoutes: Route[] = [];
  allMyPolylines: google.maps.Polyline[] = [];
  hideRoutes: boolean = true;
  currentLocation = {};
  routeDistances = {};
  distanceDisplay = 0;
  routePolyMapping = {};


  constructor(private authService: AuthService, private routeService: RouteService) { }

  ngOnInit(): void {
    navigator.geolocation.getCurrentPosition(
      position => { this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      };
      this.currentLocation = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      };
    }
    )
    this.authService.reloadUserInMemory()
    this.user = this.authService.getLoggedInUser();
    this.getUserRoutes();
  }

  updateMarker(location: Location) {
    this.currentLocation['lat'] = location.lat;
    this.currentLocation['lng'] = location.lng;
  }

  toggleRoutes(){
    if (this.hideRoutes) {
      this.drawAllRoutes(this.myRoutes);
      this.hideRoutes = false;
    } else {
      this.allMyPolylines = [];
      this.hideRoutes = true
    }
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
    // this.myLocArr = this.testCoords;
  }

  drawAllRoutes(routes: Route[]) {
    for (let i = 0; i < routes.length; i++) {
      let poly = new google.maps.Polyline();
      let path: google.maps.LatLngLiteral[] = [];
      routes[i].locations.forEach(element => {
        path.push({
          lat: element.lat,
          lng: element.lng
        })
      });
      poly.setPath(path);
      this.allMyPolylines.push(poly);
    }
  }

  getUserRoutes() {
    this.routeService.getUserRoutes().subscribe(
      data => {
        this.myRoutes = data
        console.log(this.myRoutes)
      },
      err => console.log(err)
    )
  }

  getDistance(polylinePath) {
    let len = 0;
    len = google.maps.geometry.spherical.computeLength(polylinePath);
    this.distanceDisplay = len;
  }



}
