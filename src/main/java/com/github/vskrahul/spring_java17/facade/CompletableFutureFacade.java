package com.github.vskrahul.spring_java17.facade;

import com.github.vskrahul.spring_java17.model.CompletableFutureRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompletableFutureFacade {

    Logger logger = LoggerFactory.getLogger(CompletableFutureFacade.class);

    public List<String> execute(CompletableFutureRequest request) {
        List<String> list = new ArrayList<>();
        Map<String, CompletableFuture<String>> completableFutureMap = new HashMap<>();
        for(int i = 0; i < request.getTask(); i++) {
            final String id = "completableFuture_ID_" + i;
            CompletableFuture<String> completableFuture = completeTaskInFuture(id, request.getDelay()).thenApply(v -> {
                list.add(v);
                return v;
            });
            completableFutureMap.put(id, completableFuture);
        }

        this.joinAll(completableFutureMap);

        return list;
    }

    CompletableFuture<String> completeTaskInFuture(String id, int delay) {
        return CompletableFuture.supplyAsync(() -> {
            logger.info(id);
            return id;
        }, CompletableFuture.delayedExecutor(delay, TimeUnit.SECONDS));
    }

    public Map<String, String> joinAll(Map<String, CompletableFuture<String>> completableFutureMap) {
        // Create a list of all futures
        CompletableFuture<?>[] futuresArray = completableFutureMap.values().toArray(new CompletableFuture[0]);

        // Wait for all futures to complete
        CompletableFuture<Void> allDone = CompletableFuture.allOf(futuresArray);

        // When all are done, collect results into a new map
        return allDone.thenApply(v ->
                completableFutureMap.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().join() // join each future
                        ))
        ).join(); // block until allDone is complete
    }
}
