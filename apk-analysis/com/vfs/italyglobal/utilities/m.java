package com.vfs.italyglobal.utilities;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import android.util.Base64;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public abstract class m {
    public static String a(Context context) {
        PackageInfo packageInfo;
        try {
            if (Build.VERSION.SDK_INT >= 33) {
                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(134217728));
            } else {
                packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), c());
            }
            List b10 = b(packageInfo);
            if (!b10.isEmpty()) {
                return (String) b10.get(0);
            }
            return "";
        } catch (Exception e10) {
            e10.printStackTrace();
            return "";
        }
    }

    private static List b(PackageInfo packageInfo) {
        ArrayList arrayList = new ArrayList();
        int i10 = 0;
        if (Build.VERSION.SDK_INT >= 28) {
            SigningInfo a10 = packageInfo.signingInfo;
            if (a10 != null) {
                if (a10.hasMultipleSigners()) {
                    Signature[] apkContentsSigners = packageInfo.signingInfo.getApkContentsSigners();
                    int length = apkContentsSigners.length;
                    while (i10 < length) {
                        arrayList.add(d(apkContentsSigners[i10]));
                        i10++;
                    }
                } else {
                    Signature[] signingCertificateHistory = packageInfo.signingInfo.getSigningCertificateHistory();
                    int length2 = signingCertificateHistory.length;
                    while (i10 < length2) {
                        arrayList.add(d(signingCertificateHistory[i10]));
                        i10++;
                    }
                }
            }
        } else {
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr != null) {
                int length3 = signatureArr.length;
                while (i10 < length3) {
                    arrayList.add(d(signatureArr[i10]));
                    i10++;
                }
            }
        }
        return arrayList;
    }

    private static int c() {
        if (Build.VERSION.SDK_INT >= 28) {
            return 134217728;
        }
        return 64;
    }

    private static String d(Signature signature) {
        try {
            return Base64.encodeToString(MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256).digest(signature.toByteArray()), 2);
        } catch (Exception e10) {
            e10.printStackTrace();
            return "";
        }
    }
}
