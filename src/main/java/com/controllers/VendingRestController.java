package com.controllers;


import com.models.RequestProduct;
import com.models.VendProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendingRestController {

    private VendingMachineController vendingMachineController;

    @Autowired
    public VendingRestController(VendingMachineController vendingMachineController) {
        this.vendingMachineController = vendingMachineController;
    }

    @GetMapping("/healthcheck")
    public void healthCheck() {
    }

    @PostMapping("/purchaseProduct")
    public VendProduct purchaseProduct(@RequestBody RequestProduct requestProduct) {
        return vendingMachineController.purchase(requestProduct.getProductLocation(), requestProduct.getInsertedCoins());
    }
}


