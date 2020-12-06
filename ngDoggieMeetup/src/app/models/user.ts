export class User {
  id: number;
  username: string;
  password: string;
  email: string;
  enabled: boolean;
  role: string;
  firstName: string;
  lastName: string;
  dateOption: boolean;
  profilePhotoUrl: string;
  bio: string;
  // TODO address, createDate, profilePrivate, locationPrivate
  // TODO relationships?

  constructor(
    id?: number,
    username?: string,
    password?: string,
    email?: string,
    enabled?: boolean,
    role?: string,
    firstName?: string,
    lastName?: string,
    dateOption?: boolean,
    profilePhotoUrl?: string,
    bio?: string,
  ) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.enabled = enabled;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOption = dateOption;
    this.profilePhotoUrl = profilePhotoUrl;
    this.bio = bio;
  }
}
