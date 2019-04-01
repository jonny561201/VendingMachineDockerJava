package com.controllers;


import com.models.RequestProduct;
import com.models.VendProduct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @PostMapping("/purchaseProduct")
    public VendProduct purchaseProduct(@RequestBody RequestProduct requestProduct) {
        return new VendProduct();
    }

    @GetMapping("/test")
    public String getProduct() {
        return "Hello World";
    }
}


