import { Address } from './address';

export class DogPark {
  id: number;
  name: string;
  description: string;
  offLeash: boolean;
  imageUrl: string;
  address: Address;
  constructor(
    id?: number,
    name?: string,
    description?: string,
    offLeash?: boolean,
    imageUrl?: string,
    address?: Address,
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.offLeash = offLeash;
    this.imageUrl = imageUrl;
    this.address = address;
  }
}
