package com.example.demo.controllers;
import com.example.demo.domain.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

@Controller
public class BuyProductController {
    @Autowired
    private ProductRepository productServiceRepository;

    @GetMapping("/purchaseScreen")
    public String buyProduct(@RequestParam("productID") long pID) {
        Optional<Product> chosenProduct = productServiceRepository.findById(pID);
        if (chosenProduct.isPresent()) {
            Product thing = chosenProduct.get();

            if (thing.getInv() > 0){
                thing.setInv(thing.getInv() - 1);
                productServiceRepository.save(thing);
                return "/confirmationsuccessbuy";
            }else{
                return "/confirmationfailurebuy";
            }
        }else{
            return "/confirmationfailurebuy";
        }
        }
}


