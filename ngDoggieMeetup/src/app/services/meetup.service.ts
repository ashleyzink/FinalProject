import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class MeetupService {

  constructor(private http: HttpClient,
    private authService: AuthService,
    private router: Router) { }
}
