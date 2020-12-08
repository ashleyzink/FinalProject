export class Address {
  id: number;
  street: string;
  city: string;
  stateAbbrv: string;
  zipcode: string;
  constructor(
    id?: number,
    street?: string,
    city?: string,
    stateAbbrv?: string,
    zipcode?: string,
  ) {
    this.id = id;
    this.street = street;
    this.city = city;
    this.stateAbbrv = stateAbbrv;
    this.zipcode = zipcode;
  }
}
