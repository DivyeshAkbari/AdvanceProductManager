package co.pragra.productmanager.newproductmanager.rest;

//import co.pragra.productmanager.newproductmanager.entity.Product;
//import co.pragra.productmanager.newproductmanager.repo.ProductRepo;
import co.pragra.productmanager.newproductmanager.entity.Product;
import co.pragra.productmanager.newproductmanager.repo.ProductRepo;
import co.pragra.productmanager.newproductmanager.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @PostMapping("/api/product")
    public Product createProduct(@RequestBody Product product) {
        System.out.println(product);
        return this.productService.creatProduct(product);
    }

    @GetMapping("/api/product")
    public List<Product> getAllProducts(@RequestHeader(value = "User-Agent") String userAgent,
                                        @RequestHeader(name="X_MARKET",defaultValue = "CANADA") String Market)
    {
        if(Market!=null && Market.equals("INDIA")){
            List<Product> all=this.productService.FindAll();
            return all.stream().map(product -> {
                product.setProductPrice(product.getProductPrice()*61);
                return product;}).
                    collect(Collectors.toUnmodifiableList());
        }
        return this.productService.FindAll();
    }
    @PutMapping("/api/product")
    public Product UpdateProduct(@RequestBody Product product)
    {
        return this.productService.updateProduct(product);
    }

    @GetMapping("/api/product/{id}")
    public Optional<Product> findByID(@PathVariable Long id)
    {
        return this.productService.findById(id);
    }

    @GetMapping("/api/product/FILTER")
    public List<Product> findCheaperProduct(@RequestParam(value = "cost", required = true) double cost)
    {
        return this.productService.findCheaperproduct(cost);
    }
}
