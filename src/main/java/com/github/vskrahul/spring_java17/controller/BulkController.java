package com.github.vskrahul.spring_java17.controller;

import com.github.vskrahul.spring_java17.facade.CompletableFutureFacade;
import com.github.vskrahul.spring_java17.model.CompletableFutureRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BulkController {

    @Autowired
    CompletableFutureFacade facade;


    @PutMapping("/execute")
    public List<String> execute(@RequestBody CompletableFutureRequest request) {
        return this.facade.execute(request);
    }
}
