import { User } from './../../models/user';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User = new User();
  loginUser: User;

  constructor(private authService: AuthService,
              private router: Router) { }

  ngOnInit(): void {
  }

  createUser() {
    this.authService.register(this.newUser).subscribe(
      data => {
        const loginUser = this.newUser;
        this.newUser = new User();
        this.authService.login(loginUser.username, loginUser.password).subscribe(
          good => this.router.navigateByUrl('/home'),
          bad => console.log('Error in login after register')
        )
      },
      err => console.log('Error in register')
    )
  }

}
