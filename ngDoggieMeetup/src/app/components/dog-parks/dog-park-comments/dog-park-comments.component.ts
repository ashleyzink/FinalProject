import { DogParkCommentService } from './../../../services/dog-park-comment.service';
import { Component, Input, OnInit } from '@angular/core';
import { DogParkComment } from 'src/app/models/dog-park-comment';

@Component({
  selector: 'app-dog-park-comments',
  templateUrl: './dog-park-comments.component.html',
  styleUrls: ['./dog-park-comments.component.css']
})
export class DogParkCommentsComponent implements OnInit {

  @Input() dogParkId: number;
  rootComments: DogParkComment[];

  constructor(private dogParkCommentService: DogParkCommentService) { }

  onContentChange(change: boolean) {
    console.log('in onContentChange()')
    this.reload();
  }

  ngOnInit(): void {
    console.log('comments for dog park id: ' + this.dogParkId)
    this.reload();
  }

  reload() {
    this.dogParkCommentService.index(this.dogParkId).subscribe(
      data => this.rootComments = data,
      err => console.error('Error reloading dog park comments')
    )
  }

  create(comment: DogParkComment) {
    this.dogParkCommentService.create(this.dogParkId, comment).subscribe(
      data => this.reload(),
      err => console.error('Error in create')
    )
  }

  update(comment: DogParkComment) {
    this.dogParkCommentService.update(this.dogParkId, comment).subscribe(
      data => this.reload(),
      err => console.error('Error in update')
    )
  }

  delete(comment: DogParkComment) {
    this.dogParkCommentService.delete(this.dogParkId, comment.id).subscribe(
      data => this.reload(),
      err => console.error('Error in delete')
    )
  }

}
