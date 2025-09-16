package com.github.vskrahul.spring_java17.facade.jvm;

import com.github.vskrahul.spring_java17.model.BxpMemory;
import com.github.vskrahul.spring_java17.model.JvmMemory;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryType;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoryUsageFacade {

    public BxpMemory getBxpMemoryUsage() {
        BxpMemory bxpMemory = new BxpMemory();

        bxpMemory.setHeapMemory(this.heapMemory());
        bxpMemory.setNonHeapMemory(this.nonHeapMemory());

        return bxpMemory;
    }

    private JvmMemory heapMemory() {
        JvmMemory heapMemory = new JvmMemory();
        heapMemory.setName(MemoryType.HEAP.name());
        heapMemory.setMemoryUsage(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage());
        heapMemory.setBreakout(this.breakoutOfMemoryUsage(MemoryType.HEAP));
        return heapMemory;
    }

    private JvmMemory nonHeapMemory() {
        JvmMemory nonHeapMemory = new JvmMemory();
        nonHeapMemory.setName(MemoryType.NON_HEAP.name());
        nonHeapMemory.setMemoryUsage(ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage());
        nonHeapMemory.setBreakout(this.breakoutOfMemoryUsage(MemoryType.NON_HEAP));
        return nonHeapMemory;
    }

    private List<JvmMemory> breakoutOfMemoryUsage(MemoryType memoryType) {
        List<MemoryPoolMXBean> memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
        return memoryPoolMXBeans.stream()
                .filter(v -> v.getType() == memoryType)
                .map(v -> {
                    JvmMemory jvmMemory = new JvmMemory();
                    jvmMemory.setMemoryUsage(v.getUsage());
                    jvmMemory.setName(v.getName());
                    return jvmMemory;
                }).collect(Collectors.toList());
    }
}
