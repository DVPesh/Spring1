import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Page} from "../model/page";
import {Product} from "../model/product";

@Component({
  selector: 'app-product-service',
  templateUrl: './product-service.component.html',
  styleUrls: ['./product-service.component.scss']
})
export class ProductServiceComponent implements OnInit {

  constructor(private http: HttpClient) {
  }

  public findAll() {
    return this.http.get<Page>("api/v1/product")
  }

  public findById(id: number) {
    return this.http.get<Product>(`api/v1/product/${id}`)
  }

  public deleteById(id: number) {
    return this.http.delete<number>(`api/v1/product/${id}`)
  }

  public create(product: Product) {
    return this.http.post<Product>("api/v1/product", product)
  }

  public update(product: Product) {
    return this.http.put<Product>("api/v1/product", product)
  }

  ngOnInit(): void {
  }

}
