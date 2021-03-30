package com.kodedu.linker;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.LibraryLookup;
import jdk.incubator.foreign.MemoryAddress;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.nio.file.Path;
import java.nio.file.Paths;

import static jdk.incubator.foreign.CLinker.C_INT;
import static jdk.incubator.foreign.CLinker.C_POINTER;

public class HelloApp {

    public static void main(String[] args) throws Throwable {

        Path path = Paths.get("/Users/usta/projects/java16-edu/nativecode/hello");
        LibraryLookup helloLibrary = LibraryLookup.ofPath(path);

        MethodHandle hello = CLinker.getInstance()
                .downcallHandle(
                        helloLibrary.lookup("print").get(),
                        MethodType.methodType(int.class, MemoryAddress.class),
                        FunctionDescriptor.of(C_INT, C_POINTER)
                );

        final MemoryAddress address = CLinker.toCString("Istanbul JUG").address();
        hello.invoke(address);
    }
}
