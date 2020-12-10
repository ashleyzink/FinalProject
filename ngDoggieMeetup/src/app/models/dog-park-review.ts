import { DogPark } from './dog-park';
import { DogParkReviewId } from './dog-park-review-id';
import { User } from './user';
export class DogParkReview {
  id: DogParkReviewId;
  rating: number;
  review: string;
  imgUrl: string;
  user: User;
  reviewDate: string;
  dogPark: DogPark;
  constructor(
    id?: DogParkReviewId,
    rating?: number,
    review?: string,
    imageUrl?: string,
    user?: User,
    reviewDate?: string,
    dogPark?: DogPark
  ) {
    this.id = id;
    this.rating = rating;
    this.review = review;
    this.imgUrl = imageUrl;
    this.user = user;
    this.reviewDate = reviewDate;
    this.dogPark = dogPark;
  }
}
