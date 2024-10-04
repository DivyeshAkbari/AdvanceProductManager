package co.pragra.productmanager.newproductmanager.services;

import co.pragra.productmanager.newproductmanager.entity.Product;
import co.pragra.productmanager.newproductmanager.exception.InvalidProductException;
import co.pragra.productmanager.newproductmanager.repo.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo)
    {
        this.productRepo = productRepo;
    }

    public Product creatProduct(Product product) {
        if(null==product)
        {
            throw new InvalidProductException("Product must not be null");
        }
        if(product.getProductName()==null || product.getProductName().length()<5)
        {
            throw new InvalidProductException("Product name Can not be null or less than 5 char");
        }
        product.setReviews(new ArrayList<>());
        return productRepo.save(product);
    }

    public List<Product> FindAll()
    {
        return productRepo.findAll();
    }
    public Product updateProduct(Product product) {

        if (null == product) {
            throw new InvalidProductException("Product must not be null");
        }

        Optional<Product> produtOptional = productRepo.findById(product.getId());
        produtOptional.orElseThrow(() -> new InvalidProductException("Product with id not found"));

        Product dbProduct=produtOptional.get();
        if(product.getProductName()!=null && product.getProductName().length()>5)
        {
           dbProduct.setProductName(product.getProductName());
        }
        if(product.getProductDescription()!=null)
        {
            dbProduct.setProductDescription(product.getProductDescription());
        }
        if(product.getProductPrice()!= dbProduct.getProductPrice() && product.getProductPrice()!=0.0)
        {
            dbProduct.setProductPrice(product.getProductPrice());
        }

        if(product.getReviews()!=null)
        {
            dbProduct.setReviews(product.getReviews());
        }
        dbProduct.setId(product.getId());
        return productRepo.save(dbProduct);
    }

    public Optional<Product> findById(Long id)
    {
        return productRepo.findById(id);
    }

    public List<Product> findCheaperproduct(double cost)
    {
        return productRepo.findCheaperProduct(cost);
    }
}
