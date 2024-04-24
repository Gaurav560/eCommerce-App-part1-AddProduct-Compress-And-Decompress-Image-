package com.telusko.controller;

import com.telusko.model.Product;
import com.telusko.service.ProductService;
import com.telusko.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestPart("product") Product product,
                                           @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            System.out.println(product.isProductAvailable());
            Product savedProduct = productService.createProduct(product, imageFile);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable Integer productId) {
        Product product = productService.getProductById(productId);
        if (product != null && product.getImageData() != null) {
            byte[] imageData = ImageUtils.decompressImage(product.getImageData());
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf(product.getImageType()))
                    .body(imageData);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


//    {
//        "name": "Example Product",
//            "description": "This is a detailed description of the example product.",
//            "brand": "ExampleBrand",
//            "price": 19.99,
//            "category": "Electronics",
//            "stockQuantity": 100,
//            "releaseDate": "2024-04-17",
//            "productAvailable": true
//    }

}
