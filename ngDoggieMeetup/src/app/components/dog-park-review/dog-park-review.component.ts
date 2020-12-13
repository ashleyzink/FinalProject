import { DogParkService } from './../../services/dog-park.service';
import { DogPark } from 'src/app/models/dog-park';
import { DogParkReviewId } from './../../models/dog-park-review-id';
import { DogParkReview } from './../../models/dog-park-review';
import { DogParkReviewService } from './../../services/dog-park-review.service';
import { Component, Input, OnInit } from '@angular/core';
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
  editDogParkReview: DogParkReview = null;
  dogParks: DogPark[] = [];
  @Input() dogParkId: number;
  @Input() dogParkSelected: DogPark;
  displayTable() {
    this.selected = null;
  }

  constructor(private dogParkReviewService: DogParkReviewService, private dogParkService: DogParkService) {}

  displayDogParkReview(dogparkReview) {
    this.selected = dogparkReview;
  }

  getDogParkIdFromName(input: string): number {
    for (let i = 0; i < this.dogParks.length; i++) {
      if (this.dogParks[i].name == input) {
        return this.dogParks[i].id;
      }
    }
    return null;
  }

  ngOnInit(): void {
    if (this.dogParkId && this.dogParkSelected) {
      this.dogParks.push(this.dogParkSelected)
    } else {
      this.loadDogParks();
    }
    this.reload();
  }

  loadDogParks() {
    this.dogParkService.index().subscribe(
      data => this.dogParks = data,
      error => console.error('error loading dog parks for dog park reviews')
    )
  }

  reload(): void {
    this.dogParkReviewService.index(this.dogParkId).subscribe(
      (data) => {
        this.dogParkReviews = data;
        console.log(data);
      },

      (err) => console.error('Error in reloading the dogParkReviews: ')
    );
  }

  getNumOfReviews = function () {
    return this.dogParkReviews.length;
  };

  addDogParkReview(addDogParkReviewForm: NgForm) {
    console.log(addDogParkReviewForm.value);
    let id;
    if (!this.dogParkId) {
      id = this.getDogParkIdFromName(addDogParkReviewForm.value['name']);
    } else {
      id = this.dogParkId;
    }
    this.dogParkReviews.push(this.newDogParkReview);
    this.dogParkReviewService
      .create(addDogParkReviewForm.value, id)
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

  updateDogParkReview(
    dogParkReview: DogParkReview,
    dogParkId: number,
    dogPark: DogPark
  ) {
    this.dogParkReviewService
      .update(dogParkReview, dogParkId, dogPark)
      .subscribe(
        (good) => {
          this.dogParkReviewService.index(this.dogParkId);
          if (this.selected != null) {
            this.selected = Object.assign({}, dogParkReview);
          }
        },
        (bad) => {
          console.error(bad);
        }
      );
    this.editDogParkReview = null;
  }

  setEditDogParkReview() {
    this.editDogParkReview = Object.assign({}, this.selected);
  }

  deleteDogParkReview(
    dogParkReview: DogParkReview
    // dogParkId: number,
    // dogPark: DogPark
  ) {
    this.dogParkReviewService.destroy(dogParkReview).subscribe(
      (good) => {
        this.reload();
      },
      (err) => console.error('Error deleting Review')
    );
  }
}
