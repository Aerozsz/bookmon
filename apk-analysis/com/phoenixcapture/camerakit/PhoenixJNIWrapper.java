package com.phoenixcapture.camerakit;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import java.util.ArrayList;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public class PhoenixJNIWrapper extends ContextWrapper implements AirsnapFace {
    static {
        System.loadLibrary("airsnap-face");
    }

    public PhoenixJNIWrapper(Context context) {
        super(context);
    }

    public native boolean deregisterDevice();

    public native ArrayList<FaceBox> detectFace(Bitmap bitmap);

    public native ArrayList<FaceBox> evalFace(Bitmap bitmap, boolean z10, boolean z11, float[] fArr, boolean z12, float[] fArr2, boolean z13, int[] iArr);

    public native String fetchLivenessLicense();

    public native int loadModel(String str, String str2, AssetManager assetManager, boolean z10, boolean z11, boolean z12);

    public native byte[] process(AssetManager assetManager);

    public native void setURL(String str);
}
