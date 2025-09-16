package com.github.vskrahul.spring_java17.controller.memory;

import com.github.vskrahul.spring_java17.facade.jvm.MemoryUsageFacade;
import com.github.vskrahul.spring_java17.facade.jvm.ThreadUsageFacade;
import com.github.vskrahul.spring_java17.model.BxpMemory;
import com.github.vskrahul.spring_java17.model.ThreadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1")
@RequestMapping("/v1")
public class SystemController {

    private final MemoryUsageFacade memoryUsageFacade;
    private final ThreadUsageFacade threadUsageFacade;

    @Autowired
    public SystemController(MemoryUsageFacade memoryUsageFacade,
                            ThreadUsageFacade threadUsageFacade) {
        this.memoryUsageFacade = memoryUsageFacade;
        this.threadUsageFacade = threadUsageFacade;
    }

    @GetMapping("/memory-usage")
    public BxpMemory getMemoryUsage() {
        return this.memoryUsageFacade.getBxpMemoryUsage();
    }

    @GetMapping("/thread-usage")
    public ThreadData getThreadUsage() {
        return this.threadUsageFacade.getThreadData();
    }
}
