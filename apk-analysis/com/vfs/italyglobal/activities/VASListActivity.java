package com.vfs.italyglobal.activities;

import ac.p;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import bc.a0;
import bc.n;
import bc.v;
import be.f;
import com.google.gson.e;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.activities.AddApplicantBookAppointmentActivity;
import com.vfs.italyglobal.activities.AppointmentGetEarliestDates;
import com.vfs.italyglobal.activities.AppointmentLogin;
import com.vfs.italyglobal.pojo.FeeDetail;
import com.vfs.italyglobal.pojo.SearchApplicantDetails;
import com.vfs.italyglobal.pojo.Slots;
import com.vfs.italyglobal.pojo.VASFeesResponseData;
import com.vfs.italyglobal.pojo.VASListResponseData;
import com.vfs.italyglobal.pojo.VasApplicants;
import com.vfs.italyglobal.pojo.VasCartDetails;
import com.vfs.italyglobal.pojo.VasDetails;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.g;
import fd.i0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import nb.h;
import nb.i;
import nb.o;
import ua.h5;
import ua.i5;
import ua.j5;
import ua.k5;
import ua.l5;
import ua.m5;
import ua.n5;
import ua.o5;
import ua.p5;
import va.x;
import wa.f0;
import wa.h0;
import xa.w;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class VASListActivity extends x {

    /* renamed from: f0  reason: collision with root package name */
    private final h f9463f0 = i.a(new h5(this));

    /* renamed from: g0  reason: collision with root package name */
    private final ArrayList f9464g0 = new ArrayList();

    /* renamed from: h0  reason: collision with root package name */
    private h0 f9465h0;

    /* renamed from: i0  reason: collision with root package name */
    private Slots f9466i0;

    /* renamed from: j0  reason: collision with root package name */
    private String f9467j0;

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ VASListActivity f9468a;

        a(VASListActivity vASListActivity) {
            this.f9468a = vASListActivity;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(VASListActivity vASListActivity, int i10, String str) {
            n.e(str, "<unused var>");
            AppointmentLogin.a.c(AppointmentLogin.f9338h0, vASListActivity.c1(), i10, false, 4, (Object) null);
            return nb.x.f15721a;
        }

        public void a(be.d dVar, be.x xVar) {
            Double d10;
            String str;
            String str2;
            n.e(dVar, "call");
            n.e(xVar, "response");
            try {
                Utility.Companion companion = Utility.f9497a;
                companion.c();
                VASFeesResponseData vASFeesResponseData = (VASFeesResponseData) xVar.a();
                Object obj = null;
                if (vASFeesResponseData != null) {
                    d10 = vASFeesResponseData.getTotalamount();
                } else {
                    d10 = null;
                }
                float f10 = 0.0f;
                if (d10 == null) {
                    AppointmentGetEarliestDates.f9302x0.a().setVfsServiceFees(0.0f);
                } else {
                    VasCartDetails a10 = AppointmentGetEarliestDates.f9302x0.a();
                    Double totalamount = vASFeesResponseData.getTotalamount();
                    n.b(totalamount);
                    a10.setVfsServiceFees((float) totalamount.doubleValue());
                }
                if (vASFeesResponseData != null) {
                    obj = vASFeesResponseData.getAdditionalFee();
                }
                if (obj instanceof Number) {
                    AppointmentGetEarliestDates.f9302x0.a().setVfsAdditionalFees(((Number) obj).floatValue());
                } else if (!(obj instanceof String) || ((CharSequence) obj).length() <= 0) {
                    AppointmentGetEarliestDates.f9302x0.a().setVfsAdditionalFees(0.0f);
                } else {
                    VasCartDetails a11 = AppointmentGetEarliestDates.f9302x0.a();
                    Float n10 = kc.n.n((String) obj);
                    if (n10 != null) {
                        f10 = n10.floatValue();
                    }
                    a11.setVfsAdditionalFees(f10);
                }
                if (vASFeesResponseData != null) {
                    AppointmentGetEarliestDates.a aVar = AppointmentGetEarliestDates.f9302x0;
                    VasCartDetails a12 = aVar.a();
                    if (vASFeesResponseData.getFeeDetails() != null) {
                        List<FeeDetail> feeDetails = vASFeesResponseData.getFeeDetails();
                        n.b(feeDetails);
                        str = String.valueOf(feeDetails.get(0).getCurrency());
                    } else {
                        str = "";
                    }
                    a12.setCurrency(str);
                    VasCartDetails a13 = aVar.a();
                    if (vASFeesResponseData.getFeeDetails() != null) {
                        List<FeeDetail> feeDetails2 = vASFeesResponseData.getFeeDetails();
                        n.b(feeDetails2);
                        str2 = String.valueOf(feeDetails2.get(0).getFeeName());
                    } else {
                        str2 = "";
                    }
                    a13.setFeeName(str2);
                    VASListActivity vASListActivity = this.f9468a;
                    String q10 = companion.q();
                    String k10 = companion.k(this.f9468a.c1());
                    g.a aVar2 = g.f9512a;
                    Activity c12 = this.f9468a.c1();
                    String string = this.f9468a.getString(R.string.appmnt_center_code);
                    n.d(string, "getString(...)");
                    vASListActivity.O1(q10, k10, aVar2.b(c12, string, ""));
                    return;
                }
                com.vfs.italyglobal.utilities.c.h(xVar, this.f9468a.c1(), new p5(this.f9468a));
            } catch (Exception e10) {
                e10.toString();
            }
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.f9497a.c();
            Objects.toString(th);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ VASListActivity f9469a;

        b(VASListActivity vASListActivity) {
            this.f9469a = vASListActivity;
        }

        public void a(be.d dVar, be.x xVar) {
            nb.x xVar2;
            n.e(dVar, "call");
            n.e(xVar, "response");
            Utility.f9497a.c();
            this.f9469a.P1().clear();
            i0 i0Var = (i0) xVar.a();
            if (i0Var != null) {
                VASListActivity vASListActivity = this.f9469a;
                Object i10 = new e().i(i0Var.o(), VASListResponseData.class);
                n.d(i10, "fromJson(...)");
                List<VasDetails> data = ((VASListResponseData) i10).getData();
                if (data != null) {
                    ArrayList arrayList = new ArrayList();
                    for (T next : data) {
                        if (n.a(((VasDetails) next).isAvailableOnline(), Boolean.TRUE)) {
                            arrayList.add(next);
                        }
                    }
                    vASListActivity.P1().addAll(arrayList);
                    vASListActivity.k2();
                    vASListActivity.U1(vASListActivity.P1());
                    xVar2 = nb.x.f15721a;
                } else {
                    xVar2 = null;
                }
                if (xVar2 != null) {
                    return;
                }
            }
            com.vfs.italyglobal.utilities.c.i(xVar, this.f9469a.c1(), (p) null, 2, (Object) null);
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.f9497a.c();
            Objects.toString(th);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class c implements za.g {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ VASListActivity f9470a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ int f9471b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ v f9472c;

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ AppCompatCheckBox f9473d;

        c(VASListActivity vASListActivity, int i10, v vVar, AppCompatCheckBox appCompatCheckBox) {
            this.f9470a = vASListActivity;
            this.f9471b = i10;
            this.f9472c = vVar;
            this.f9473d = appCompatCheckBox;
        }

        public void a(VasApplicants vasApplicants, int i10) {
            n.e(vasApplicants, "item");
            ((VasDetails) this.f9470a.P1().get(this.f9471b)).getVasApplicantsList().get(i10).setChecked(!((VasDetails) this.f9470a.P1().get(this.f9471b)).getVasApplicantsList().get(i10).isChecked());
            ((VasDetails) this.f9470a.P1().get(this.f9471b)).getVasApplicantsList().get(i10).isChecked();
            int size = ((VasDetails) this.f9470a.P1().get(this.f9471b)).getVasApplicantsList().size();
            for (int i11 = 0; i11 < size; i11++) {
                if (!((VasDetails) this.f9470a.P1().get(this.f9471b)).getVasApplicantsList().get(i11).isChecked()) {
                    this.f9472c.f4723b = false;
                    ((VasDetails) this.f9470a.P1().get(this.f9471b)).getVasApplicantsList().get(i11);
                }
            }
            this.f9473d.setChecked(this.f9472c.f4723b);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class d implements za.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ VASListActivity f9474a;

        d(VASListActivity vASListActivity) {
            this.f9474a = vASListActivity;
        }

        public void a(VasDetails vasDetails, int i10, AppCompatButton appCompatButton) {
            n.e(vasDetails, "item");
            n.e(appCompatButton, "view");
            Boolean isProductAdded = vasDetails.isProductAdded();
            n.b(isProductAdded);
            if (!isProductAdded.booleanValue()) {
                this.f9474a.Z1(vasDetails, i10);
            } else {
                this.f9474a.g2(vasDetails, i10, appCompatButton);
            }
        }
    }

    public VASListActivity() {
        super(false, false, 3, (bc.h) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00e2 A[Catch:{ Exception -> 0x00ba }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void N1() {
        /*
            r10 = this;
            java.lang.String r0 = ""
            boolean r1 = com.vfs.italyglobal.utilities.c.j(r10)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r2 = "getString(...)"
            if (r1 == 0) goto L_0x0103
            com.vfs.italyglobal.utilities.Utility$Companion r1 = com.vfs.italyglobal.utilities.Utility.f9497a     // Catch:{ Exception -> 0x00ba }
            android.app.Activity r3 = r10.c1()     // Catch:{ Exception -> 0x00ba }
            r1.y(r3)     // Catch:{ Exception -> 0x00ba }
            com.google.gson.j r3 = new com.google.gson.j     // Catch:{ Exception -> 0x00ba }
            r3.<init>()     // Catch:{ Exception -> 0x00ba }
            com.vfs.italyglobal.utilities.g$a r4 = com.vfs.italyglobal.utilities.g.f9512a     // Catch:{ Exception -> 0x00ba }
            android.app.Activity r5 = r10.c1()     // Catch:{ Exception -> 0x00ba }
            r6 = 2131886165(0x7f120055, float:1.9406901E38)
            java.lang.String r6 = r10.getString(r6)     // Catch:{ Exception -> 0x00ba }
            bc.n.d(r6, r2)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r5 = r4.b(r5, r6, r0)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r6 = "missionCode"
            java.lang.String r7 = r1.q()     // Catch:{ Exception -> 0x00ba }
            r3.r(r6, r7)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r6 = "countryCode"
            android.app.Activity r7 = r10.c1()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r7 = r1.k(r7)     // Catch:{ Exception -> 0x00ba }
            r3.r(r6, r7)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r6 = "centerCode"
            android.app.Activity r7 = r10.c1()     // Catch:{ Exception -> 0x00ba }
            r8 = 2131886158(0x7f12004e, float:1.9406887E38)
            java.lang.String r8 = r10.getString(r8)     // Catch:{ Exception -> 0x00ba }
            bc.n.d(r8, r2)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r7 = r4.b(r7, r8, r0)     // Catch:{ Exception -> 0x00ba }
            r3.r(r6, r7)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r6 = "loginUser"
            android.app.Activity r7 = r10.c1()     // Catch:{ Exception -> 0x00ba }
            r8 = 2131886169(0x7f120059, float:1.940691E38)
            java.lang.String r8 = r10.getString(r8)     // Catch:{ Exception -> 0x00ba }
            bc.n.d(r8, r2)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r7 = r4.b(r7, r8, r0)     // Catch:{ Exception -> 0x00ba }
            r3.r(r6, r7)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r6 = "urn"
            android.app.Activity r7 = r10.c1()     // Catch:{ Exception -> 0x00ba }
            r8 = 2131886183(0x7f120067, float:1.9406938E38)
            java.lang.String r9 = r10.getString(r8)     // Catch:{ Exception -> 0x00ba }
            bc.n.d(r9, r2)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r7 = r4.b(r7, r9, r0)     // Catch:{ Exception -> 0x00ba }
            r3.r(r6, r7)     // Catch:{ Exception -> 0x00ba }
            android.app.Activity r6 = r10.c1()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r7 = r10.getString(r8)     // Catch:{ Exception -> 0x00ba }
            bc.n.d(r7, r2)     // Catch:{ Exception -> 0x00ba }
            r4.b(r6, r7, r0)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r2 = r1.l()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r1 = r1.e(r10, r2)     // Catch:{ Exception -> 0x00ba }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ Exception -> 0x00ba }
            r2.<init>()     // Catch:{ Exception -> 0x00ba }
            java.lang.String r4 = "Authorize"
            r2.put(r4, r5)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r4 = "referer"
            com.vfs.italyglobal.AppDelegate r5 = r10.d1()     // Catch:{ Exception -> 0x00ba }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x00ba }
            if (r5 == 0) goto L_0x00bc
            java.lang.String r5 = r5.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x00ba }
            if (r5 != 0) goto L_0x00bd
            goto L_0x00bc
        L_0x00ba:
            r0 = move-exception
            goto L_0x0116
        L_0x00bc:
            r5 = r0
        L_0x00bd:
            r2.put(r4, r5)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r4 = "origin"
            com.vfs.italyglobal.AppDelegate r5 = r10.d1()     // Catch:{ Exception -> 0x00ba }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x00ba }
            if (r5 == 0) goto L_0x00d2
            java.lang.String r5 = r5.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x00ba }
            if (r5 != 0) goto L_0x00d3
        L_0x00d2:
            r5 = r0
        L_0x00d3:
            r2.put(r4, r5)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r4 = "cfmlift"
            com.vfs.italyglobal.AppDelegate r5 = r10.d1()     // Catch:{ Exception -> 0x00ba }
            com.vfs.italyglobal.pojo.AppConfigModel r5 = r5.e()     // Catch:{ Exception -> 0x00ba }
            if (r5 == 0) goto L_0x00ea
            java.lang.String r5 = r5.getAppointmentCfmLift()     // Catch:{ Exception -> 0x00ba }
            if (r5 != 0) goto L_0x00e9
            goto L_0x00ea
        L_0x00e9:
            r0 = r5
        L_0x00ea:
            r2.put(r4, r0)     // Catch:{ Exception -> 0x00ba }
            java.lang.String r0 = "ClientSource"
            r2.put(r0, r1)     // Catch:{ Exception -> 0x00ba }
            ab.b r0 = r10.f1()     // Catch:{ Exception -> 0x00ba }
            be.d r0 = r0.l(r2, r3)     // Catch:{ Exception -> 0x00ba }
            com.vfs.italyglobal.activities.VASListActivity$a r1 = new com.vfs.italyglobal.activities.VASListActivity$a     // Catch:{ Exception -> 0x00ba }
            r1.<init>(r10)     // Catch:{ Exception -> 0x00ba }
            r0.A(r1)     // Catch:{ Exception -> 0x00ba }
            return
        L_0x0103:
            com.vfs.italyglobal.utilities.Utility$Companion r0 = com.vfs.italyglobal.utilities.Utility.f9497a     // Catch:{ Exception -> 0x00ba }
            r0.c()     // Catch:{ Exception -> 0x00ba }
            r1 = 2131886591(0x7f1201ff, float:1.9407765E38)
            java.lang.String r1 = r10.getString(r1)     // Catch:{ Exception -> 0x00ba }
            bc.n.d(r1, r2)     // Catch:{ Exception -> 0x00ba }
            r0.w(r10, r1)     // Catch:{ Exception -> 0x00ba }
            return
        L_0x0116:
            r0.toString()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.VASListActivity.N1():void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005c A[Catch:{ Exception -> 0x0033 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void O1(java.lang.String r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            java.lang.String r0 = "toUpperCase(...)"
            boolean r1 = com.vfs.italyglobal.utilities.c.j(r7)     // Catch:{ Exception -> 0x0033 }
            if (r1 == 0) goto L_0x00b3
            com.vfs.italyglobal.utilities.Utility$Companion r1 = com.vfs.italyglobal.utilities.Utility.f9497a     // Catch:{ Exception -> 0x0033 }
            android.app.Activity r2 = r7.c1()     // Catch:{ Exception -> 0x0033 }
            r1.y(r2)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r2 = r1.l()     // Catch:{ Exception -> 0x0033 }
            java.lang.String r1 = r1.e(r7, r2)     // Catch:{ Exception -> 0x0033 }
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ Exception -> 0x0033 }
            r2.<init>()     // Catch:{ Exception -> 0x0033 }
            java.lang.String r3 = "referer"
            com.vfs.italyglobal.AppDelegate r4 = r7.d1()     // Catch:{ Exception -> 0x0033 }
            com.vfs.italyglobal.pojo.AppConfigModel r4 = r4.e()     // Catch:{ Exception -> 0x0033 }
            java.lang.String r5 = ""
            if (r4 == 0) goto L_0x0036
            java.lang.String r4 = r4.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0033 }
            if (r4 != 0) goto L_0x0037
            goto L_0x0036
        L_0x0033:
            r8 = move-exception
            goto L_0x00c8
        L_0x0036:
            r4 = r5
        L_0x0037:
            r2.put(r3, r4)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r3 = "origin"
            com.vfs.italyglobal.AppDelegate r4 = r7.d1()     // Catch:{ Exception -> 0x0033 }
            com.vfs.italyglobal.pojo.AppConfigModel r4 = r4.e()     // Catch:{ Exception -> 0x0033 }
            if (r4 == 0) goto L_0x004c
            java.lang.String r4 = r4.getAppointmentUrlOrigin()     // Catch:{ Exception -> 0x0033 }
            if (r4 != 0) goto L_0x004d
        L_0x004c:
            r4 = r5
        L_0x004d:
            r2.put(r3, r4)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r3 = "cfmlift"
            com.vfs.italyglobal.AppDelegate r4 = r7.d1()     // Catch:{ Exception -> 0x0033 }
            com.vfs.italyglobal.pojo.AppConfigModel r4 = r4.e()     // Catch:{ Exception -> 0x0033 }
            if (r4 == 0) goto L_0x0064
            java.lang.String r4 = r4.getAppointmentCfmLift()     // Catch:{ Exception -> 0x0033 }
            if (r4 != 0) goto L_0x0063
            goto L_0x0064
        L_0x0063:
            r5 = r4
        L_0x0064:
            r2.put(r3, r5)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r3 = "ClientSource"
            r2.put(r3, r1)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r1 = "route"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0033 }
            r3.<init>()     // Catch:{ Exception -> 0x0033 }
            java.util.Locale r4 = java.util.Locale.ROOT     // Catch:{ Exception -> 0x0033 }
            java.lang.String r5 = r9.toUpperCase(r4)     // Catch:{ Exception -> 0x0033 }
            bc.n.d(r5, r0)     // Catch:{ Exception -> 0x0033 }
            r3.append(r5)     // Catch:{ Exception -> 0x0033 }
            r5 = 47
            r3.append(r5)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r6 = r10.toUpperCase(r4)     // Catch:{ Exception -> 0x0033 }
            bc.n.d(r6, r0)     // Catch:{ Exception -> 0x0033 }
            r3.append(r6)     // Catch:{ Exception -> 0x0033 }
            r3.append(r5)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r4 = r8.toUpperCase(r4)     // Catch:{ Exception -> 0x0033 }
            bc.n.d(r4, r0)     // Catch:{ Exception -> 0x0033 }
            r3.append(r4)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r0 = r3.toString()     // Catch:{ Exception -> 0x0033 }
            r2.put(r1, r0)     // Catch:{ Exception -> 0x0033 }
            ab.b r0 = r7.f1()     // Catch:{ Exception -> 0x0033 }
            be.d r8 = r0.B(r2, r8, r9, r10)     // Catch:{ Exception -> 0x0033 }
            com.vfs.italyglobal.activities.VASListActivity$b r9 = new com.vfs.italyglobal.activities.VASListActivity$b     // Catch:{ Exception -> 0x0033 }
            r9.<init>(r7)     // Catch:{ Exception -> 0x0033 }
            r8.A(r9)     // Catch:{ Exception -> 0x0033 }
            return
        L_0x00b3:
            com.vfs.italyglobal.utilities.Utility$Companion r8 = com.vfs.italyglobal.utilities.Utility.f9497a     // Catch:{ Exception -> 0x0033 }
            r8.c()     // Catch:{ Exception -> 0x0033 }
            r9 = 2131886591(0x7f1201ff, float:1.9407765E38)
            java.lang.String r9 = r7.getString(r9)     // Catch:{ Exception -> 0x0033 }
            java.lang.String r10 = "getString(...)"
            bc.n.d(r9, r10)     // Catch:{ Exception -> 0x0033 }
            r8.w(r7, r9)     // Catch:{ Exception -> 0x0033 }
            return
        L_0x00c8:
            r8.toString()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.VASListActivity.O1(java.lang.String, java.lang.String, java.lang.String):void");
    }

    private final w Q1() {
        return (w) this.f9463f0.getValue();
    }

    private final float R1() {
        try {
            AppointmentGetEarliestDates.a aVar = AppointmentGetEarliestDates.f9302x0;
            float sumOfAllVas = aVar.a().getSumOfAllVas();
            float vfsServiceFees = aVar.a().getVfsServiceFees();
            a0 a0Var = a0.f4702a;
            String format = String.format(Locale.getDefault(), "%.2f", Arrays.copyOf(new Object[]{Float.valueOf(aVar.a().getVfsAdditionalFees() + sumOfAllVas + vfsServiceFees)}, 1));
            n.d(format, "format(...)");
            return Float.parseFloat(format);
        } catch (Exception e10) {
            e10.toString();
            return 0.0f;
        }
    }

    private final boolean S1() {
        boolean z10;
        Exception e10;
        try {
            int size = this.f9464g0.size();
            int i10 = 0;
            z10 = false;
            while (i10 < size) {
                try {
                    int size2 = ((VasDetails) this.f9464g0.get(i10)).getVasApplicantsList().size();
                    for (int i11 = 0; i11 < size2; i11++) {
                        if (((VasDetails) this.f9464g0.get(i10)).getVasApplicantsList().get(i11).isChecked()) {
                            z10 = true;
                        }
                    }
                    i10++;
                } catch (Exception e11) {
                    e10 = e11;
                    e10.toString();
                    return z10;
                }
            }
            return z10;
        } catch (Exception e12) {
            z10 = false;
            e10 = e12;
            e10.toString();
            return z10;
        }
    }

    /* access modifiers changed from: private */
    public static final w T1(VASListActivity vASListActivity) {
        return w.c(vASListActivity.getLayoutInflater());
    }

    /* access modifiers changed from: private */
    public final void U1(ArrayList arrayList) {
        try {
            int size = arrayList.size();
            for (int i10 = 0; i10 < size; i10++) {
                ArrayList arrayList2 = new ArrayList();
                int size2 = AddApplicantBookAppointmentActivity.f9247v0.a().size();
                for (int i11 = 0; i11 < size2; i11++) {
                    VasApplicants vasApplicants = new VasApplicants();
                    AddApplicantBookAppointmentActivity.a aVar = AddApplicantBookAppointmentActivity.f9247v0;
                    vasApplicants.setApplicantARN(String.valueOf(((SearchApplicantDetails) aVar.a().get(i11)).getArn()));
                    vasApplicants.setFirstName(String.valueOf(((SearchApplicantDetails) aVar.a().get(i11)).getFirstName()));
                    vasApplicants.setLastName(String.valueOf(((SearchApplicantDetails) aVar.a().get(i11)).getLastName()));
                    vasApplicants.setVasID(String.valueOf(((VasDetails) arrayList.get(i10)).getId()));
                    vasApplicants.setVasCode(String.valueOf(((VasDetails) arrayList.get(i10)).getCode()));
                    vasApplicants.setUnitPrice(String.valueOf(((VasDetails) arrayList.get(i10)).getAmount()));
                    vasApplicants.setCurrency(String.valueOf(((VasDetails) arrayList.get(i10)).getCurrency()));
                    vasApplicants.setChecked(false);
                    arrayList2.add(vasApplicants);
                }
                ((VasDetails) this.f9464g0.get(i10)).getVasApplicantsList().addAll(arrayList2);
            }
        } catch (Exception e10) {
            e10.toString();
        }
    }

    private final void V1(boolean z10) {
        AppointmentGetEarliestDates.a aVar = AppointmentGetEarliestDates.f9302x0;
        aVar.a().setSumOfAllVas(l2());
        aVar.a().setFinalAmount(R1());
        Intent intent = new Intent(this, ReviewActivity.class);
        intent.putExtra(getString(R.string.bundle_vasList), this.f9464g0);
        intent.putExtra("isVasAdded", z10);
        overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public static final void W1(VASListActivity vASListActivity, View view) {
        vASListActivity.Y1();
    }

    /* access modifiers changed from: private */
    public static final void X1(VASListActivity vASListActivity, View view) {
        vASListActivity.onBackPressed();
    }

    private final void Y1() {
        try {
            if (!com.vfs.italyglobal.utilities.c.j(this)) {
                Utility.Companion companion = Utility.f9497a;
                companion.c();
                String string = getString(R.string.network_error);
                n.d(string, "getString(...)");
                companion.w(this, string);
            } else if (S1()) {
                V1(true);
            } else {
                d2();
            }
        } catch (Exception e10) {
            e10.toString();
        }
    }

    /* access modifiers changed from: private */
    public static final void a2(VASListActivity vASListActivity, int i10, f0 f0Var, CompoundButton compoundButton, boolean z10) {
        n.e(compoundButton, "<unused var>");
        int size = ((VasDetails) vASListActivity.f9464g0.get(i10)).getVasApplicantsList().size();
        for (int i11 = 0; i11 < size; i11++) {
            ((VasDetails) vASListActivity.f9464g0.get(i10)).getVasApplicantsList().get(i11).setChecked(z10);
            f0Var.j();
        }
    }

    /* access modifiers changed from: private */
    public static final void b2(VASListActivity vASListActivity, int i10, o oVar, View view) {
        ((VasDetails) vASListActivity.f9464g0.get(i10)).setProductAdded(Boolean.FALSE);
        h0 h0Var = vASListActivity.f9465h0;
        if (h0Var == null) {
            n.o("vasAdapter");
            h0Var = null;
        }
        h0Var.j();
        ((AlertDialog) oVar.c()).dismiss();
    }

    /* access modifiers changed from: private */
    public static final void c2(VASListActivity vASListActivity, int i10, VasDetails vasDetails, o oVar, View view) {
        int size = ((VasDetails) vASListActivity.f9464g0.get(i10)).getVasApplicantsList().size();
        int i11 = 0;
        for (int i12 = 0; i12 < size; i12++) {
            if (((VasDetails) vASListActivity.f9464g0.get(i10)).getVasApplicantsList().get(i12).isChecked()) {
                i11++;
                ((VasDetails) vASListActivity.f9464g0.get(i10)).setSumOfEachVas(((float) i11) * Float.parseFloat(String.valueOf(vasDetails.getAmount())));
            }
        }
        h0 h0Var = null;
        if (i11 > 0) {
            Toast.makeText(vASListActivity.c1(), vASListActivity.getString(R.string.service_added), 1).show();
            ((VasDetails) vASListActivity.f9464g0.get(i10)).setProductAdded(Boolean.TRUE);
            ((VasDetails) vASListActivity.f9464g0.get(i10)).setUnitCount(i11);
            h0 h0Var2 = vASListActivity.f9465h0;
            if (h0Var2 == null) {
                n.o("vasAdapter");
            } else {
                h0Var = h0Var2;
            }
            h0Var.j();
            ((AlertDialog) oVar.c()).dismiss();
            return;
        }
        Utility.Companion companion = Utility.f9497a;
        String string = vASListActivity.getString(R.string.kindly_select_single_or_multiple_applicant_s_to_add_service_in_cart);
        n.d(string, "getString(...)");
        companion.w(vASListActivity, string);
        ((VasDetails) vASListActivity.f9464g0.get(i10)).setUnitCount(0);
        ((VasDetails) vASListActivity.f9464g0.get(i10)).setProductAdded(Boolean.FALSE);
        h0 h0Var3 = vASListActivity.f9465h0;
        if (h0Var3 == null) {
            n.o("vasAdapter");
        } else {
            h0Var = h0Var3;
        }
        h0Var.j();
    }

    private final void d2() {
        try {
            o d10 = com.vfs.italyglobal.utilities.c.d(this, R.layout.custom_alert_vas_skip);
            AlertDialog alertDialog = (AlertDialog) d10.c();
            ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btnContinue)).setOnClickListener(new i5(alertDialog, this));
            ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btnCancel)).setOnClickListener(new j5(alertDialog));
            alertDialog.show();
        } catch (Exception e10) {
            e10.toString();
        }
    }

    /* access modifiers changed from: private */
    public static final void e2(AlertDialog alertDialog, VASListActivity vASListActivity, View view) {
        alertDialog.dismiss();
        vASListActivity.V1(false);
    }

    /* access modifiers changed from: private */
    public static final void f2(AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
    }

    /* access modifiers changed from: private */
    public final void g2(VasDetails vasDetails, int i10, AppCompatButton appCompatButton) {
        try {
            o d10 = com.vfs.italyglobal.utilities.c.d(this, R.layout.custom_alert_vas_remove);
            AlertDialog alertDialog = (AlertDialog) d10.c();
            ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btnRemove)).setOnClickListener(new k5(this, i10, alertDialog));
            ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btnCancel)).setOnClickListener(new l5(alertDialog));
            alertDialog.show();
        } catch (Exception e10) {
            Objects.toString(vasDetails);
            Objects.toString(appCompatButton);
            e10.toString();
        }
    }

    /* access modifiers changed from: private */
    public static final void h2(VASListActivity vASListActivity, int i10, AlertDialog alertDialog, View view) {
        int size = ((VasDetails) vASListActivity.f9464g0.get(i10)).getVasApplicantsList().size();
        for (int i11 = 0; i11 < size; i11++) {
            ((VasDetails) vASListActivity.f9464g0.get(i10)).getVasApplicantsList().get(i11).setChecked(false);
        }
        ((VasDetails) vASListActivity.f9464g0.get(i10)).setProductAdded(Boolean.FALSE);
        ((VasDetails) vASListActivity.f9464g0.get(i10)).setSumOfEachVas(0.0f);
        ((VasDetails) vASListActivity.f9464g0.get(i10)).setUnitCount(0);
        h0 h0Var = vASListActivity.f9465h0;
        if (h0Var == null) {
            n.o("vasAdapter");
            h0Var = null;
        }
        h0Var.j();
        alertDialog.dismiss();
    }

    /* access modifiers changed from: private */
    public static final void i2(AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
    }

    private final void j2() {
        AppointmentGetEarliestDates.a aVar = AppointmentGetEarliestDates.f9302x0;
        VasCartDetails a10 = aVar.a();
        String str = this.f9467j0;
        Slots slots = null;
        if (str == null) {
            n.o("appointmentGCSelectedSlotDate");
            str = null;
        }
        a10.setAppointmentDate(str);
        VasCartDetails a11 = aVar.a();
        Slots slots2 = this.f9466i0;
        if (slots2 == null) {
            n.o("appointmentGCTimeSlots");
            slots2 = null;
        }
        a11.setAllocationId(String.valueOf(slots2.getAllocationId()));
        VasCartDetails a12 = aVar.a();
        Slots slots3 = this.f9466i0;
        if (slots3 == null) {
            n.o("appointmentGCTimeSlots");
            slots3 = null;
        }
        a12.setAppointmentTime(String.valueOf(slots3.getSlot()));
        VasCartDetails a13 = aVar.a();
        Slots slots4 = this.f9466i0;
        if (slots4 == null) {
            n.o("appointmentGCTimeSlots");
        } else {
            slots = slots4;
        }
        a13.setAppointmentType(String.valueOf(slots.getType()));
    }

    private final float l2() {
        float f10 = 0.0f;
        try {
            int size = this.f9464g0.size();
            for (int i10 = 0; i10 < size; i10++) {
                f10 += ((VasDetails) this.f9464g0.get(i10)).getSumOfEachVas();
            }
            return f10;
        } catch (Exception e10) {
            e10.toString();
            return 0.0f;
        }
    }

    public final ArrayList P1() {
        return this.f9464g0;
    }

    public final void Z1(VasDetails vasDetails, int i10) {
        n.e(vasDetails, "item");
        try {
            o d10 = com.vfs.italyglobal.utilities.c.d(this, R.layout.custom_add_service_dialog);
            AppCompatTextView appCompatTextView = (AppCompatTextView) ((View) d10.d()).findViewById(R.id.txtDescription);
            AppCompatCheckBox appCompatCheckBox = (AppCompatCheckBox) ((View) d10.d()).findViewById(R.id.cbApplyToAll);
            RecyclerView recyclerView = (RecyclerView) ((View) d10.d()).findViewById(R.id.rvApplicantList);
            ((AppCompatTextView) ((View) d10.d()).findViewById(R.id.txtHeader)).setText(vasDetails.getName());
            appCompatTextView.setText(vasDetails.getDescription());
            appCompatTextView.setMovementMethod(new ScrollingMovementMethod());
            ((AppCompatTextView) ((View) d10.d()).findViewById(R.id.txtAmount)).setText(getString(R.string.msg_vas_product_amount, new Object[]{vasDetails.getCurrency(), vasDetails.getAmount()}));
            v vVar = new v();
            vVar.f4723b = true;
            int size = ((VasDetails) this.f9464g0.get(i10)).getVasApplicantsList().size();
            for (int i11 = 0; i11 < size; i11++) {
                ((VasDetails) this.f9464g0.get(i10)).getVasApplicantsList().get(i11).setChecked(true);
            }
            appCompatCheckBox.setChecked(vVar.f4723b);
            f0 f0Var = new f0(((VasDetails) this.f9464g0.get(i10)).getVasApplicantsList(), new c(this, i10, vVar, appCompatCheckBox));
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.t0();
            recyclerView.setAdapter(f0Var);
            appCompatCheckBox.setOnCheckedChangeListener(new m5(this, i10, f0Var));
            ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btnCancel)).setOnClickListener(new n5(this, i10, d10));
            ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btnAddProduct)).setOnClickListener(new o5(this, i10, vasDetails, d10));
            ((AlertDialog) d10.c()).show();
        } catch (Exception e10) {
            e10.toString();
        }
    }

    public final void k2() {
        try {
            if (!this.f9464g0.isEmpty()) {
                this.f9465h0 = new h0(c1(), this.f9464g0, new d(this));
                Q1().f20706d.setLayoutManager(new LinearLayoutManager(this));
                Q1().f20706d.t0();
                RecyclerView recyclerView = Q1().f20706d;
                h0 h0Var = this.f9465h0;
                if (h0Var == null) {
                    n.o("vasAdapter");
                    h0Var = null;
                }
                recyclerView.setAdapter(h0Var);
                Q1().f20706d.setVisibility(0);
                Q1().f20707e.setVisibility(8);
                return;
            }
            Q1().f20706d.setVisibility(8);
            Q1().f20707e.setVisibility(0);
        } catch (Exception e10) {
            e10.toString();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r11) {
        /*
            r10 = this;
            java.lang.String r0 = ""
            super.onCreate(r11)
            xa.w r11 = r10.Q1()
            androidx.appcompat.widget.LinearLayoutCompat r11 = r11.b()
            r10.setContentView((android.view.View) r11)
            android.content.Intent r11 = r10.getIntent()     // Catch:{ Exception -> 0x0040 }
            android.os.Bundle r11 = r11.getExtras()     // Catch:{ Exception -> 0x0040 }
            com.vfs.italyglobal.pojo.Slots r1 = new com.vfs.italyglobal.pojo.Slots     // Catch:{ Exception -> 0x0040 }
            r8 = 63
            r9 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0040 }
            r10.f9466i0 = r1     // Catch:{ Exception -> 0x0040 }
            r10.f9467j0 = r0     // Catch:{ Exception -> 0x0040 }
            bc.n.b(r11)     // Catch:{ Exception -> 0x0040 }
            r1 = 2131886214(0x7f120086, float:1.9407E38)
            java.lang.String r1 = r10.getString(r1)     // Catch:{ Exception -> 0x0040 }
            java.lang.Object r1 = r11.get(r1)     // Catch:{ Exception -> 0x0040 }
            boolean r2 = r1 instanceof com.vfs.italyglobal.pojo.Slots     // Catch:{ Exception -> 0x0040 }
            r3 = 0
            if (r2 == 0) goto L_0x0043
            com.vfs.italyglobal.pojo.Slots r1 = (com.vfs.italyglobal.pojo.Slots) r1     // Catch:{ Exception -> 0x0040 }
            goto L_0x0044
        L_0x0040:
            r0 = move-exception
            r11 = r0
            goto L_0x008d
        L_0x0043:
            r1 = r3
        L_0x0044:
            bc.n.b(r1)     // Catch:{ Exception -> 0x0040 }
            r10.f9466i0 = r1     // Catch:{ Exception -> 0x0040 }
            r1 = 2131886216(0x7f120088, float:1.9407005E38)
            java.lang.String r1 = r10.getString(r1)     // Catch:{ Exception -> 0x0040 }
            java.lang.Object r11 = r11.get(r1)     // Catch:{ Exception -> 0x0040 }
            if (r11 != 0) goto L_0x0057
            goto L_0x0058
        L_0x0057:
            r0 = r11
        L_0x0058:
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x0040 }
            r10.f9467j0 = r0     // Catch:{ Exception -> 0x0040 }
            com.vfs.italyglobal.pojo.Slots r11 = r10.f9466i0     // Catch:{ Exception -> 0x0040 }
            if (r11 != 0) goto L_0x0066
            java.lang.String r11 = "appointmentGCTimeSlots"
            bc.n.o(r11)     // Catch:{ Exception -> 0x0040 }
            goto L_0x0067
        L_0x0066:
            r3 = r11
        L_0x0067:
            r3.getSlot()     // Catch:{ Exception -> 0x0040 }
            r10.j2()     // Catch:{ Exception -> 0x0040 }
            r10.N1()     // Catch:{ Exception -> 0x0040 }
            xa.w r11 = r10.Q1()     // Catch:{ Exception -> 0x0040 }
            androidx.appcompat.widget.AppCompatButton r11 = r11.f20704b     // Catch:{ Exception -> 0x0040 }
            ua.f5 r0 = new ua.f5     // Catch:{ Exception -> 0x0040 }
            r0.<init>(r10)     // Catch:{ Exception -> 0x0040 }
            r11.setOnClickListener(r0)     // Catch:{ Exception -> 0x0040 }
            xa.w r11 = r10.Q1()     // Catch:{ Exception -> 0x0040 }
            androidx.appcompat.widget.AppCompatImageView r11 = r11.f20705c     // Catch:{ Exception -> 0x0040 }
            ua.g5 r0 = new ua.g5     // Catch:{ Exception -> 0x0040 }
            r0.<init>(r10)     // Catch:{ Exception -> 0x0040 }
            r11.setOnClickListener(r0)     // Catch:{ Exception -> 0x0040 }
            return
        L_0x008d:
            r11.toString()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.VASListActivity.onCreate(android.os.Bundle):void");
    }
}
