package com.mig.stock.controller;

import com.mig.stock.dto.StockRepository;
import com.mig.stock.model.Stock;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
@Tag(name = "Stock Controller", description = "Stock Controller")
public class StockController {

    @Autowired
    StockRepository stockRepository;

    @GetMapping
    @Tag(name = "findAll",description = "Return all stocks")
    public ResponseEntity<List<Stock>> findAll() {
        return ResponseEntity.ok(stockRepository.findAll());
    }


    @GetMapping("/byProduct")
    @Tag(name = "Get Stock By ProductId")
    ResponseEntity<Stock> getStockByProductId(@RequestParam  Long productId){
        if(productId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Stock> s= stockRepository.findByProductId(productId);
        if(s.isPresent()){
            return ResponseEntity.ok(s.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Tag(name = "Add Quantity",description = "add quantity to stock")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock){
        if(stock == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Stock> old = stockRepository.findByProductId(stock.getProductId());
        if(old.isPresent()){
            Stock s = old.get();
            s.setQuantity(s.getQuantity()+stock.getQuantity());
            stockRepository.save(s);
            return ResponseEntity.ok(s);
        }
        else {
            stockRepository.save(stock);
            return ResponseEntity.ok(stock);
        }

    }
}
