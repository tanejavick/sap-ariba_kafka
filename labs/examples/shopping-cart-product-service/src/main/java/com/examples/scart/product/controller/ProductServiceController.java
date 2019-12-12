package com.examples.scart.product.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.examples.scart.product.model.Product;

@RestController
public class ProductServiceController {
   private static Map<String, Product> productRepo = new HashMap<>();
   private static Logger log = LoggerFactory.getLogger(ProductServiceController.class);
   
   
   static {
      Product mobile = new Product();
      mobile.setId("1");
      mobile.setName("Samsung Galaxy Note10");      
      mobile.setCategory("Mobiles");
      mobile.setManufacturer("Samsung");
      productRepo.put(mobile.getId(), mobile);
      
      Product laptop = new Product();
      laptop.setId("2");
      laptop.setName("Lenovo Thinkpad E490");
      laptop.setCategory("Laptops");
      laptop.setManufacturer("Samsung");      
      productRepo.put(laptop.getId(), laptop);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
      productRepo.remove(id);
      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
      productRepo.remove(id);
      product.setId(id);
      productRepo.put(id, product);
      return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Product product)  {
	   
      productRepo.put(product.getId(), product);
      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
   }
   
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() throws InterruptedException {
	  log.info("Received request to list products...");
	  try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
}
