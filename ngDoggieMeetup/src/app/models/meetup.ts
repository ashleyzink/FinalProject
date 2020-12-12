import { Dog } from './dog';
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
  dogs: Dog[];
  constructor(
    id?: number,
    title?: string,
    description?: string,
    meetupDate?: string,
    createDate?: string,
    user?: User,
    dogPark?: DogPark,
    dogs?: Dog[],
  ) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.meetupDate = meetupDate;
    this.createDate = createDate;
    this.user = user;
    this.dogPark = dogPark;
    this.dogs = dogs;
  }

}
