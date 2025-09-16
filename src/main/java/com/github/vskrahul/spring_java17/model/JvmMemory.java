package com.github.vskrahul.spring_java17.model;

import lombok.Data;

import java.lang.management.MemoryUsage;
import java.util.List;

@Data
public class JvmMemory {
    private String name;
    private MemoryUsage memoryUsage;
    private List<JvmMemory> breakout;
}
