import { DogPark } from './dog-park';
import { User } from './user';
export class DogParkReview {
  rating: number;
  review: string;
  imgUrl: string;
  user: User;
  reviewDate: string;
  dogPark: DogPark;
  constructor(
    rating?: number,
    review?: string,
    imageUrl?: string,
    user?: User,
    reviewDate?: string,
    dogPark?: DogPark
  ) {
    this.rating = rating;
    this.review = review;
    this.imgUrl = imageUrl;
    this.user = user;
    this.reviewDate = reviewDate;
    this.dogPark = dogPark;
  }
}
