import { RouteService } from './../../../services/route.service';
import { TrackerService } from './../../../services/tracker.service';
import { Route } from './../../../models/route';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { User } from 'src/app/models/user';
import { Location } from './../../../models/location';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-tracker',
  templateUrl: './tracker.component.html',
  styleUrls: ['./tracker.component.css']
})
export class TrackerComponent implements OnInit {

  @Input() user: User;
  route: Route;
  tracker;
  @Output('updatedPostion') updatePostion = new EventEmitter<Route>()
  @Output('submittedRoute') submitRoute = new EventEmitter<Route>()
  @Output('positionChanged') posChange = new EventEmitter<Location>()

  constructor(private routeService: RouteService) { }

  ngOnInit(): void {
  }





  newRoute() {
    this.route = new Route();
    this.route.user = this.user;
    let ms = Date.now();
    this.route.startTime = new Date(ms).toISOString();

    this.tracker = navigator.geolocation.watchPosition(
      data => {
        console.log(data);
        let loc = new Location;
        loc.lat = data.coords.latitude;
        loc.lng = data.coords.longitude;
        let d = new Date(data.timestamp);
        loc.pointTime = d.toISOString();
        this.route.locations.push(loc);
        this.updatePostion.emit(this.route);
        this.posChange.emit(loc);
      }
    )
  }
  endRoute() {
    let ms = Date.now();
    this.route.endTime = new Date(ms).toISOString();
    navigator.geolocation.clearWatch(this.tracker);
    console.log(this.route)
    // Should push to DB at this point
    this.routeService.addRoute(this.route).subscribe(
      data => {
        console.log(data);
        this.submitRoute.emit(data);
        this.route = null;
      },
      err => console.error(err)
    )
  }

}
