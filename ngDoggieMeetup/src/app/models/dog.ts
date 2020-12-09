import { User } from './user';

export class Dog {
  id: number;
  name: string;
  breed: string;
  temperament: string;
  profilePicUrl: string;
  activityLevel: string;
  size: string;
  bio: string;
  birthday: string;
  rainbowBridge: string;
  createDate: string;
  gender: string;
  user: User;

  constructor(
    id?: number,
    name?: string,
    breed?: string,
    temperament?: string,
    profilePicUrl?: string,
    activityLevel?: string,
    size?: string,
    bio?: string,
    birthday?: string,
    rainbowBridge?: string,
    createDate?: string,
    gender?: string,
    user?: User
  ) {
    this.id = id;
    this.name = name;
    this.breed = breed;
    this.temperament = temperament;
    this.profilePicUrl = profilePicUrl;
    this.activityLevel = activityLevel;
    this.size = size;
    this.bio = bio;
    this.birthday = birthday;
    this.rainbowBridge = rainbowBridge;
    this.createDate = createDate;
    this.gender = gender;
    this.user = user;

  }


}
