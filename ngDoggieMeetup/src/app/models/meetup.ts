import { DogPark } from './dog-park';
import { User } from './user';

export class Meetup {
  id: number;
  title: string;
  description: string;
  meetupDate: string;
  createDate: string;
  user: User;
  dogPark: DogPark;
  constructor(
    id?: number,
    title?: string,
    description?: string,
    meetupDate?: string,
    createDate?: string,
    user?: User,
    dogPark?: DogPark,
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.meetupDate = meetupDate;
    this.createDate = createDate;
    this.user = user;
    this.dogPark = dogPark;
  }

}
