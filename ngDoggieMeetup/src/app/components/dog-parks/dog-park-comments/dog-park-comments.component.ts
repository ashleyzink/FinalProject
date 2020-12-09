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

}
