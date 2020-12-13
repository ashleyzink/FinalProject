import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { DogPark } from './../../../models/dog-park';
import { DogParkCommentService } from './../../../services/dog-park-comment.service';
import { Component, Input, OnInit } from '@angular/core';
import { DogParkComment } from 'src/app/models/dog-park-comment';

@Component({
  selector: 'app-dog-park-comments',
  templateUrl: './dog-park-comments.component.html',
  styleUrls: ['./dog-park-comments.component.css']
})
export class DogParkCommentsComponent implements OnInit {

  @Input() dogPark: DogPark;
  rootComments: DogParkComment[];
  newComment: DogParkComment = new DogParkComment();
  user: User;

  constructor(private dogParkCommentService: DogParkCommentService, private authService: AuthService) { }

  onContentChange(change: boolean) {
    console.log('in onContentChange()')
    this.reload();
  }

  ngOnInit(): void {
    console.log('comments for dog park id: ' + this.dogPark.id)
    this.reload();
    this.authService.reloadUserInMemory();
  }

  checkLoginMatchesUser(user: User): boolean {
    return this.authService.checkUserLoggedIn(user);
  }

  checkLoginIsAdmin(): boolean {
    return this.authService.checkLoggedInUserIsAdmin();
  }

  getLoggedInUser(): User {
    return this.authService.getLoggedInUser();
  }

  ngOnChanges(): void {
    this.reload();
  }

  reload() {
    this.dogParkCommentService.index(this.dogPark.id).subscribe(
      data => this.rootComments = data,
      err => console.error('Error reloading dog park comments')
    )
  }

  create(comment: DogParkComment) {
    comment.dogPark = this.dogPark;
    this.dogParkCommentService.create(this.dogPark.id, comment).subscribe(
      data => this.reload(),
      err => console.error('Error in create')
    )
    this.newComment = new DogParkComment();
  }

  update(comment: DogParkComment) {
    this.dogParkCommentService.update(this.dogPark.id, comment).subscribe(
      data => this.reload(),
      err => console.error('Error in update')
    )
  }

  delete(comment: DogParkComment) {
    this.dogParkCommentService.delete(this.dogPark.id, comment.id).subscribe(
      data => this.reload(),
      err => console.error('Error in delete')
    )
  }

}
