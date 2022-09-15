export class Product {

  constructor(public id: number | null,
              public title: string,
              public cost: any,
              public expirationDate: Date,
              public supplierEmail: string,
              public quantity: number) {
  }

}
