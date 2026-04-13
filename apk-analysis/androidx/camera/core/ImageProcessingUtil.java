package androidx.camera.core;

import android.graphics.Bitmap;
import android.media.Image;
import android.media.ImageWriter;
import android.util.Log;
import android.view.Surface;
import c0.n0;
import c0.o0;
import c0.v0;
import f0.j1;
import java.nio.ByteBuffer;
import java.util.Locale;
import v1.h;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public abstract class ImageProcessingUtil {

    /* renamed from: a  reason: collision with root package name */
    private static int f1598a;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    enum a {
        UNKNOWN,
        SUCCESS,
        ERROR_CONVERSION
    }

    static {
        System.loadLibrary("image_processing_util_jni");
    }

    public static /* synthetic */ void a(n nVar, n nVar2, n nVar3) {
        if (nVar != null && nVar2 != null) {
            nVar2.close();
        }
    }

    public static /* synthetic */ void b(n nVar, n nVar2, n nVar3) {
        if (nVar != null && nVar2 != null) {
            nVar2.close();
        }
    }

    public static boolean c(n nVar) {
        if (!l(nVar)) {
            v0.c("ImageProcessingUtil", "Unsupported format for YUV to RGB");
            return false;
        } else if (d(nVar) != a.ERROR_CONVERSION) {
            return true;
        } else {
            v0.c("ImageProcessingUtil", "One pixel shift for YUV failure");
            return false;
        }
    }

    private static a d(n nVar) {
        int e10 = nVar.e();
        int a10 = nVar.a();
        int a11 = nVar.k()[0].a();
        int a12 = nVar.k()[1].a();
        int a13 = nVar.k()[2].a();
        int b10 = nVar.k()[0].b();
        int b11 = nVar.k()[1].b();
        ByteBuffer buffer = nVar.k()[0].getBuffer();
        ByteBuffer byteBuffer = buffer;
        int i10 = a11;
        ByteBuffer byteBuffer2 = byteBuffer;
        ByteBuffer buffer2 = nVar.k()[1].getBuffer();
        int i11 = a12;
        ByteBuffer byteBuffer3 = buffer2;
        int i12 = a13;
        if (nativeShiftPixel(byteBuffer2, i10, byteBuffer3, i11, nVar.k()[2].getBuffer(), i12, b10, b11, e10, a10, b10, b11, b11) != 0) {
            return a.ERROR_CONVERSION;
        }
        return a.SUCCESS;
    }

    public static n e(j1 j1Var, byte[] bArr) {
        boolean z10;
        if (j1Var.d() == 256) {
            z10 = true;
        } else {
            z10 = false;
        }
        h.a(z10);
        h.g(bArr);
        Surface surface = j1Var.getSurface();
        h.g(surface);
        if (nativeWriteJpegToSurface(bArr, surface) != 0) {
            v0.c("ImageProcessingUtil", "Failed to enqueue JPEG image.");
            return null;
        }
        n c10 = j1Var.c();
        if (c10 == null) {
            v0.c("ImageProcessingUtil", "Failed to get acquire JPEG image.");
        }
        return c10;
    }

    public static Bitmap f(n nVar) {
        if (nVar.getFormat() == 35) {
            int e10 = nVar.e();
            int a10 = nVar.a();
            int a11 = nVar.k()[0].a();
            int a12 = nVar.k()[1].a();
            int a13 = nVar.k()[2].a();
            int b10 = nVar.k()[0].b();
            int b11 = nVar.k()[1].b();
            Bitmap createBitmap = Bitmap.createBitmap(nVar.e(), nVar.a(), Bitmap.Config.ARGB_8888);
            int rowBytes = createBitmap.getRowBytes();
            ByteBuffer buffer = nVar.k()[0].getBuffer();
            if (nativeConvertAndroid420ToBitmap(buffer, a11, nVar.k()[1].getBuffer(), a12, nVar.k()[2].getBuffer(), a13, b10, b11, createBitmap, rowBytes, e10, a10) == 0) {
                return createBitmap;
            }
            throw new UnsupportedOperationException("YUV to RGB conversion failed");
        }
        throw new IllegalArgumentException("Input image format must be YUV_420_888");
    }

    public static n g(n nVar, j1 j1Var, ByteBuffer byteBuffer, int i10, boolean z10) {
        if (!l(nVar)) {
            v0.c("ImageProcessingUtil", "Unsupported format for YUV to RGB");
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (!k(i10)) {
            v0.c("ImageProcessingUtil", "Unsupported rotation degrees for rotate RGB");
            return null;
        } else if (h(nVar, j1Var.getSurface(), byteBuffer, i10, z10) == a.ERROR_CONVERSION) {
            v0.c("ImageProcessingUtil", "YUV to RGB conversion failure");
            return null;
        } else {
            if (Log.isLoggable("MH", 3)) {
                v0.a("ImageProcessingUtil", String.format(Locale.US, "Image processing performance profiling, duration: [%d], image count: %d", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis), Integer.valueOf(f1598a)}));
                f1598a++;
            }
            n c10 = j1Var.c();
            if (c10 == null) {
                v0.c("ImageProcessingUtil", "YUV to RGB acquireLatestImage failure");
                return null;
            }
            s sVar = new s(c10);
            sVar.c(new n0(c10, nVar));
            return sVar;
        }
    }

    private static a h(n nVar, Surface surface, ByteBuffer byteBuffer, int i10, boolean z10) {
        int i11;
        int i12;
        int i13;
        int e10 = nVar.e();
        int a10 = nVar.a();
        int a11 = nVar.k()[0].a();
        int a12 = nVar.k()[1].a();
        int a13 = nVar.k()[2].a();
        int b10 = nVar.k()[0].b();
        int b11 = nVar.k()[1].b();
        if (z10) {
            i11 = b10;
        } else {
            i11 = 0;
        }
        if (z10) {
            i12 = b11;
        } else {
            i12 = 0;
        }
        if (z10) {
            i13 = b11;
        } else {
            i13 = 0;
        }
        if (nativeConvertAndroid420ToABGR(nVar.k()[0].getBuffer(), a11, nVar.k()[1].getBuffer(), a12, nVar.k()[2].getBuffer(), a13, b10, b11, surface, byteBuffer, e10, a10, i11, i12, i13, i10) != 0) {
            return a.ERROR_CONVERSION;
        }
        return a.SUCCESS;
    }

    public static void i(Bitmap bitmap, ByteBuffer byteBuffer, int i10) {
        nativeCopyBetweenByteBufferAndBitmap(bitmap, byteBuffer, bitmap.getRowBytes(), i10, bitmap.getWidth(), bitmap.getHeight(), false);
    }

    public static void j(Bitmap bitmap, ByteBuffer byteBuffer, int i10) {
        nativeCopyBetweenByteBufferAndBitmap(bitmap, byteBuffer, i10, bitmap.getRowBytes(), bitmap.getWidth(), bitmap.getHeight(), true);
    }

    private static boolean k(int i10) {
        if (i10 == 0 || i10 == 90 || i10 == 180 || i10 == 270) {
            return true;
        }
        return false;
    }

    private static boolean l(n nVar) {
        if (nVar.getFormat() == 35 && nVar.k().length == 3) {
            return true;
        }
        return false;
    }

    public static n m(n nVar, j1 j1Var, ImageWriter imageWriter, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i10) {
        a aVar;
        n nVar2;
        if (!l(nVar)) {
            v0.c("ImageProcessingUtil", "Unsupported format for rotate YUV");
            return null;
        } else if (!k(i10)) {
            v0.c("ImageProcessingUtil", "Unsupported rotation degrees for rotate YUV");
            return null;
        } else {
            a aVar2 = a.ERROR_CONVERSION;
            if (i10 > 0) {
                nVar2 = nVar;
                aVar = n(nVar2, imageWriter, byteBuffer, byteBuffer2, byteBuffer3, i10);
            } else {
                nVar2 = nVar;
                aVar = aVar2;
            }
            if (aVar == aVar2) {
                v0.c("ImageProcessingUtil", "rotate YUV failure");
                return null;
            }
            n c10 = j1Var.c();
            if (c10 == null) {
                v0.c("ImageProcessingUtil", "YUV rotation acquireLatestImage failure");
                return null;
            }
            s sVar = new s(c10);
            sVar.c(new o0(c10, nVar2));
            return sVar;
        }
    }

    private static a n(n nVar, ImageWriter imageWriter, ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i10) {
        int e10 = nVar.e();
        int a10 = nVar.a();
        int a11 = nVar.k()[0].a();
        int a12 = nVar.k()[1].a();
        int a13 = nVar.k()[2].a();
        int b10 = nVar.k()[1].b();
        Image b11 = k0.a.b(imageWriter);
        if (b11 == null) {
            return a.ERROR_CONVERSION;
        }
        ByteBuffer buffer = nVar.k()[0].getBuffer();
        ByteBuffer buffer2 = nVar.k()[1].getBuffer();
        ByteBuffer buffer3 = nVar.k()[2].getBuffer();
        ByteBuffer buffer4 = b11.getPlanes()[0].getBuffer();
        int rowStride = b11.getPlanes()[0].getRowStride();
        int pixelStride = b11.getPlanes()[0].getPixelStride();
        ByteBuffer buffer5 = b11.getPlanes()[1].getBuffer();
        int rowStride2 = b11.getPlanes()[1].getRowStride();
        int pixelStride2 = b11.getPlanes()[1].getPixelStride();
        ByteBuffer buffer6 = b11.getPlanes()[2].getBuffer();
        ByteBuffer byteBuffer4 = buffer3;
        int i11 = pixelStride;
        ByteBuffer byteBuffer5 = buffer;
        ByteBuffer byteBuffer6 = buffer4;
        ByteBuffer byteBuffer7 = buffer5;
        int i12 = pixelStride2;
        if (nativeRotateYUV(byteBuffer5, a11, buffer2, a12, byteBuffer4, a13, b10, byteBuffer6, rowStride, i11, byteBuffer7, rowStride2, i12, buffer6, b11.getPlanes()[2].getRowStride(), b11.getPlanes()[2].getPixelStride(), byteBuffer, byteBuffer2, byteBuffer3, e10, a10, i10) != 0) {
            return a.ERROR_CONVERSION;
        }
        k0.a.d(imageWriter, b11);
        return a.SUCCESS;
    }

    private static native int nativeConvertAndroid420ToABGR(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, int i13, int i14, Surface surface, ByteBuffer byteBuffer4, int i15, int i16, int i17, int i18, int i19, int i20);

    private static native int nativeConvertAndroid420ToBitmap(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, int i13, int i14, Bitmap bitmap, int i15, int i16, int i17);

    private static native int nativeCopyBetweenByteBufferAndBitmap(Bitmap bitmap, ByteBuffer byteBuffer, int i10, int i11, int i12, int i13, boolean z10);

    private static native int nativeRotateYUV(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, int i13, ByteBuffer byteBuffer4, int i14, int i15, ByteBuffer byteBuffer5, int i16, int i17, ByteBuffer byteBuffer6, int i18, int i19, ByteBuffer byteBuffer7, ByteBuffer byteBuffer8, ByteBuffer byteBuffer9, int i20, int i21, int i22);

    private static native int nativeShiftPixel(ByteBuffer byteBuffer, int i10, ByteBuffer byteBuffer2, int i11, ByteBuffer byteBuffer3, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19);

    private static native int nativeWriteJpegToSurface(byte[] bArr, Surface surface);

    public static boolean o(Surface surface, byte[] bArr) {
        h.g(bArr);
        h.g(surface);
        if (nativeWriteJpegToSurface(bArr, surface) == 0) {
            return true;
        }
        v0.c("ImageProcessingUtil", "Failed to enqueue JPEG image.");
        return false;
    }
}
