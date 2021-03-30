package com.kodedu.linker;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.LibraryLookup;
import jdk.incubator.foreign.MemoryAddress;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static jdk.incubator.foreign.CLinker.C_LONG;
import static jdk.incubator.foreign.CLinker.C_POINTER;

public class StringLength {

    public static void main(String[] args) throws Throwable {
        // Utils: checkRestrictedAccess
        // -Dforeign.restricted=debug

        MethodHandle strlen = CLinker.getInstance().downcallHandle(
                LibraryLookup.ofDefault().lookup("strlen").get(),
                MethodType.methodType(long.class, MemoryAddress.class),
                FunctionDescriptor.of(C_LONG, C_POINTER)
        );

        MemoryAddress helloWorldAddress = CLinker.toCString("Hello World!").address();
        Object text = strlen.invoke(helloWorldAddress);
        System.out.println("Length: " + text);
    }
}
