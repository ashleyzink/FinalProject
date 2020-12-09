import { Component, Input, OnInit } from '@angular/core';
import { DogParkComment } from 'src/app/models/dog-park-comment';

@Component({
  selector: 'app-dog-park-comment-display',
  templateUrl: './dog-park-comment-display.component.html',
  styleUrls: ['./dog-park-comment-display.component.css']
})
export class DogParkCommentDisplayComponent implements OnInit {

  @Input() comment: DogParkComment;

  constructor() { }

  ngOnInit(): void {
  }

}
