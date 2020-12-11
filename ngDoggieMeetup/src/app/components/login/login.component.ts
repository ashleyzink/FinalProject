import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  showErrorMessage = null;
  loginUser: User = new User();

  constructor(private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
  }

  login() {
    this.showErrorMessage = false;
    this.authService.login(this.loginUser.username, this.loginUser.password).subscribe(
      data => {
        this.router.navigateByUrl('/home')
      },
      err => {console.log('Error in login')
      this.showErrorMessage = true;
    });
  }

  registerPage() {
        this.router.navigateByUrl('/register')
  }

}
