package com.vfs.italyglobal.utilities;

import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;
import bc.h;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class n {

    /* renamed from: e  reason: collision with root package name */
    public static final a f9525e = new a((h) null);

    /* renamed from: a  reason: collision with root package name */
    private final TextView f9526a;

    /* renamed from: b  reason: collision with root package name */
    private final String f9527b;

    /* renamed from: c  reason: collision with root package name */
    private final SpannableString f9528c;

    /* renamed from: d  reason: collision with root package name */
    private int f9529d;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a {
        public /* synthetic */ a(h hVar) {
            this();
        }

        public final n a(TextView textView, String str) {
            bc.n.e(textView, "textView");
            bc.n.e(str, "content");
            return new n(textView, str, (h) null);
        }

        private a() {
        }
    }

    public /* synthetic */ n(TextView textView, String str, h hVar) {
        this(textView, str);
    }

    public final void a() {
        this.f9526a.setText(this.f9528c);
    }

    public final n b(ClickableSpan clickableSpan, String... strArr) {
        bc.n.e(clickableSpan, "clickableSpan");
        bc.n.e(strArr, "texts");
        for (String str : strArr) {
            if (kc.n.Q(this.f9527b, str, false, 2, (Object) null)) {
                int c02 = kc.n.c0(this.f9527b, str, 0, false, 6, (Object) null);
                this.f9528c.setSpan(clickableSpan, c02, str.length() + c02, this.f9529d);
            }
        }
        this.f9526a.setMovementMethod(LinkMovementMethod.getInstance());
        return this;
    }

    public final n c(int i10, String... strArr) {
        bc.n.e(strArr, "texts");
        for (String str : strArr) {
            if (kc.n.Q(this.f9527b, str, false, 2, (Object) null)) {
                int c02 = kc.n.c0(this.f9527b, str, 0, false, 6, (Object) null);
                this.f9528c.setSpan(new ForegroundColorSpan(k1.a.c(this.f9526a.getContext(), i10)), c02, str.length() + c02, this.f9529d);
            }
        }
        return this;
    }

    public final n d(String... strArr) {
        bc.n.e(strArr, "texts");
        for (String str : strArr) {
            if (kc.n.Q(this.f9527b, str, false, 2, (Object) null)) {
                int c02 = kc.n.c0(this.f9527b, str, 0, false, 6, (Object) null);
                this.f9528c.setSpan(new UnderlineSpan(), c02, str.length() + c02, this.f9529d);
            }
        }
        return this;
    }

    private n(TextView textView, String str) {
        this.f9526a = textView;
        this.f9527b = str;
        this.f9528c = new SpannableString(str);
        this.f9529d = 33;
    }
}
