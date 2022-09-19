import {Component, OnInit} from '@angular/core';
import {Product} from "../model/product";
import {ProductServiceComponent} from "../product-service/product-service.component";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit {

  product = new Product(null, "", null, new Date(), "", 0);
  error = false;
  errorMessage = "";

  constructor(private productService: ProductServiceComponent,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe((param) => {
      let productId: number = param['id'];
      console.log(productId);
      this.productService.findById(productId).subscribe(response => {
        console.log(response)
        this.product = response;
        this.error = false;
        this.errorMessage = "";
      }, error => {
        console.log(error);
        this.error = true;
        this.errorMessage = "ошибка метода ngOnInit";
      })
    })
  }

  update() {
    this.productService.update(this.product).subscribe(res => {
      console.log(res);
      this.router.navigateByUrl('/product');
      this.error = false;
      this.errorMessage = "";
    }, err => {
      console.log(err);
      this.error = true;
      this.errorMessage = "ошибка метода update";
    })
  }
}
