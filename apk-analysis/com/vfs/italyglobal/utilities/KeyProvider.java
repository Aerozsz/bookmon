package com.vfs.italyglobal.utilities;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public abstract class KeyProvider {
    static {
        System.loadLibrary("native-lib");
    }

    public static native String getKeyService1();
}
