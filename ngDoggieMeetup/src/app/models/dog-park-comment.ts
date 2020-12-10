import { DogPark } from './dog-park';
import { User } from 'src/app/models/user';
export class DogParkComment {
  id: number;
  commentDate: string;
  commentText: string;
  title: string;
  user: User;
  replies: DogParkComment[];
  dogPark: DogPark;
  constructor(
    id?: number,
    commentDate?: string,
    commentText?: string,
    title?: string,
    user?: User,
    replies?: DogParkComment[],
    dogPark?: DogPark,
  ) {
    this.id = id;
    this.commentDate = commentDate;
    this.commentText = commentText;
    this.title = title;
    this.user = user;
    this.replies = replies;
    this.dogPark = dogPark;
  }
}
