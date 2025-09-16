package com.github.vskrahul.spring_java17.facade.jvm;

import com.github.vskrahul.spring_java17.model.ThreadData;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.List;

@Service
public class ThreadUsageFacade {

    public ThreadData getThreadData() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();

        List<String> threads = Arrays.stream(threadIds)
                .mapToObj(v -> threadMXBean.getThreadInfo(v).getThreadName())
                .toList();

        return ThreadData.builder()
                .liveThreadCount(threadMXBean.getThreadCount())
                .currentThreadCpuTime(threadMXBean.getCurrentThreadCpuTime())
                .currentThreadUserTime(threadMXBean.getCurrentThreadUserTime())
                .daemonThreadCount(threadMXBean.getDaemonThreadCount())
                .peakThreadCount(threadMXBean.getPeakThreadCount())
                .startedThreadCount(threadMXBean.getTotalStartedThreadCount())
                .threads(threads)
                .build();
    }
}
