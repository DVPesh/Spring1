import {Component, OnInit} from '@angular/core';
import {Product} from "../model/product";
import {ProductServiceComponent} from "../product-service/product-service.component";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {

  products: Product[] = [];

  constructor(private productService: ProductServiceComponent) {
  }

  ngOnInit()
    :
    void {
    this.productService.findAll().subscribe(response => {
      console.log(response.content);
      this.products = response.content;
    }, error => {
      console.log(error);
    })
  }

  delete(id: number | null) {
    if (id == null) return;
    this.productService.deleteById(id).subscribe(response => {
      console.log(response)
    }, error => {
      console.log(error)
    })
    this.ngOnInit();
  }
}
