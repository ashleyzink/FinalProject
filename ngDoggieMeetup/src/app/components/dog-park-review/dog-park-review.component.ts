import { DogParkReview } from './../../models/dog-park-review';
import { DogParkReviewService } from './../../services/dog-park-review.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { NgForm } from '@angular/forms';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-dog-park-review',
  templateUrl: './dog-park-review.component.html',
  styleUrls: ['./dog-park-review.component.css'],
})
export class DogParkReviewComponent implements OnInit {
  selected: DogParkReview = null;
  newDogParkReview: DogParkReview = new DogParkReview();
  dogParkReviews: DogParkReview[] = [];
  updateDogParkReview: DogParkReview = null;
  dogParkId: number = 1;

  constructor(private dogParkReviewService: DogParkReviewService) {}

  displayTable() {
    this.selected = null;
  }

  displayDogParkReview(dogparkReview) {
    this.selected = dogparkReview;
  }

  ngOnInit(): void {
    this.reload();
  }

  deselect() {
    this.selected = null;
    this.updateDogParkReview = null;
  }

  select(item: DogParkReview) {
    this.selected = item;
    this.updateDogParkReview = null;
  }
  toggleUpdateReview() {
    if (this.updateDogParkReview) {
      this.updateDogParkReview = null;
    } else {
      this.updateDogParkReview = this.selected;
    }
  }

  reload(): void {
    this.dogParkReviewService.index(this.dogParkId).subscribe(
      (data) => {
        (this.dogParkReviews = data), console.log(data);
      },

      (err) => console.error('Error in reloading the dogParkReviews: ')
    );
  }

  getNumOfReviews = function () {
    return this.dogParkReviews.length;
  };
  addDogParkReview(addDogParkReviewForm: NgForm) {
    console.log(addDogParkReviewForm.value);
    this.dogParkReviews.push(this.newDogParkReview);
    this.dogParkReviewService
      .create(addDogParkReviewForm.value, this.dogParkId)
      .subscribe(
        (data) => {
          this.reload();
        },
        (err) => {
          console.error(err);
        }
      );
    this.dogParkReviewService.index(this.dogParkId).subscribe(
      (data) => {
        this.dogParkReviews = data;
      },
      (err) => {
        console.error(err);
      }
    );
    this.newDogParkReview = new DogParkReview();
    addDogParkReviewForm.reset();
    console.log(addDogParkReviewForm.value);
    console.log('Dog Park Review Added!!+++++****');
  }

  deleteDogParkReview(user: User, dogParkReview: DogParkReview): void {
    this.dogParkReviewService
      .deleteDogParkReview(user, dogParkReview)
      .subscribe((data) => {
        this.dogParkReviews = this.dogParkReviews.filter(
          (dogParkReview) => dogParkReview !== dogParkReview
        );
      });
  }
}
