package androidx.camera.core.impl.utils;

import android.view.Surface;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public abstract class SurfaceUtil {

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public int f1664a = 0;

        /* renamed from: b  reason: collision with root package name */
        public int f1665b = 0;

        /* renamed from: c  reason: collision with root package name */
        public int f1666c = 0;
    }

    static {
        System.loadLibrary("surface_util_jni");
    }

    public static a a(Surface surface) {
        int[] nativeGetSurfaceInfo = nativeGetSurfaceInfo(surface);
        a aVar = new a();
        aVar.f1664a = nativeGetSurfaceInfo[0];
        aVar.f1665b = nativeGetSurfaceInfo[1];
        aVar.f1666c = nativeGetSurfaceInfo[2];
        return aVar;
    }

    private static native int[] nativeGetSurfaceInfo(Surface surface);
}
