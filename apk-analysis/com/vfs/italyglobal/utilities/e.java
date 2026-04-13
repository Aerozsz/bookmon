package com.vfs.italyglobal.utilities;

import android.text.InputFilter;
import android.text.Spanned;
import bc.n;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class e implements InputFilter {

    /* renamed from: a  reason: collision with root package name */
    private final int f9509a;

    /* renamed from: b  reason: collision with root package name */
    private final int f9510b;

    public e(int i10, int i11) {
        this.f9509a = i10;
        this.f9510b = i11;
    }

    private final boolean a(int i10, int i11, int i12) {
        if (i11 > i10) {
            if (i10 > i12 || i12 > i11) {
                return false;
            }
            return true;
        } else if (i11 > i12 || i12 > i10) {
            return false;
        } else {
            return true;
        }
    }

    public CharSequence filter(CharSequence charSequence, int i10, int i11, Spanned spanned, int i12, int i13) {
        try {
            StringBuilder sb2 = new StringBuilder();
            n.b(spanned);
            sb2.append(spanned.subSequence(0, i12));
            n.b(charSequence);
            sb2.append(charSequence.subSequence(i10, i11));
            sb2.append(spanned.subSequence(i13, spanned.length()));
            String sb3 = sb2.toString();
            String.valueOf(spanned);
            if (sb3.length() == 0) {
                return null;
            }
            if (a(this.f9509a, this.f9510b, Integer.parseInt(sb3))) {
                return charSequence;
            }
            return "";
        } catch (NumberFormatException e10) {
            e10.toString();
            return "";
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public e(String str, String str2) {
        this(Integer.parseInt(str), Integer.parseInt(str2));
        n.e(str, "min");
        n.e(str2, "max");
    }
}
