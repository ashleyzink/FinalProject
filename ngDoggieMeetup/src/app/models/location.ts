import { Route } from './route';
export class Location {
  id: number;
  lat: number;
  lng: number;
  pointTime: string;
  route: Route;
  constructor(
    id?: number,
    lat?: number,
    lng?: number,
    pointTime?: string,
    route?: Route
  ){
    this.id = id;
    this.lat = lat;
    this.lng = lng;
    this.pointTime = pointTime;
    this.route = route;
  }
}
