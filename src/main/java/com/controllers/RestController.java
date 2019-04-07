package com.controllers;


import com.models.RequestProduct;
import com.models.VendProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private VendingMachineController vendingMachineController;

    @Autowired
    public RestController(VendingMachineController vendingMachineController) {
        this.vendingMachineController = vendingMachineController;

    }

    @GetMapping("/healthcheck")
    public void healthCheck() {
    }

    @PostMapping("/purchaseProduct")
    public VendProduct purchaseProduct(@RequestBody RequestProduct requestProduct) {

        vendingMachineController.purchase(requestProduct.getProductLocation(), requestProduct.getInsertedCoins());

        return new VendProduct();
    }
}


