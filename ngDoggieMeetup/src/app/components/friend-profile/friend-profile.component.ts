import { Router, ActivatedRoute } from '@angular/router';
import { UserProfileService } from 'src/app/services/user-profile.service';
import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-friend-profile',
  templateUrl: './friend-profile.component.html',
  styleUrls: ['./friend-profile.component.css'],
})
export class FriendProfileComponent implements OnInit {
  @Input() selectedUser: User;

  editFriend: User = null;
  friends = [];

  constructor(
    private userProfileService: UserProfileService,
    private router: Router,
    private currentRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    let id = this.currentRoute.snapshot.paramMap.get('id');
    this.getSelectedUser(Number.parseInt(id));
  }

  getSelectedUser(id: number) {
    this.userProfileService.showUserProfile(id).subscribe(
      (data) => {
        this.selectedUser = data;
      },
      (fail) => console.error('Failed getting user information')
    );
  }
  setEditFriend() {
    this.editFriend = Object.assign({}, this.selectedUser);
  }

  userProfilePage() {
    this.router.navigateByUrl('/userProfile');

  }


  // userProfilePage() {
  //   this.userProfileService.userProfilePage().subscribe(
  //     (data) => {
  //       this.friends = data;
  //       this.router.navigateByUrl('/userProfile');
  //     },
  //     (fail) => console.error('Error showing showAllUsers()')
  //   );
  // }
}
