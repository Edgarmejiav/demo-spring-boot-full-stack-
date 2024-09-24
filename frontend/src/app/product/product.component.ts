import {Component, inject, OnInit} from '@angular/core';
import {Product} from "../../interface/product.model";
import {ProductsService} from "../products.service";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent implements OnInit {
  products: Product[] = [];
  productService = inject(ProductsService);

  constructor() {
  }

  ngOnInit() {
    this.loadProducts();

  }

  loadProducts() {
    this.productService.getProducts().subscribe({
      next: (products: Product[]) => {
        this.products = products;
      },
      error: (err) => {
        console.log(err);
      }
    });
  }


}
