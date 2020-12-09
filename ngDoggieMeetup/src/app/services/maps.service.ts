import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Address } from '../models/address';

@Injectable({
  providedIn: 'root'
})
export class MapsService {

  private baseUrl = 'https://maps.googleapis.com/maps/api/';
  private geocodeUrl = this.baseUrl + 'geocode/json?address='
  private reverseGeocodeUrl = this.baseUrl + 'geocode/json?latlng='
  private key = 'AIzaSyBHmCXr5IdHwRvUCAvA_cmXjJadZe0Ldzw'

  constructor(private http: HttpClient) { }

  // Geocode results
  // results[]: {
  //   types[]: string,
  //   formatted_address: string,
  //   address_components[]: {
  //     short_name: string,
  //     long_name: string,
  //     postcode_localities[]: string,
  //     types[]: string
  //   },
  //   partial_match: boolean,
  //   place_id: string,
  //   postcode_localities[]: string,
  //   geometry: {
  //     location: LatLng,
  //     location_type: GeocoderLocationType
  //     viewport: LatLngBounds,
  //     bounds: LatLngBounds
  //   }
  //  }

  getGeocode(place: string) {
    console.log(place);
    return this.http.get<Object>(this.geocodeUrl + place + '&key=' + this.key).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error loading jobs');
      })
    );
  }

  getReverseGeocode(lat: number, lng: number) {
    // https://maps.googleapis.com/maps/api/geocode/json?latlng=40.714224,-73.961452&key=YOUR_API_KEY
    return this.http.get<Object>(this.reverseGeocodeUrl + lat + ',' + lng + '&key=' + this.key).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('error loading jobs');
      })
    );
  }

  parseReverseGeocode(input) {
    let addr: Address = new Address();
    console.log(input);
    addr.street = input['results'][0]['address_components'][0]['long_name'];
    addr.street += ' ' + input['results'][0]['address_components'][1]['long_name'];
    addr.city = input['results'][0]['address_components'][2]['long_name'];
    if (input['results'][0]['address_components'][4]['short_name']['length'] === 2) {
      addr.stateAbbrv = input['results'][0]['address_components'][4]['short_name'];
    } else {
      addr.stateAbbrv = input['results'][0]['address_components'][5]['short_name'];
    }
    addr.zipcode = input['results'][0]['address_components'][6]['long_name'];
    return addr;
  }
}
