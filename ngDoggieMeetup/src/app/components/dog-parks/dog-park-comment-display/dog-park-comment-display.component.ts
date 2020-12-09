import { DogParkCommentService } from './../../../services/dog-park-comment.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { DogParkComment } from 'src/app/models/dog-park-comment';

@Component({
  selector: 'app-dog-park-comment-display',
  templateUrl: './dog-park-comment-display.component.html',
  styleUrls: ['./dog-park-comment-display.component.css']
})
export class DogParkCommentDisplayComponent implements OnInit {

  @Input() comment: DogParkComment;

  @Output() contentChange = new EventEmitter<boolean>();

  showReplies: boolean = false;

  constructor(private dogParkCommentService: DogParkCommentService) { }

  ngOnInit(): void {
  }

  onContentChange(change: boolean) {
    console.log('in onContentChange()')
    this.contentChange.emit(change);
  }

  toggleReplies() {
    this.showReplies = !this.showReplies;
  }

  create(comment: DogParkComment) {
    this.dogParkCommentService.create(comment.dogPark.id, comment).subscribe(
      data => this.contentChange.emit(true),
      err => console.error('Error in create')
    )
  }

  update(comment: DogParkComment) {
    this.dogParkCommentService.update(comment.dogPark.id, comment).subscribe(
      data => this.contentChange.emit(true),
      err => console.error('Error in update')
    )
  }

  delete(comment: DogParkComment) {
    this.dogParkCommentService.delete(comment.dogPark.id, comment.id).subscribe(
      data => this.contentChange.emit(true),
      err => console.error('Error in delete')
    )
  }

}
