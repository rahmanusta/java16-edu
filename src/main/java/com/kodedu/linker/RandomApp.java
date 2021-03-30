package com.kodedu.linker;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.LibraryLookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static jdk.incubator.foreign.CLinker.C_INT;

public class RandomApp {

    public static void main(String[] args) throws Throwable {

        MethodHandle random = CLinker.getInstance()
                .downcallHandle(
                        LibraryLookup.ofDefault().lookup("rand").get(),
                        MethodType.methodType(int.class),
                        FunctionDescriptor.of(C_INT)
                );

        System.out.println("Random number: " + random.invoke());
    }
}
