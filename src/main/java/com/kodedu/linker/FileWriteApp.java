package com.kodedu.linker;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.LibraryLookup;
import jdk.incubator.foreign.MemoryAddress;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static jdk.incubator.foreign.CLinker.*;

public class FileWriteApp {

    public static void main(String[] args) throws Throwable {

        MethodHandle fopen = CLinker.getInstance()
                .downcallHandle(
                        LibraryLookup.ofDefault().lookup("fopen").get(),
                        MethodType.methodType(MemoryAddress.class, MemoryAddress.class, MemoryAddress.class),
                        FunctionDescriptor.of(C_POINTER, C_POINTER, C_POINTER)
                );

        MemoryAddress file = CLinker.toCString("file2.txt").address();
        MemoryAddress access = CLinker.toCString("w").address();
        MemoryAddress openedFile = (MemoryAddress) fopen.invoke(file, access);

        MethodHandle fwrite = CLinker.getInstance()
                .downcallHandle(
                        LibraryLookup.ofDefault().lookup("fwrite").get(),
                        MethodType.methodType(long.class, MemoryAddress.class, int.class, int.class, MemoryAddress.class),
                        FunctionDescriptor.of(C_LONG, C_POINTER, C_INT, C_INT, C_POINTER)
                );

        String helloWorld = "Hello World";
        Long result = (Long) fwrite.invoke(CLinker.toCString(helloWorld).address(), 1, helloWorld.length(), openedFile);

        System.out.println(result);
    }
}
