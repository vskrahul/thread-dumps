package com.github.vskrahul.spring_java17.model;

import lombok.Data;

@Data
public class BxpMemory {
    JvmMemory heapMemory;
    JvmMemory nonHeapMemory;
}
