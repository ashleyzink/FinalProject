import { ActivatedRoute, Router } from '@angular/router';
import { UserProfileService } from './../../services/user-profile.service';
import { DogService } from 'src/app/services/dog.service';
import { Component, Input, OnInit } from '@angular/core';
import { Dog } from 'src/app/models/dog';

@Component({
  selector: 'app-dog-profile',
  templateUrl: './dog-profile.component.html',
  styleUrls: ['./dog-profile.component.css']
})
export class DogProfileComponent implements OnInit {

 @Input() selectedDog: Dog;

 editDog: Dog = null;


  constructor(private dogService: DogService, private router: Router, private currentRoute: ActivatedRoute) { }

    ngOnInit(): void {
      let id = this.currentRoute.snapshot.paramMap.get('id');
      this.getSelectedDog(Number.parseInt(id));

  }

  getSelectedDog(id: number){
    this.dogService.showUserDog(id).subscribe(
        data => {
          this.selectedDog = data;
          // this.router.navigateByUrl('/dogUserProfile');
        },
        fail => console.error('Failed getting users dog details')
    )
    }
 setEditDog() {
    this.editDog = Object.assign({}, this.selectedDog);
  }



}
