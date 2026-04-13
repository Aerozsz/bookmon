package com.vfs.italyglobal.activities;

import ac.l;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SpinnerAdapter;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import bc.a0;
import bc.n;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.CenterCodeResponseData;
import com.vfs.italyglobal.pojo.CheckIsSlotAvailableResModel;
import com.vfs.italyglobal.pojo.ErrorMessage;
import com.vfs.italyglobal.pojo.ErrorResponseModel;
import com.vfs.italyglobal.pojo.PaymentMode;
import com.vfs.italyglobal.pojo.VasCartDetails;
import com.vfs.italyglobal.pojo.VisaCategoryDataResponse;
import com.vfs.italyglobal.pojo.VisaSubCategoryResponseData;
import com.vfs.italyglobal.pojo.request.CheckIsSlotAvailableRequestParams;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.g;
import fd.i0;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import ob.p;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import ua.k0;
import ua.l0;
import ua.m0;
import ua.n0;
import ua.o0;
import ua.p0;
import ua.q0;
import ua.r0;
import ua.s0;
import ua.t0;
import ua.u0;
import ua.v0;
import va.x;
import xa.n1;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class AppointmentGetEarliestDates extends x {

    /* renamed from: x0  reason: collision with root package name */
    public static final a f9302x0 = new a((bc.h) null);
    /* access modifiers changed from: private */

    /* renamed from: y0  reason: collision with root package name */
    public static VasCartDetails f9303y0 = new VasCartDetails();

    /* renamed from: f0  reason: collision with root package name */
    private final nb.h f9304f0 = nb.i.a(new k0(this));

    /* renamed from: g0  reason: collision with root package name */
    public String f9305g0;

    /* renamed from: h0  reason: collision with root package name */
    public String f9306h0;

    /* renamed from: i0  reason: collision with root package name */
    public String f9307i0;

    /* renamed from: j0  reason: collision with root package name */
    public String f9308j0;

    /* renamed from: k0  reason: collision with root package name */
    private int f9309k0;

    /* renamed from: l0  reason: collision with root package name */
    private ArrayList f9310l0;

    /* renamed from: m0  reason: collision with root package name */
    private ArrayList f9311m0;

    /* renamed from: n0  reason: collision with root package name */
    private ArrayList f9312n0;

    /* renamed from: o0  reason: collision with root package name */
    private ArrayList f9313o0;

    /* renamed from: p0  reason: collision with root package name */
    private ArrayList f9314p0;

    /* renamed from: q0  reason: collision with root package name */
    private ArrayList f9315q0;

    /* renamed from: r0  reason: collision with root package name */
    private ArrayList f9316r0;

    /* renamed from: s0  reason: collision with root package name */
    private ArrayList f9317s0;

    /* renamed from: t0  reason: collision with root package name */
    private VisaSubCategoryResponseData f9318t0;

    /* renamed from: u0  reason: collision with root package name */
    private Calendar f9319u0;

    /* renamed from: v0  reason: collision with root package name */
    private Bundle f9320v0;

    /* renamed from: w0  reason: collision with root package name */
    public List f9321w0;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a {
        public /* synthetic */ a(bc.h hVar) {
            this();
        }

        public final VasCartDetails a() {
            return AppointmentGetEarliestDates.f9303y0;
        }

        private a() {
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static class b extends ArrayAdapter {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public b(Context context, int i10, List list) {
            super(context, i10, list);
            n.e(context, "context");
            n.b(list);
        }

        public View getView(int i10, View view, ViewGroup viewGroup) {
            n.e(viewGroup, "parent");
            View view2 = super.getView(i10, view, viewGroup);
            n.d(view2, "getView(...)");
            view2.setPadding(0, 0, 0, 0);
            return view2;
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class c implements be.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentGetEarliestDates f9322a;

        c(AppointmentGetEarliestDates appointmentGetEarliestDates) {
            this.f9322a = appointmentGetEarliestDates;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(int i10, String str) {
            n.e(str, "errorMsg");
            Utility.f9497a.c();
            return nb.x.f15721a;
        }

        public void a(be.d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            try {
                if (xVar.a() != null) {
                    AppointmentGetEarliestDates appointmentGetEarliestDates = this.f9322a;
                    Object a10 = xVar.a();
                    n.b(a10);
                    appointmentGetEarliestDates.o2((List) a10);
                    if (!this.f9322a.X1().isEmpty()) {
                        int size = this.f9322a.X1().size();
                        for (int i10 = 0; i10 < size; i10++) {
                            if (((CenterCodeResponseData) this.f9322a.X1().get(i10)).getCenterName() != null) {
                                ArrayList P1 = this.f9322a.P1();
                                n.b(P1);
                                String centerName = ((CenterCodeResponseData) this.f9322a.X1().get(i10)).getCenterName();
                                n.b(centerName);
                                P1.add(centerName);
                                ArrayList O1 = this.f9322a.O1();
                                n.b(O1);
                                String isoCode = ((CenterCodeResponseData) this.f9322a.X1().get(i10)).getIsoCode();
                                n.b(isoCode);
                                O1.add(isoCode);
                            } else {
                                Utility.Companion companion = Utility.f9497a;
                                companion.c();
                                Activity c12 = this.f9322a.c1();
                                ErrorMessage error = ((CenterCodeResponseData) this.f9322a.X1().get(i10)).getError();
                                n.b(error);
                                String description = error.getDescription();
                                n.b(description);
                                companion.w(c12, description);
                            }
                        }
                        AppointmentGetEarliestDates appointmentGetEarliestDates2 = this.f9322a;
                        appointmentGetEarliestDates2.r2(appointmentGetEarliestDates2.P1());
                        return;
                    }
                    return;
                }
                com.vfs.italyglobal.utilities.c.h(xVar, this.f9322a.c1(), new q0());
            } catch (Exception e10) {
                e10.toString();
            }
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (th instanceof SocketTimeoutException) {
                companion.z(this.f9322a.c1());
                this.f9322a.onBackPressed();
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class d implements be.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentGetEarliestDates f9323a;

        d(AppointmentGetEarliestDates appointmentGetEarliestDates) {
            this.f9323a = appointmentGetEarliestDates;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(int i10, String str) {
            n.e(str, "errorMsg");
            Utility.f9497a.c();
            return nb.x.f15721a;
        }

        public void a(be.d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            try {
                if (xVar.a() != null) {
                    List list = (List) xVar.a();
                    if (list == null) {
                        Utility.Companion companion = Utility.f9497a;
                        Activity c12 = this.f9323a.c1();
                        String string = this.f9323a.getString(R.string.generic_error);
                        n.d(string, "getString(...)");
                        companion.w(c12, string);
                    } else if (!list.isEmpty()) {
                        ArrayList S1 = this.f9323a.S1();
                        n.b(S1);
                        S1.clear();
                        ArrayList R1 = this.f9323a.R1();
                        n.b(R1);
                        R1.clear();
                        int size = list.size();
                        for (int i10 = 0; i10 < size; i10++) {
                            if (((VisaCategoryDataResponse) list.get(i10)).getError() == null) {
                                ArrayList S12 = this.f9323a.S1();
                                n.b(S12);
                                S12.add(((VisaCategoryDataResponse) list.get(i10)).getName());
                                ArrayList R12 = this.f9323a.R1();
                                n.b(R12);
                                R12.add(((VisaCategoryDataResponse) list.get(i10)).getCode());
                            } else {
                                Utility.Companion companion2 = Utility.f9497a;
                                Activity c13 = this.f9323a.c1();
                                ErrorMessage error = ((VisaCategoryDataResponse) list.get(i10)).getError();
                                n.b(error);
                                companion2.w(c13, String.valueOf(error.getDescription()));
                                companion2.c();
                            }
                        }
                        AppointmentGetEarliestDates appointmentGetEarliestDates = this.f9323a;
                        appointmentGetEarliestDates.t2(appointmentGetEarliestDates.S1());
                        this.f9323a.u2(new ArrayList());
                        this.f9323a.s2(new ArrayList());
                    }
                } else {
                    com.vfs.italyglobal.utilities.c.h(xVar, this.f9323a.c1(), new r0());
                }
            } catch (Exception e10) {
                e10.toString();
            }
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (th instanceof SocketTimeoutException) {
                companion.z(this.f9323a.c1());
                this.f9323a.onBackPressed();
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class e implements be.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentGetEarliestDates f9324a;

        e(AppointmentGetEarliestDates appointmentGetEarliestDates) {
            this.f9324a = appointmentGetEarliestDates;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(int i10, String str) {
            n.e(str, "errorMsg");
            Utility.f9497a.c();
            return nb.x.f15721a;
        }

        public void a(be.d dVar, be.x xVar) {
            String str;
            String str2;
            n.e(dVar, "call");
            n.e(xVar, "response");
            try {
                Utility.Companion companion = Utility.f9497a;
                companion.c();
                if (xVar.a() != null) {
                    i0 i0Var = (i0) xVar.a();
                    if (i0Var != null) {
                        str = i0Var.o();
                    } else {
                        str = null;
                    }
                    Object nextValue = new JSONTokener(str).nextValue();
                    n.c(nextValue, "null cannot be cast to non-null type kotlin.Any");
                    com.google.gson.e eVar = new com.google.gson.e();
                    if (nextValue instanceof JSONObject) {
                        companion.w(this.f9324a.c1(), String.valueOf(((ErrorMessage) eVar.i(((JSONObject) nextValue).toString(), ErrorMessage.class)).getDescription()));
                    } else if (nextValue instanceof JSONArray) {
                        Object i10 = eVar.i(str, VisaSubCategoryResponseData[].class);
                        n.d(i10, "fromJson(...)");
                        List Q = ob.j.Q((Object[]) i10);
                        if (Q == null) {
                            Activity c12 = this.f9324a.c1();
                            String string = this.f9324a.getString(R.string.generic_error);
                            n.d(string, "getString(...)");
                            companion.w(c12, string);
                        } else if (!Q.isEmpty()) {
                            ArrayList V1 = this.f9324a.V1();
                            n.b(V1);
                            V1.clear();
                            ArrayList U1 = this.f9324a.U1();
                            n.b(U1);
                            U1.clear();
                            ArrayList T1 = this.f9324a.T1();
                            n.b(T1);
                            T1.clear();
                            int size = Q.size();
                            for (int i11 = 0; i11 < size; i11++) {
                                if (((VisaSubCategoryResponseData) Q.get(i11)).getError() == null) {
                                    ArrayList V12 = this.f9324a.V1();
                                    n.b(V12);
                                    V12.add(((VisaSubCategoryResponseData) Q.get(i11)).getName());
                                    ArrayList U12 = this.f9324a.U1();
                                    n.b(U12);
                                    U12.add(((VisaSubCategoryResponseData) Q.get(i11)).getCode());
                                    ArrayList T12 = this.f9324a.T1();
                                    n.b(T12);
                                    T12.add(Q.get(i11));
                                } else {
                                    Utility.Companion companion2 = Utility.f9497a;
                                    Activity c13 = this.f9324a.c1();
                                    ErrorMessage error = ((VisaSubCategoryResponseData) Q.get(i11)).getError();
                                    if (error != null) {
                                        str2 = error.getDescription();
                                    } else {
                                        str2 = null;
                                    }
                                    companion2.w(c13, String.valueOf(str2));
                                    companion2.c();
                                }
                            }
                            AppointmentGetEarliestDates appointmentGetEarliestDates = this.f9324a;
                            appointmentGetEarliestDates.u2(appointmentGetEarliestDates.V1());
                        }
                    }
                } else {
                    com.vfs.italyglobal.utilities.c.h(xVar, this.f9324a.c1(), new s0());
                }
            } catch (Exception e10) {
                e10.toString();
            }
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (th instanceof SocketTimeoutException) {
                companion.z(this.f9324a.c1());
                this.f9324a.onBackPressed();
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class f implements be.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentGetEarliestDates f9325a;

        f(AppointmentGetEarliestDates appointmentGetEarliestDates) {
            this.f9325a = appointmentGetEarliestDates;
        }

        /* access modifiers changed from: private */
        public static final nb.x e(be.x xVar, AppointmentGetEarliestDates appointmentGetEarliestDates, int i10, String str) {
            n.e(str, "errorMsg");
            if (!(i10 == 403 || i10 == 429)) {
                i0 d10 = xVar.d();
                if (d10 != null) {
                    Integer status = ((ErrorResponseModel) new com.google.gson.e().i(d10.o(), ErrorResponseModel.class)).getStatus();
                    if (status != null && status.intValue() == 401) {
                        String string = appointmentGetEarliestDates.getString(R.string.msg_session_expired);
                        n.d(string, "getString(...)");
                        com.vfs.italyglobal.utilities.c.e(appointmentGetEarliestDates, string, (String) null, new u0(appointmentGetEarliestDates), false, (String) null, (ac.a) null, false, 122, (Object) null).show();
                    } else {
                        AppointmentGetEarliestDates appointmentGetEarliestDates2 = appointmentGetEarliestDates;
                        Utility.Companion companion = Utility.f9497a;
                        Activity c12 = appointmentGetEarliestDates2.c1();
                        String string2 = appointmentGetEarliestDates2.getString(R.string.generic_error);
                        n.d(string2, "getString(...)");
                        companion.w(c12, string2);
                    }
                } else {
                    AppointmentGetEarliestDates appointmentGetEarliestDates3 = appointmentGetEarliestDates;
                    Utility.Companion companion2 = Utility.f9497a;
                    Activity c13 = appointmentGetEarliestDates3.c1();
                    String string3 = appointmentGetEarliestDates3.getString(R.string.generic_error);
                    n.d(string3, "getString(...)");
                    companion2.w(c13, string3);
                }
            }
            return nb.x.f15721a;
        }

        /* access modifiers changed from: private */
        public static final nb.x f(AppointmentGetEarliestDates appointmentGetEarliestDates) {
            Intent intent = new Intent(appointmentGetEarliestDates.c1(), AppointmentLogin.class);
            intent.setFlags(268468224);
            appointmentGetEarliestDates.startActivity(intent);
            return nb.x.f15721a;
        }

        public void a(be.d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            try {
                CheckIsSlotAvailableResModel checkIsSlotAvailableResModel = (CheckIsSlotAvailableResModel) xVar.a();
                if (checkIsSlotAvailableResModel != null) {
                    AppointmentGetEarliestDates appointmentGetEarliestDates = this.f9325a;
                    String earliestDate = checkIsSlotAvailableResModel.getEarliestDate();
                    if (earliestDate != null) {
                        String n10 = companion.n(earliestDate);
                        appointmentGetEarliestDates.Y1().f20566i.setVisibility(0);
                        appointmentGetEarliestDates.Y1().f20559b.setClickable(true);
                        appointmentGetEarliestDates.Y1().f20559b.setEnabled(true);
                        AppCompatTextView appCompatTextView = appointmentGetEarliestDates.Y1().f20573p;
                        a0 a0Var = a0.f4702a;
                        String string = appointmentGetEarliestDates.getString(R.string.lbl_slots_available);
                        n.d(string, "getString(...)");
                        String format = String.format(string, Arrays.copyOf(new Object[]{companion.f(n10)}, 1));
                        n.d(format, "format(...)");
                        appCompatTextView.setText(format);
                        g.a aVar = com.vfs.italyglobal.utilities.g.f9512a;
                        Activity c12 = appointmentGetEarliestDates.c1();
                        String string2 = appointmentGetEarliestDates.getString(R.string.appmnt_earliest_date);
                        n.d(string2, "getString(...)");
                        aVar.g(c12, string2, n10);
                        return;
                    }
                    ErrorMessage error = checkIsSlotAvailableResModel.getError();
                    if (error == null) {
                        Activity c13 = appointmentGetEarliestDates.c1();
                        String string3 = appointmentGetEarliestDates.getString(R.string.generic_error);
                        n.d(string3, "getString(...)");
                        companion.w(c13, string3);
                    } else if (n.a(error.getCode(), "1035")) {
                        appointmentGetEarliestDates.Y1().f20566i.setVisibility(0);
                        appointmentGetEarliestDates.Y1().f20559b.setClickable(false);
                        appointmentGetEarliestDates.Y1().f20559b.setEnabled(false);
                        appointmentGetEarliestDates.Y1().f20573p.setText(appointmentGetEarliestDates.getString(R.string.lbl_no_slots_available_note));
                    } else {
                        companion.w(appointmentGetEarliestDates.c1(), String.valueOf(error.getDescription()));
                    }
                } else {
                    AppointmentGetEarliestDates appointmentGetEarliestDates2 = this.f9325a;
                    com.vfs.italyglobal.utilities.c.h(xVar, appointmentGetEarliestDates2.c1(), new t0(xVar, appointmentGetEarliestDates2));
                }
            } catch (Exception e10) {
                e10.printStackTrace();
            }
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (th instanceof SocketTimeoutException) {
                companion.z(this.f9325a.c1());
                this.f9325a.onBackPressed();
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class g implements be.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AppointmentGetEarliestDates f9326a;

        g(AppointmentGetEarliestDates appointmentGetEarliestDates) {
            this.f9326a = appointmentGetEarliestDates;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(int i10, String str) {
            n.e(str, "errorMsg");
            Utility.f9497a.c();
            return nb.x.f15721a;
        }

        public void a(be.d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            try {
                Utility.f9497a.c();
                if (xVar.a() != null) {
                    List list = (List) xVar.a();
                    n.b(list);
                    if (!list.isEmpty()) {
                        ArrayList<PaymentMode> arrayList = new ArrayList<>();
                        for (Object next : list) {
                            if (n.a(((PaymentMode) next).getIsActive(), Boolean.TRUE)) {
                                arrayList.add(next);
                            }
                        }
                        ArrayList<String> arrayList2 = new ArrayList<>(p.t(arrayList, 10));
                        for (PaymentMode name : arrayList) {
                            arrayList2.add(name.getName());
                        }
                        for (String valueOf : arrayList2) {
                            ArrayList Q1 = this.f9326a.Q1();
                            n.b(Q1);
                            Q1.add(String.valueOf(valueOf));
                        }
                    }
                    AppointmentGetEarliestDates appointmentGetEarliestDates = this.f9326a;
                    appointmentGetEarliestDates.s2(appointmentGetEarliestDates.Q1());
                    return;
                }
                com.vfs.italyglobal.utilities.c.h(xVar, this.f9326a.c1(), new v0());
            } catch (Exception e10) {
                e10.toString();
            }
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            if (th instanceof SocketTimeoutException) {
                companion.z(this.f9326a.c1());
                this.f9326a.onBackPressed();
            }
        }
    }

    public AppointmentGetEarliestDates() {
        super(false, false, 3, (bc.h) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0058 A[Catch:{ Exception -> 0x0030 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void I1() {
        /*
            r7 = this;
            com.vfs.italyglobal.utilities.Utility$Companion r0 = com.vfs.italyglobal.utilities.Utility.f9497a     // Catch:{ Exception -> 0x0030 }
            java.lang.String r1 = r0.q()     // Catch:{ Exception -> 0x0030 }
            android.app.Activity r2 = r7.c1()     // Catch:{ Exception -> 0x0030 }
            java.lang.String r2 = r0.k(r2)     // Catch:{ Exception -> 0x0030 }
            java.lang.String r3 = r0.l()     // Catch:{ Exception -> 0x0030 }
            java.lang.String r0 = r0.e(r7, r3)     // Catch:{ Exception -> 0x0030 }
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ Exception -> 0x0030 }
            r3.<init>()     // Catch:{ Exception -> 0x0030 }
            java.lang.String r4 = "referer"
            com.vfs.italyglobal.AppDelegate r5 = r7.d1()     // Catch:{ Exception -> 0x0030 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0030 }
            java.lang.String r6 = ""
            if (r5 == 0) goto L_0x0032
            java.lang.String r5 = r5.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0030 }
            if (r5 != 0) goto L_0x0033
            goto L_0x0032
        L_0x0030:
            r0 = move-exception
            goto L_0x0079
        L_0x0032:
            r5 = r6
        L_0x0033:
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0030 }
            java.lang.String r4 = "origin"
            com.vfs.italyglobal.AppDelegate r5 = r7.d1()     // Catch:{ Exception -> 0x0030 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0030 }
            if (r5 == 0) goto L_0x0048
            java.lang.String r5 = r5.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0030 }
            if (r5 != 0) goto L_0x0049
        L_0x0048:
            r5 = r6
        L_0x0049:
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0030 }
            java.lang.String r4 = "cfmlift"
            com.vfs.italyglobal.AppDelegate r5 = r7.d1()     // Catch:{ Exception -> 0x0030 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0030 }
            if (r5 == 0) goto L_0x0060
            java.lang.String r5 = r5.getAppointmentCfmLift()     // Catch:{ Exception -> 0x0030 }
            if (r5 != 0) goto L_0x005f
            goto L_0x0060
        L_0x005f:
            r6 = r5
        L_0x0060:
            r3.put(r4, r6)     // Catch:{ Exception -> 0x0030 }
            java.lang.String r4 = "ClientSource"
            r3.put(r4, r0)     // Catch:{ Exception -> 0x0030 }
            ab.b r0 = r7.f1()     // Catch:{ Exception -> 0x0030 }
            be.d r0 = r0.f(r3, r1, r2)     // Catch:{ Exception -> 0x0030 }
            com.vfs.italyglobal.activities.AppointmentGetEarliestDates$c r1 = new com.vfs.italyglobal.activities.AppointmentGetEarliestDates$c     // Catch:{ Exception -> 0x0030 }
            r1.<init>(r7)     // Catch:{ Exception -> 0x0030 }
            r0.A(r1)     // Catch:{ Exception -> 0x0030 }
            return
        L_0x0079:
            r0.toString()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.AppointmentGetEarliestDates.I1():void");
    }

    private final int N1(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date parse = simpleDateFormat.parse(str);
        n.b(parse);
        Date parse2 = simpleDateFormat.parse(str2);
        n.b(parse2);
        return (int) TimeUnit.MILLISECONDS.toDays(parse2.getTime() - parse.getTime());
    }

    /* access modifiers changed from: private */
    public final n1 Y1() {
        return (n1) this.f9304f0.getValue();
    }

    /* access modifiers changed from: private */
    public static final void e2(AppointmentGetEarliestDates appointmentGetEarliestDates, View view) {
        AppCompatEditText appCompatEditText = appointmentGetEarliestDates.Y1().f20560c;
        n.d(appCompatEditText, "edtFromDateApmnt");
        AppCompatEditText appCompatEditText2 = appointmentGetEarliestDates.Y1().f20562e;
        n.d(appCompatEditText2, "edtNoOfCalculatedDays");
        appointmentGetEarliestDates.l2(appCompatEditText, 1, appCompatEditText2);
    }

    /* access modifiers changed from: private */
    public static final void f2(AppointmentGetEarliestDates appointmentGetEarliestDates, View view) {
        AppCompatEditText appCompatEditText = appointmentGetEarliestDates.Y1().f20563f;
        n.d(appCompatEditText, "edtToDateApmnt");
        AppCompatEditText appCompatEditText2 = appointmentGetEarliestDates.Y1().f20562e;
        n.d(appCompatEditText2, "edtNoOfCalculatedDays");
        appointmentGetEarliestDates.l2(appCompatEditText, 2, appCompatEditText2);
    }

    /* access modifiers changed from: private */
    public static final void g2(AppointmentGetEarliestDates appointmentGetEarliestDates, View view) {
        appointmentGetEarliestDates.j2();
    }

    /* access modifiers changed from: private */
    public static final void h2(AppointmentGetEarliestDates appointmentGetEarliestDates, View view) {
        if (com.vfs.italyglobal.utilities.c.j(appointmentGetEarliestDates)) {
            appointmentGetEarliestDates.M1();
            return;
        }
        Utility.Companion companion = Utility.f9497a;
        String string = appointmentGetEarliestDates.getString(R.string.network_error);
        n.d(string, "getString(...)");
        companion.w(appointmentGetEarliestDates, string);
    }

    /* access modifiers changed from: private */
    public static final n1 i2(AppointmentGetEarliestDates appointmentGetEarliestDates) {
        return n1.c(appointmentGetEarliestDates.getLayoutInflater());
    }

    private final void k2() {
        Intent intent = new Intent(this, AddApplicantBookAppointmentActivity.class);
        Utility.f9497a.c();
        String string = getString(R.string.bundle_isOTPEnabled);
        VisaSubCategoryResponseData visaSubCategoryResponseData = this.f9318t0;
        n.b(visaSubCategoryResponseData);
        intent.putExtra(string, visaSubCategoryResponseData.isScheduleOTPEnabled());
        String string2 = getString(R.string.bundle_isApplicantOTPEnabled);
        VisaSubCategoryResponseData visaSubCategoryResponseData2 = this.f9318t0;
        n.b(visaSubCategoryResponseData2);
        intent.putExtra(string2, visaSubCategoryResponseData2.isApplicantOTPEnabled());
        String string3 = getString(R.string.bundle_isFaceLivenessEnabled);
        VisaSubCategoryResponseData visaSubCategoryResponseData3 = this.f9318t0;
        n.b(visaSubCategoryResponseData3);
        intent.putExtra(string3, visaSubCategoryResponseData3.isSelfiUploadEnable());
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    private final void l2(EditText editText, int i10, EditText editText2) {
        Calendar instance = Calendar.getInstance();
        n.d(instance, "getInstance(...)");
        this.f9319u0 = instance;
        Calendar calendar = null;
        if (instance == null) {
            n.o("myCalendar");
            instance = null;
        }
        int i11 = instance.get(1);
        Calendar calendar2 = this.f9319u0;
        if (calendar2 == null) {
            n.o("myCalendar");
            calendar2 = null;
        }
        int i12 = calendar2.get(2);
        Calendar calendar3 = this.f9319u0;
        if (calendar3 == null) {
            n.o("myCalendar");
            calendar3 = null;
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new p0(this, editText, editText2), i11, i12, calendar3.get(5));
        Calendar calendar4 = this.f9319u0;
        if (calendar4 == null) {
            n.o("myCalendar");
        } else {
            calendar = calendar4;
        }
        datePickerDialog.getDatePicker().setMinDate(calendar.getTime().getTime());
        datePickerDialog.show();
    }

    /* access modifiers changed from: private */
    public static final void m2(AppointmentGetEarliestDates appointmentGetEarliestDates, EditText editText, EditText editText2, DatePicker datePicker, int i10, int i11, int i12) {
        Calendar calendar = appointmentGetEarliestDates.f9319u0;
        Calendar calendar2 = null;
        if (calendar == null) {
            n.o("myCalendar");
            calendar = null;
        }
        calendar.set(1, i10);
        Calendar calendar3 = appointmentGetEarliestDates.f9319u0;
        if (calendar3 == null) {
            n.o("myCalendar");
            calendar3 = null;
        }
        calendar3.set(2, i11);
        Calendar calendar4 = appointmentGetEarliestDates.f9319u0;
        if (calendar4 == null) {
            n.o("myCalendar");
            calendar4 = null;
        }
        calendar4.set(5, i12);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Calendar calendar5 = appointmentGetEarliestDates.f9319u0;
        if (calendar5 == null) {
            n.o("myCalendar");
        } else {
            calendar2 = calendar5;
        }
        editText.setText(simpleDateFormat.format(calendar2.getTime()));
        nb.x.f15721a.toString();
        if (String.valueOf(appointmentGetEarliestDates.Y1().f20563f.getText()).length() > 0 && String.valueOf(appointmentGetEarliestDates.Y1().f20560c.getText()).length() > 0) {
            int N1 = appointmentGetEarliestDates.N1(String.valueOf(appointmentGetEarliestDates.Y1().f20560c.getText()), String.valueOf(appointmentGetEarliestDates.Y1().f20563f.getText()));
            appointmentGetEarliestDates.f9309k0 = N1;
            if (N1 < 1) {
                Utility.Companion companion = Utility.f9497a;
                Activity c12 = appointmentGetEarliestDates.c1();
                String string = appointmentGetEarliestDates.getString(R.string.to_date_must_be_greater_than_from_date);
                n.d(string, "getString(...)");
                companion.w(c12, string);
                appointmentGetEarliestDates.Y1().f20563f.setText("");
                return;
            }
            editText2.setText(String.valueOf(N1));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x007b A[Catch:{ Exception -> 0x0053 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void J1(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = "centerCode"
            bc.n.e(r8, r0)
            xa.n1 r0 = r7.Y1()     // Catch:{ Exception -> 0x0053 }
            androidx.appcompat.widget.LinearLayoutCompat r0 = r0.f20566i     // Catch:{ Exception -> 0x0053 }
            r1 = 8
            r0.setVisibility(r1)     // Catch:{ Exception -> 0x0053 }
            xa.n1 r0 = r7.Y1()     // Catch:{ Exception -> 0x0053 }
            androidx.appcompat.widget.AppCompatButton r0 = r0.f20559b     // Catch:{ Exception -> 0x0053 }
            r1 = 0
            r0.setClickable(r1)     // Catch:{ Exception -> 0x0053 }
            xa.n1 r0 = r7.Y1()     // Catch:{ Exception -> 0x0053 }
            androidx.appcompat.widget.AppCompatButton r0 = r0.f20559b     // Catch:{ Exception -> 0x0053 }
            r0.setEnabled(r1)     // Catch:{ Exception -> 0x0053 }
            com.vfs.italyglobal.utilities.Utility$Companion r0 = com.vfs.italyglobal.utilities.Utility.f9497a     // Catch:{ Exception -> 0x0053 }
            java.lang.String r1 = r0.q()     // Catch:{ Exception -> 0x0053 }
            android.app.Activity r2 = r7.c1()     // Catch:{ Exception -> 0x0053 }
            java.lang.String r2 = r0.k(r2)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r3 = r0.l()     // Catch:{ Exception -> 0x0053 }
            java.lang.String r0 = r0.e(r7, r3)     // Catch:{ Exception -> 0x0053 }
            java.util.HashMap r3 = new java.util.HashMap     // Catch:{ Exception -> 0x0053 }
            r3.<init>()     // Catch:{ Exception -> 0x0053 }
            java.lang.String r4 = "referer"
            com.vfs.italyglobal.AppDelegate r5 = r7.d1()     // Catch:{ Exception -> 0x0053 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0053 }
            java.lang.String r6 = ""
            if (r5 == 0) goto L_0x0055
            java.lang.String r5 = r5.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0053 }
            if (r5 != 0) goto L_0x0056
            goto L_0x0055
        L_0x0053:
            r8 = move-exception
            goto L_0x009c
        L_0x0055:
            r5 = r6
        L_0x0056:
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r4 = "origin"
            com.vfs.italyglobal.AppDelegate r5 = r7.d1()     // Catch:{ Exception -> 0x0053 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0053 }
            if (r5 == 0) goto L_0x006b
            java.lang.String r5 = r5.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0053 }
            if (r5 != 0) goto L_0x006c
        L_0x006b:
            r5 = r6
        L_0x006c:
            r3.put(r4, r5)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r4 = "cfmlift"
            com.vfs.italyglobal.AppDelegate r5 = r7.d1()     // Catch:{ Exception -> 0x0053 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0053 }
            if (r5 == 0) goto L_0x0083
            java.lang.String r5 = r5.getAppointmentCfmLift()     // Catch:{ Exception -> 0x0053 }
            if (r5 != 0) goto L_0x0082
            goto L_0x0083
        L_0x0082:
            r6 = r5
        L_0x0083:
            r3.put(r4, r6)     // Catch:{ Exception -> 0x0053 }
            java.lang.String r4 = "ClientSource"
            r3.put(r4, r0)     // Catch:{ Exception -> 0x0053 }
            ab.b r0 = r7.f1()     // Catch:{ Exception -> 0x0053 }
            be.d r8 = r0.e(r3, r1, r2, r8)     // Catch:{ Exception -> 0x0053 }
            com.vfs.italyglobal.activities.AppointmentGetEarliestDates$d r0 = new com.vfs.italyglobal.activities.AppointmentGetEarliestDates$d     // Catch:{ Exception -> 0x0053 }
            r0.<init>(r7)     // Catch:{ Exception -> 0x0053 }
            r8.A(r0)     // Catch:{ Exception -> 0x0053 }
            return
        L_0x009c:
            r8.toString()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.AppointmentGetEarliestDates.J1(java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0062 A[Catch:{ Exception -> 0x0039 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void K1(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = "visaCategory"
            bc.n.e(r8, r0)
            com.vfs.italyglobal.utilities.Utility$Companion r0 = com.vfs.italyglobal.utilities.Utility.f9497a     // Catch:{ Exception -> 0x0039 }
            java.lang.String r3 = r0.q()     // Catch:{ Exception -> 0x0039 }
            android.app.Activity r1 = r7.c1()     // Catch:{ Exception -> 0x0039 }
            java.lang.String r4 = r0.k(r1)     // Catch:{ Exception -> 0x0039 }
            android.app.Activity r1 = r7.c1()     // Catch:{ Exception -> 0x0039 }
            java.lang.String r2 = r0.l()     // Catch:{ Exception -> 0x0039 }
            java.lang.String r0 = r0.e(r1, r2)     // Catch:{ Exception -> 0x0039 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ Exception -> 0x0039 }
            r2.<init>()     // Catch:{ Exception -> 0x0039 }
            java.lang.String r1 = "referer"
            com.vfs.italyglobal.AppDelegate r5 = r7.d1()     // Catch:{ Exception -> 0x0039 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0039 }
            java.lang.String r6 = ""
            if (r5 == 0) goto L_0x003c
            java.lang.String r5 = r5.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0039 }
            if (r5 != 0) goto L_0x003d
            goto L_0x003c
        L_0x0039:
            r0 = move-exception
            r8 = r0
            goto L_0x0088
        L_0x003c:
            r5 = r6
        L_0x003d:
            r2.put(r1, r5)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r1 = "origin"
            com.vfs.italyglobal.AppDelegate r5 = r7.d1()     // Catch:{ Exception -> 0x0039 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0039 }
            if (r5 == 0) goto L_0x0052
            java.lang.String r5 = r5.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0039 }
            if (r5 != 0) goto L_0x0053
        L_0x0052:
            r5 = r6
        L_0x0053:
            r2.put(r1, r5)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r1 = "cfmlift"
            com.vfs.italyglobal.AppDelegate r5 = r7.d1()     // Catch:{ Exception -> 0x0039 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0039 }
            if (r5 == 0) goto L_0x006a
            java.lang.String r5 = r5.getAppointmentCfmLift()     // Catch:{ Exception -> 0x0039 }
            if (r5 != 0) goto L_0x0069
            goto L_0x006a
        L_0x0069:
            r6 = r5
        L_0x006a:
            r2.put(r1, r6)     // Catch:{ Exception -> 0x0039 }
            java.lang.String r1 = "ClientSource"
            r2.put(r1, r0)     // Catch:{ Exception -> 0x0039 }
            ab.b r1 = r7.f1()     // Catch:{ Exception -> 0x0039 }
            java.lang.String r5 = r7.W1()     // Catch:{ Exception -> 0x0039 }
            r6 = r8
            be.d r8 = r1.K(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0039 }
            com.vfs.italyglobal.activities.AppointmentGetEarliestDates$e r0 = new com.vfs.italyglobal.activities.AppointmentGetEarliestDates$e     // Catch:{ Exception -> 0x0039 }
            r0.<init>(r7)     // Catch:{ Exception -> 0x0039 }
            r8.A(r0)     // Catch:{ Exception -> 0x0039 }
            return
        L_0x0088:
            r8.toString()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.AppointmentGetEarliestDates.K1(java.lang.String):void");
    }

    public final void L1() {
        String str;
        String str2;
        String str3;
        Utility.Companion companion = Utility.f9497a;
        companion.y(c1());
        HashMap hashMap = new HashMap();
        AppConfigModel e10 = d1().e();
        if (e10 == null || (str = e10.getAppointmentUrlOrigin()) == null) {
            str = "";
        }
        hashMap.put("referer", str);
        AppConfigModel e11 = d1().e();
        if (e11 == null || (str2 = e11.getAppointmentUrlOrigin()) == null) {
            str2 = "";
        }
        hashMap.put("origin", str2);
        AppConfigModel e12 = d1().e();
        if (e12 == null || (str3 = e12.getAppointmentCfmLift()) == null) {
            str3 = "";
        }
        hashMap.put("cfmlift", str3);
        g.a aVar = com.vfs.italyglobal.utilities.g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appmnt_login_authToken);
        n.d(string, "getString(...)");
        hashMap.put("Authorize", aVar.b(c12, string, ""));
        hashMap.put("ClientSource", companion.e(c1(), companion.l()));
        String k10 = companion.k(c1());
        String q10 = companion.q();
        String W1 = W1();
        String c22 = c2();
        String string2 = getString(R.string.appmnt_login_roleName);
        n.d(string2, "getString(...)");
        g.a aVar2 = aVar;
        String str4 = string2;
        g.a aVar3 = aVar2;
        String d10 = g.a.d(aVar3, this, str4, (String) null, 4, (Object) null);
        String string3 = getString(R.string.appmnt_login_loggedInUser);
        n.d(string3, "getString(...)");
        CheckIsSlotAvailableRequestParams checkIsSlotAvailableRequestParams = new CheckIsSlotAvailableRequestParams(k10, q10, W1, c22, d10, g.a.d(aVar3, this, string3, (String) null, 4, (Object) null));
        checkIsSlotAvailableRequestParams.toString();
        f1().z(hashMap, checkIsSlotAvailableRequestParams).A(new f(this));
    }

    public final void M1() {
        if (W1() != null) {
            String str = "";
            if (!n.a(W1(), str)) {
                if (b2() == null || n.a(b2(), str)) {
                    Utility.Companion companion = Utility.f9497a;
                    Activity c12 = c1();
                    String string = getString(R.string.visa_cat_validation);
                    n.d(string, "getString(...)");
                    companion.w(c12, string);
                    return;
                } else if (c2() == null || n.a(c2(), str)) {
                    Utility.Companion companion2 = Utility.f9497a;
                    Activity c13 = c1();
                    String string2 = getString(R.string.visa_sub_cat_validation);
                    n.d(string2, "getString(...)");
                    companion2.w(c13, string2);
                    return;
                } else {
                    if (n.a(Utility.f9497a.k(c1()), "DZA")) {
                        String a22 = a2();
                        Locale locale = Locale.ROOT;
                        String lowerCase = a22.toLowerCase(locale);
                        n.d(lowerCase, "toLowerCase(...)");
                        String lowerCase2 = "ONLINE".toLowerCase(locale);
                        n.d(lowerCase2, "toLowerCase(...)");
                        if (kc.n.Q(lowerCase, lowerCase2, false, 2, (Object) null)) {
                            str = "PAYOnline";
                        } else {
                            String lowerCase3 = a2().toLowerCase(locale);
                            n.d(lowerCase3, "toLowerCase(...)");
                            String lowerCase4 = "VAC".toLowerCase(locale);
                            n.d(lowerCase4, "toLowerCase(...)");
                            if (kc.n.Q(lowerCase3, lowerCase4, false, 2, (Object) null)) {
                                str = "PAYAtVAC";
                            }
                        }
                        g.a aVar = com.vfs.italyglobal.utilities.g.f9512a;
                        Activity c14 = c1();
                        String string3 = getString(R.string.appmnt_visa_sub_category_payment_mode);
                        n.d(string3, "getString(...)");
                        aVar.g(c14, string3, str);
                    }
                    k2();
                    return;
                }
            }
        }
        Utility.Companion companion3 = Utility.f9497a;
        Activity c15 = c1();
        String string4 = getString(R.string.visa_center_validation);
        n.d(string4, "getString(...)");
        companion3.w(c15, string4);
    }

    public final ArrayList O1() {
        return this.f9311m0;
    }

    public final ArrayList P1() {
        return this.f9310l0;
    }

    public final ArrayList Q1() {
        return this.f9317s0;
    }

    public final ArrayList R1() {
        return this.f9312n0;
    }

    public final ArrayList S1() {
        return this.f9313o0;
    }

    public final ArrayList T1() {
        return this.f9314p0;
    }

    public final ArrayList U1() {
        return this.f9315q0;
    }

    public final ArrayList V1() {
        return this.f9316r0;
    }

    public final String W1() {
        String str = this.f9305g0;
        if (str != null) {
            return str;
        }
        n.o("centerCode");
        return null;
    }

    public final List X1() {
        List list = this.f9321w0;
        if (list != null) {
            return list;
        }
        n.o("centerMasterData");
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005e A[Catch:{ Exception -> 0x0035 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void Z1(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "visaSubCategoryCode"
            bc.n.e(r9, r0)
            com.vfs.italyglobal.utilities.Utility$Companion r0 = com.vfs.italyglobal.utilities.Utility.f9497a     // Catch:{ Exception -> 0x0035 }
            java.lang.String r3 = r0.q()     // Catch:{ Exception -> 0x0035 }
            android.app.Activity r1 = r8.c1()     // Catch:{ Exception -> 0x0035 }
            java.lang.String r4 = r0.k(r1)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r1 = r0.l()     // Catch:{ Exception -> 0x0035 }
            java.lang.String r0 = r0.e(r8, r1)     // Catch:{ Exception -> 0x0035 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ Exception -> 0x0035 }
            r2.<init>()     // Catch:{ Exception -> 0x0035 }
            java.lang.String r1 = "referer"
            com.vfs.italyglobal.AppDelegate r5 = r8.d1()     // Catch:{ Exception -> 0x0035 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0035 }
            java.lang.String r6 = ""
            if (r5 == 0) goto L_0x0038
            java.lang.String r5 = r5.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0035 }
            if (r5 != 0) goto L_0x0039
            goto L_0x0038
        L_0x0035:
            r0 = move-exception
            r9 = r0
            goto L_0x0086
        L_0x0038:
            r5 = r6
        L_0x0039:
            r2.put(r1, r5)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r1 = "origin"
            com.vfs.italyglobal.AppDelegate r5 = r8.d1()     // Catch:{ Exception -> 0x0035 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0035 }
            if (r5 == 0) goto L_0x004e
            java.lang.String r5 = r5.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0035 }
            if (r5 != 0) goto L_0x004f
        L_0x004e:
            r5 = r6
        L_0x004f:
            r2.put(r1, r5)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r1 = "cfmlift"
            com.vfs.italyglobal.AppDelegate r5 = r8.d1()     // Catch:{ Exception -> 0x0035 }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x0035 }
            if (r5 == 0) goto L_0x0066
            java.lang.String r5 = r5.getAppointmentCfmLift()     // Catch:{ Exception -> 0x0035 }
            if (r5 != 0) goto L_0x0065
            goto L_0x0066
        L_0x0065:
            r6 = r5
        L_0x0066:
            r2.put(r1, r6)     // Catch:{ Exception -> 0x0035 }
            java.lang.String r1 = "ClientSource"
            r2.put(r1, r0)     // Catch:{ Exception -> 0x0035 }
            ab.b r1 = r8.f1()     // Catch:{ Exception -> 0x0035 }
            java.lang.String r5 = r8.W1()     // Catch:{ Exception -> 0x0035 }
            java.lang.String r6 = "en-US"
            r7 = r9
            be.d r9 = r1.J(r2, r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x0035 }
            com.vfs.italyglobal.activities.AppointmentGetEarliestDates$g r0 = new com.vfs.italyglobal.activities.AppointmentGetEarliestDates$g     // Catch:{ Exception -> 0x0035 }
            r0.<init>(r8)     // Catch:{ Exception -> 0x0035 }
            r9.A(r0)     // Catch:{ Exception -> 0x0035 }
            return
        L_0x0086:
            r9.toString()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.AppointmentGetEarliestDates.Z1(java.lang.String):void");
    }

    public final String a2() {
        String str = this.f9308j0;
        if (str != null) {
            return str;
        }
        n.o("paymentMode");
        return null;
    }

    public final String b2() {
        String str = this.f9306h0;
        if (str != null) {
            return str;
        }
        n.o("visaCategoryCode");
        return null;
    }

    public final String c2() {
        String str = this.f9307i0;
        if (str != null) {
            return str;
        }
        n.o("visaSubCategoryCode");
        return null;
    }

    public final void d2() {
        this.f9320v0 = getIntent().getExtras();
        f9303y0 = new VasCartDetails();
        n2("");
        v2("");
        w2("");
        p2("");
        this.f9311m0 = new ArrayList();
        this.f9310l0 = new ArrayList();
        this.f9312n0 = new ArrayList();
        this.f9313o0 = new ArrayList();
        this.f9314p0 = new ArrayList();
        this.f9315q0 = new ArrayList();
        this.f9316r0 = new ArrayList();
        this.f9317s0 = new ArrayList();
        Utility.Companion companion = Utility.f9497a;
        companion.y(c1());
        I1();
        Y1().f20560c.setOnClickListener(new l0(this));
        Y1().f20563f.setOnClickListener(new m0(this));
        Y1().f20564g.setOnClickListener(new n0(this));
        Y1().f20567j.setOnItemSelectedListener(new h(this));
        Y1().f20569l.setOnItemSelectedListener(new i(this));
        Y1().f20570m.setOnItemSelectedListener(new j(this));
        if (n.a(companion.k(c1()), "DZA")) {
            Y1().f20565h.setVisibility(0);
            Y1().f20568k.setOnItemSelectedListener(new k(this));
            AppCompatTextView appCompatTextView = Y1().f20571n;
            n.d(appCompatTextView, "tvPrepayment");
            String string = getString(R.string.prepaymentInstructions);
            n.d(string, "getString(...)");
            defpackage.c.d(appCompatTextView, string, 3, true, (l) null, 8, (Object) null);
        }
        Y1().f20559b.setOnClickListener(new o0(this));
    }

    public final void j2() {
        onBackPressed();
    }

    public final void n2(String str) {
        n.e(str, "<set-?>");
        this.f9305g0 = str;
    }

    public final void o2(List list) {
        n.e(list, "<set-?>");
        this.f9321w0 = list;
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) Y1().b());
        d2();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Utility.Companion companion = Utility.f9497a;
        companion.c();
        companion.d();
    }

    public final void p2(String str) {
        n.e(str, "<set-?>");
        this.f9308j0 = str;
    }

    public final void q2(VisaSubCategoryResponseData visaSubCategoryResponseData) {
        this.f9318t0 = visaSubCategoryResponseData;
    }

    public final void r2(ArrayList arrayList) {
        b bVar = new b(this, R.layout.simple_spinner_main_item, arrayList);
        bVar.setDropDownViewResource(R.layout.simple_spinner_item);
        Y1().f20567j.setAdapter((SpinnerAdapter) bVar);
    }

    public final void s2(ArrayList arrayList) {
        b bVar = new b(this, R.layout.simple_spinner_main_item, arrayList);
        bVar.setDropDownViewResource(R.layout.simple_spinner_item);
        Y1().f20568k.setAdapter((SpinnerAdapter) bVar);
    }

    public final void t2(ArrayList arrayList) {
        b bVar = new b(this, R.layout.simple_spinner_main_item, arrayList);
        bVar.setDropDownViewResource(R.layout.simple_spinner_item);
        Y1().f20569l.setAdapter((SpinnerAdapter) bVar);
    }

    public final void u2(ArrayList arrayList) {
        b bVar = new b(this, R.layout.simple_spinner_main_item, arrayList);
        bVar.setDropDownViewResource(R.layout.simple_spinner_item);
        Y1().f20570m.setAdapter((SpinnerAdapter) bVar);
    }

    public final void v2(String str) {
        n.e(str, "<set-?>");
        this.f9306h0 = str;
    }

    public final void w2(String str) {
        n.e(str, "<set-?>");
        this.f9307i0 = str;
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class h implements AdapterView.OnItemSelectedListener {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ AppointmentGetEarliestDates f9327b;

        h(AppointmentGetEarliestDates appointmentGetEarliestDates) {
            this.f9327b = appointmentGetEarliestDates;
        }

        public void onItemSelected(AdapterView adapterView, View view, int i10, long j10) {
            AppointmentGetEarliestDates appointmentGetEarliestDates = this.f9327b;
            ArrayList O1 = appointmentGetEarliestDates.O1();
            n.b(O1);
            appointmentGetEarliestDates.n2(O1.get(this.f9327b.Y1().f20567j.getSelectedItemPosition()).toString());
            this.f9327b.v2("");
            this.f9327b.w2("");
            this.f9327b.p2("");
            a aVar = AppointmentGetEarliestDates.f9302x0;
            aVar.a().setFromCountryName(String.valueOf(((CenterCodeResponseData) this.f9327b.X1().get(i10)).getCountryName()));
            aVar.a().setFromCountryCode(String.valueOf(((CenterCodeResponseData) this.f9327b.X1().get(i10)).getCountryCode()));
            aVar.a().setToCountryName(String.valueOf(((CenterCodeResponseData) this.f9327b.X1().get(i10)).getMissionName()));
            aVar.a().setToCountryCode(String.valueOf(((CenterCodeResponseData) this.f9327b.X1().get(i10)).getMissionCode()));
            aVar.a().setVisaApplicationCenter(String.valueOf(((CenterCodeResponseData) this.f9327b.X1().get(i10)).getCenterName()));
            aVar.a().setVisaApplicationCenterCode(String.valueOf(((CenterCodeResponseData) this.f9327b.X1().get(i10)).getIsoCode()));
            g.a aVar2 = com.vfs.italyglobal.utilities.g.f9512a;
            Activity c12 = this.f9327b.c1();
            String string = this.f9327b.getString(R.string.appmnt_center_code);
            n.d(string, "getString(...)");
            aVar2.g(c12, string, this.f9327b.W1());
            AppointmentGetEarliestDates appointmentGetEarliestDates2 = this.f9327b;
            appointmentGetEarliestDates2.J1(appointmentGetEarliestDates2.W1());
        }

        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class i implements AdapterView.OnItemSelectedListener {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ AppointmentGetEarliestDates f9328b;

        i(AppointmentGetEarliestDates appointmentGetEarliestDates) {
            this.f9328b = appointmentGetEarliestDates;
        }

        public void onItemSelected(AdapterView adapterView, View view, int i10, long j10) {
            AppointmentGetEarliestDates appointmentGetEarliestDates = this.f9328b;
            ArrayList R1 = appointmentGetEarliestDates.R1();
            n.b(R1);
            appointmentGetEarliestDates.v2(R1.get(this.f9328b.Y1().f20569l.getSelectedItemPosition()).toString());
            this.f9328b.w2("");
            this.f9328b.p2("");
            g.a aVar = com.vfs.italyglobal.utilities.g.f9512a;
            Activity c12 = this.f9328b.c1();
            String string = this.f9328b.getString(R.string.appmnt_visa_category_code);
            n.d(string, "getString(...)");
            aVar.g(c12, string, this.f9328b.b2());
            a aVar2 = AppointmentGetEarliestDates.f9302x0;
            VasCartDetails a10 = aVar2.a();
            ArrayList S1 = this.f9328b.S1();
            n.b(S1);
            a10.setApplicationCategory(S1.get(i10).toString());
            aVar2.a().setApplicationCategoryCode(this.f9328b.b2());
            AppointmentGetEarliestDates appointmentGetEarliestDates2 = this.f9328b;
            appointmentGetEarliestDates2.K1(appointmentGetEarliestDates2.b2());
        }

        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class j implements AdapterView.OnItemSelectedListener {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ AppointmentGetEarliestDates f9329b;

        j(AppointmentGetEarliestDates appointmentGetEarliestDates) {
            this.f9329b = appointmentGetEarliestDates;
        }

        public void onItemSelected(AdapterView adapterView, View view, int i10, long j10) {
            AppointmentGetEarliestDates appointmentGetEarliestDates = this.f9329b;
            ArrayList T1 = appointmentGetEarliestDates.T1();
            n.b(T1);
            Object obj = T1.get(i10);
            n.c(obj, "null cannot be cast to non-null type com.vfs.italyglobal.pojo.VisaSubCategoryResponseData");
            appointmentGetEarliestDates.q2((VisaSubCategoryResponseData) obj);
            AppointmentGetEarliestDates appointmentGetEarliestDates2 = this.f9329b;
            ArrayList U1 = appointmentGetEarliestDates2.U1();
            n.b(U1);
            appointmentGetEarliestDates2.w2(U1.get(this.f9329b.Y1().f20570m.getSelectedItemPosition()).toString());
            g.a aVar = com.vfs.italyglobal.utilities.g.f9512a;
            Activity c12 = this.f9329b.c1();
            String string = this.f9329b.getString(R.string.appmnt_visa_sub_category_code);
            n.d(string, "getString(...)");
            aVar.g(c12, string, this.f9329b.c2());
            a aVar2 = AppointmentGetEarliestDates.f9302x0;
            VasCartDetails a10 = aVar2.a();
            ArrayList V1 = this.f9329b.V1();
            n.b(V1);
            a10.setApplicationSubCategory(V1.get(i10).toString());
            aVar2.a().setApplicationSubCategoryCode(this.f9329b.c2());
            this.f9329b.L1();
            if (n.a(Utility.f9497a.k(this.f9329b.c1()), "DZA")) {
                ArrayList Q1 = this.f9329b.Q1();
                n.b(Q1);
                Q1.clear();
                this.f9329b.p2("");
                AppointmentGetEarliestDates appointmentGetEarliestDates3 = this.f9329b;
                appointmentGetEarliestDates3.Z1(appointmentGetEarliestDates3.c2());
            }
        }

        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class k implements AdapterView.OnItemSelectedListener {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ AppointmentGetEarliestDates f9330b;

        k(AppointmentGetEarliestDates appointmentGetEarliestDates) {
            this.f9330b = appointmentGetEarliestDates;
        }

        public void onItemSelected(AdapterView adapterView, View view, int i10, long j10) {
            AppointmentGetEarliestDates appointmentGetEarliestDates = this.f9330b;
            ArrayList Q1 = appointmentGetEarliestDates.Q1();
            n.b(Q1);
            appointmentGetEarliestDates.p2(Q1.get(this.f9330b.Y1().f20568k.getSelectedItemPosition()).toString());
        }

        public void onNothingSelected(AdapterView adapterView) {
        }
    }
}
