package com.vfs.italyglobal.utilities;

import ac.a;
import android.app.AlertDialog;
import android.view.View;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final /* synthetic */ class b implements View.OnClickListener {

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ boolean f9505b;

    /* renamed from: n  reason: collision with root package name */
    public final /* synthetic */ AlertDialog f9506n;

    /* renamed from: o  reason: collision with root package name */
    public final /* synthetic */ a f9507o;

    public /* synthetic */ b(boolean z10, AlertDialog alertDialog, a aVar) {
        this.f9505b = z10;
        this.f9506n = alertDialog;
        this.f9507o = aVar;
    }

    public final void onClick(View view) {
        c.g(this.f9505b, this.f9506n, this.f9507o, view);
    }
}
