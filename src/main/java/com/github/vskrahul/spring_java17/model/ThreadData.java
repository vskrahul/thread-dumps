package com.github.vskrahul.spring_java17.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ThreadData {
    long liveThreadCount;
    long currentThreadCpuTime;
    long currentThreadUserTime;
    long daemonThreadCount;
    long peakThreadCount;
    long startedThreadCount;
    List<String> threads;
}
