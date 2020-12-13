import { Location } from './../models/location';
import { Route } from './../models/route';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TrackerService {

  route: Route = new Route();

  constructor() { }


  tracker = navigator.geolocation.watchPosition(
    data => {
      console.log(data);
      let loc = new Location;
      loc.lat = data.coords.latitude;
      loc.lng = data.coords.longitude;
      let d = new Date(data.timestamp);
      loc.pointTime = d.toISOString();
      this.route.locations.push(loc)
    }
  )
}

