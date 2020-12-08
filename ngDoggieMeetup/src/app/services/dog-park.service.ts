import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { DogPark } from '../models/dog-park';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class DogParkService {

  constructor(private http: HttpClient,
    private authService: AuthService,
    private router: Router) { }
    private url = environment.baseUrl + 'api/dogParks/'
    private authUrl = environment.baseUrl + 'api/auth/dogParks/'

    getAuthHttpOptions(): Object {
      const credentials = this.authService.getCredentials();
      const httpOptions = {
        headers: new HttpHeaders({
          'Authorization': `Basic ${credentials}`,
          'X-Requested-With': 'XMLHttpRequest',
          'Content-type': 'application/json'
        })
      };
      return httpOptions;
    }
    getHttpOptions(): Object {
      const httpOptions = {
        headers: new HttpHeaders({
          'X-Requested-With': 'XMLHttpRequest',
          'Content-type': 'application/json'
        })
      };
      return httpOptions;
    }


    index(): Observable<DogPark[]> {
      const httpOptions = this.getHttpOptions();
      return this.http.get<DogPark[]>(this.url, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error getting dog parks index');
        })
      );
    }

    show(id: number): Observable<DogPark> {
      const httpOptions = this.getHttpOptions();
      return this.http.get<DogPark>(this.url + '/' + id, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error getting dog park id: ' + id);
        })
      );
    }

    create(newDogPark: DogPark): Observable<DogPark> {
      if(!this.authService.checkLogin) {
        console.log('not logged in at DogParkService.create() : redirect to /login')
        this.router.navigateByUrl('/login');
      }
      const httpOptions = this.getAuthHttpOptions();
      return this.http.post<DogPark>(this.authUrl, newDogPark, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error creating dog park');
        })
      );
    }

    update(dogPark: DogPark): Observable<DogPark> {
      if(!this.authService.checkLogin) {
        console.log('not logged in at DogParkService.update() : redirect to /login')
        this.router.navigateByUrl('/login');
      }
      const httpOptions = this.getAuthHttpOptions();
      return this.http.put<DogPark>(this.authUrl + dogPark.id, dogPark, httpOptions).pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError('error updating dog park id: ' + dogPark.id);
        })
      );
    }


  public delete(id: number): Observable<Object> {
    if(!this.authService.checkLogin) {
      console.log('not logged in at DogParkService.delete() : redirect to /login')
      this.router.navigateByUrl('/login');
    }
    const httpOptions = this.getAuthHttpOptions();
    return this.http.delete(this.authUrl + id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('Error deleting dog park id: ' + id);
      })
    );
  }
}
