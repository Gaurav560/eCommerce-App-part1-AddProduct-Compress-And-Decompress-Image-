package com.telusko.service;

import com.telusko.model.Product;
import com.telusko.repo.ProductRepo;
import com.telusko.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;


    @Override
    public Product getProductById(Integer id) {
        return productRepo.findById(id).orElse(null);
    }


    @Override
    public Product createProduct(Product product, MultipartFile imageFile) throws Exception {
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImageName(imageFile.getOriginalFilename());
            product.setImageType(imageFile.getContentType());
            product.setImageData(ImageUtils.compressImage(imageFile.getBytes()));
        }
        return productRepo.save(product);
    }


}
