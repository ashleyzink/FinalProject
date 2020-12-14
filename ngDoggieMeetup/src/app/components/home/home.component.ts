
import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  images = ['https://images2.minutemediacdn.com/image/upload/c_fill,g_auto,h_1248,w_2220/f_auto,q_auto,w_1100/v1555302690/shape/mentalfloss/1dogwood.jpg',
            'https://images.dailyhive.com/20180808134301/shutterstock_1015812967.jpg',
            'https://patch.com/img/cdn/users/456577/2011/05/raw/b85204422a2735e414af80ccc2c4ddb1.jpg',
            'https://bloximages.newyork1.vip.townnews.com/herald-dispatch.com/content/tncms/assets/v3/editorial/d/02/d02f6cea-ba30-11e9-8896-2fed2700be63/5d4caa6288a7c.image.jpg?resize=1200%2C800'];

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }
  checkLogin(): boolean {
    return this.authService.checkLogin();
  }

}
