package e2;

import android.os.ParcelFileDescriptor;
import androidx.datastore.core.NativeSharedCounter;
import bc.h;
import java.io.IOException;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class f0 {

    /* renamed from: b  reason: collision with root package name */
    public static final a f10246b = new a((h) null);
    /* access modifiers changed from: private */

    /* renamed from: c  reason: collision with root package name */
    public static final NativeSharedCounter f10247c = new NativeSharedCounter();

    /* renamed from: a  reason: collision with root package name */
    private final long f10248a;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a {
        public /* synthetic */ a(h hVar) {
            this();
        }

        private final f0 b(ParcelFileDescriptor parcelFileDescriptor) {
            int fd2 = parcelFileDescriptor.getFd();
            if (c().nativeTruncateFile(fd2) == 0) {
                long nativeCreateSharedCounter = c().nativeCreateSharedCounter(fd2);
                if (nativeCreateSharedCounter >= 0) {
                    return new f0(nativeCreateSharedCounter, (h) null);
                }
                throw new IOException("Failed to mmap counter file");
            }
            throw new IOException("Failed to truncate counter file");
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0021  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final e2.f0 a(ac.a r2) {
            /*
                r1 = this;
                java.lang.String r0 = "produceFile"
                bc.n.e(r2, r0)
                java.lang.Object r2 = r2.c()
                java.io.File r2 = (java.io.File) r2
                r0 = 939524096(0x38000000, float:3.0517578E-5)
                android.os.ParcelFileDescriptor r2 = android.os.ParcelFileDescriptor.open(r2, r0)     // Catch:{ all -> 0x001d }
                e2.f0 r0 = r1.b(r2)     // Catch:{ all -> 0x001b }
                if (r2 == 0) goto L_0x001a
                r2.close()
            L_0x001a:
                return r0
            L_0x001b:
                r0 = move-exception
                goto L_0x001f
            L_0x001d:
                r0 = move-exception
                r2 = 0
            L_0x001f:
                if (r2 == 0) goto L_0x0024
                r2.close()
            L_0x0024:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: e2.f0.a.a(ac.a):e2.f0");
        }

        public final NativeSharedCounter c() {
            return f0.f10247c;
        }

        public final void d() {
            System.loadLibrary("datastore_shared_counter");
        }

        private a() {
        }
    }

    public /* synthetic */ f0(long j10, h hVar) {
        this(j10);
    }

    public final int b() {
        return f10247c.nativeGetCounterValue(this.f10248a);
    }

    public final int c() {
        return f10247c.nativeIncrementAndGetCounterValue(this.f10248a);
    }

    private f0(long j10) {
        this.f10248a = j10;
    }
}
