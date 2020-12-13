import { strict } from 'assert';
import { User } from './user';

export class Route {
  id: number;
  startTime: string;
  endTime: string;
  user: User;
  locations: Location[];
  constructor(
    id?: number,
    startTime?: string,
    endTime?: string,
    user?: User,
    locations?: Location[],
  ) {
    this.id = id;
    this.startTime = startTime;
    this.endTime = endTime;
    this.user = user;
    this.locations = locations;
  }
}
