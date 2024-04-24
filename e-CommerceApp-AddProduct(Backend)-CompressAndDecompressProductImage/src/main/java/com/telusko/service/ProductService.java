package com.telusko.service;

import com.telusko.model.Product;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

    Product getProductById(Integer id);

    Product createProduct(Product product, MultipartFile imageFile) throws Exception;

}
