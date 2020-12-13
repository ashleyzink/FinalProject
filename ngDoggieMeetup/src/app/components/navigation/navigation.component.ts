import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  checkLogin(): boolean {
    return this.authService.checkLogin();
  }

  checkLoginIsAdmin(): boolean {
    return this.authService.checkLoggedInUserIsAdmin();
  }

}
