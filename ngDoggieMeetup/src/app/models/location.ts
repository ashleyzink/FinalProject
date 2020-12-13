export class Location {
  id: number;
  lat: number;
  lng: number;
  pointTime: string;
  constructor(
    id?: number,
    lat?: number,
    lng?: number,
    pointTime?: string,
  ){
    this.id = id;
    this.lat = lat;
    this.lng = lng;
    this.pointTime = pointTime;
  }
}
