import { DogParkCommentService } from './../../../services/dog-park-comment.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { DogParkComment } from 'src/app/models/dog-park-comment';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-dog-park-comment-display',
  templateUrl: './dog-park-comment-display.component.html',
  styleUrls: ['./dog-park-comment-display.component.css']
})
export class DogParkCommentDisplayComponent implements OnInit {

  @Input() comment: DogParkComment;

  @Output() contentChange = new EventEmitter<boolean>();

  @Output('contentHasChanged') contentChange2 = new EventEmitter<boolean>();

  @Input() user: User;

  showReplies: boolean = false;
  newReply: DogParkComment = null;
  updateComment: DogParkComment = null;


  constructor(private dogParkCommentService: DogParkCommentService, private authService: AuthService) { }

  ngOnInit(): void {

  }

  setUpdate() {
    this.updateComment = this.comment;
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

  reply() {
    this.newReply = new DogParkComment();
    this.newReply.title = 'Reply to #'+this.comment.id;
    this.newReply.dogPark = this.comment.dogPark;
  }

  onContentChange(change: boolean) {
    console.log('in onContentChange()')
    this.contentChange.emit(change);
    this.contentChange2.emit(change);
  }

  toggleReplies() {
    this.showReplies = !this.showReplies;
  }

  create(comment: DogParkComment) {
    this.dogParkCommentService.create(comment.dogPark.id, comment).subscribe(
      data => this.contentChange.emit(true),
      err => console.error('Error in create')
    )
    this.newReply = null;
  }

  update(comment: DogParkComment) {
    this.dogParkCommentService.update(comment.dogPark.id, comment).subscribe(
      data => this.contentChange.emit(true),
      err => console.error('Error in update')
    )
    this.updateComment = null;
  }

  delete(comment: DogParkComment) {
    this.dogParkCommentService.delete(comment.dogPark.id, comment.id).subscribe(
      data => this.contentChange.emit(true),
      err => console.error('Error in delete')
    )
  }

}
