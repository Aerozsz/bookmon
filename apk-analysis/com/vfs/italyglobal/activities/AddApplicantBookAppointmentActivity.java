package com.vfs.italyglobal.activities;

import ac.p;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.s;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import bc.n;
import com.google.gson.j;
import com.phoenixcapture.camerakit.R;
import com.vfs.italyglobal.activities.AppointmentLogin;
import com.vfs.italyglobal.pojo.AppConfigModel;
import com.vfs.italyglobal.pojo.ApplicantDeleteResponse;
import com.vfs.italyglobal.pojo.ApplicantListResponseData;
import com.vfs.italyglobal.pojo.ApplicantResponseList;
import com.vfs.italyglobal.pojo.ErrorMessage;
import com.vfs.italyglobal.pojo.Nationality;
import com.vfs.italyglobal.pojo.SearchApplicantDetails;
import com.vfs.italyglobal.pojo.SearchAppointmentDetails;
import com.vfs.italyglobal.utilities.KeyProvider;
import com.vfs.italyglobal.utilities.Utility;
import com.vfs.italyglobal.utilities.g;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import k3.c;
import nb.i;
import nb.o;
import ua.k;
import ua.l;
import ua.m;
import ua.t;
import va.x;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public final class AddApplicantBookAppointmentActivity extends x implements d.c {

    /* renamed from: v0  reason: collision with root package name */
    public static final a f9247v0 = new a((bc.h) null);

    /* renamed from: w0  reason: collision with root package name */
    public static ArrayList f9248w0;

    /* renamed from: f0  reason: collision with root package name */
    private final nb.h f9249f0 = i.a(new ua.a(this));

    /* renamed from: g0  reason: collision with root package name */
    private AlertDialog f9250g0;
    /* access modifiers changed from: private */

    /* renamed from: h0  reason: collision with root package name */
    public View f9251h0;
    /* access modifiers changed from: private */

    /* renamed from: i0  reason: collision with root package name */
    public boolean f9252i0;
    /* access modifiers changed from: private */

    /* renamed from: j0  reason: collision with root package name */
    public boolean f9253j0;
    /* access modifiers changed from: private */

    /* renamed from: k0  reason: collision with root package name */
    public boolean f9254k0;

    /* renamed from: l0  reason: collision with root package name */
    private boolean f9255l0;

    /* renamed from: m0  reason: collision with root package name */
    private byte[] f9256m0;

    /* renamed from: n0  reason: collision with root package name */
    private SearchApplicantDetails f9257n0 = new SearchApplicantDetails();

    /* renamed from: o0  reason: collision with root package name */
    public wa.e f9258o0;

    /* renamed from: p0  reason: collision with root package name */
    private int f9259p0;

    /* renamed from: q0  reason: collision with root package name */
    private Calendar f9260q0;
    /* access modifiers changed from: private */

    /* renamed from: r0  reason: collision with root package name */
    public ArrayList f9261r0;
    /* access modifiers changed from: private */

    /* renamed from: s0  reason: collision with root package name */
    public ArrayList f9262s0;

    /* renamed from: t0  reason: collision with root package name */
    private boolean f9263t0;

    /* renamed from: u0  reason: collision with root package name */
    private SearchAppointmentDetails f9264u0 = new SearchAppointmentDetails();

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class a {
        public /* synthetic */ a(bc.h hVar) {
            this();
        }

        public final ArrayList a() {
            ArrayList arrayList = AddApplicantBookAppointmentActivity.f9248w0;
            if (arrayList != null) {
                return arrayList;
            }
            n.o("arrApplicantList");
            return null;
        }

        public final void b(ArrayList arrayList) {
            n.e(arrayList, "<set-?>");
            AddApplicantBookAppointmentActivity.f9248w0 = arrayList;
        }

        private a() {
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class b implements be.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ Integer f9265a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ AddApplicantBookAppointmentActivity f9266b;

        b(Integer num, AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity) {
            this.f9265a = num;
            this.f9266b = addApplicantBookAppointmentActivity;
        }

        public void a(be.d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            ApplicantDeleteResponse applicantDeleteResponse = (ApplicantDeleteResponse) xVar.a();
            if (applicantDeleteResponse == null) {
                com.vfs.italyglobal.utilities.c.i(xVar, this.f9266b.c1(), (p) null, 2, (Object) null);
            } else if (applicantDeleteResponse.getError() != null) {
                companion.c();
                Activity c12 = this.f9266b.c1();
                ErrorMessage error = applicantDeleteResponse.getError();
                n.b(error);
                companion.w(c12, String.valueOf(error.getDescription()));
            } else if (kc.n.z(applicantDeleteResponse.getSuccess(), "true", false, 2, (Object) null)) {
                a aVar = AddApplicantBookAppointmentActivity.f9247v0;
                ArrayList a10 = aVar.a();
                Integer num = this.f9265a;
                n.b(num);
                a10.remove(num.intValue());
                int size = aVar.a().size();
                for (int intValue = this.f9265a.intValue(); intValue < size; intValue++) {
                    a aVar2 = AddApplicantBookAppointmentActivity.f9247v0;
                    ((SearchApplicantDetails) aVar2.a().get(intValue)).setPos(Integer.valueOf(intValue));
                    this.f9266b.P2(aVar2.a());
                }
                this.f9266b.h2().j();
                this.f9266b.f2();
                Utility.Companion companion2 = Utility.f9497a;
                Activity c13 = this.f9266b.c1();
                String string = this.f9266b.getString(R.string.applicant_deleted_successfully);
                n.d(string, "getString(...)");
                companion2.w(c13, string);
            }
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            Activity c12 = this.f9266b.c1();
            String localizedMessage = th.getLocalizedMessage();
            if (localizedMessage == null) {
                localizedMessage = this.f9266b.getString(R.string.generic_error);
                n.d(localizedMessage, "getString(...)");
            }
            companion.w(c12, localizedMessage);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class c implements be.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AddApplicantBookAppointmentActivity f9267a;

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ int f9268b;

        /* renamed from: c  reason: collision with root package name */
        final /* synthetic */ SearchApplicantDetails f9269c;

        c(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, int i10, SearchApplicantDetails searchApplicantDetails) {
            this.f9267a = addApplicantBookAppointmentActivity;
            this.f9268b = i10;
            this.f9269c = searchApplicantDetails;
        }

        /* access modifiers changed from: private */
        public static final nb.x d(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, int i10, String str) {
            n.e(str, "<unused var>");
            AppointmentLogin.a.c(AppointmentLogin.f9338h0, addApplicantBookAppointmentActivity.c1(), i10, false, 4, (Object) null);
            return nb.x.f15721a;
        }

        public void a(be.d dVar, be.x xVar) {
            nb.x xVar2;
            n.e(dVar, "call");
            n.e(xVar, "response");
            try {
                Utility.Companion companion = Utility.f9497a;
                companion.c();
                ApplicantListResponseData applicantListResponseData = (ApplicantListResponseData) xVar.a();
                if (applicantListResponseData != null) {
                    AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity = this.f9267a;
                    int i10 = this.f9268b;
                    SearchApplicantDetails searchApplicantDetails = this.f9269c;
                    if (applicantListResponseData.getUrn() != null) {
                        if (addApplicantBookAppointmentActivity.f9252i0) {
                            Toast.makeText(addApplicantBookAppointmentActivity.c1(), addApplicantBookAppointmentActivity.getString(R.string.applicant_updated_successfully), 1).show();
                            AddApplicantBookAppointmentActivity.f9247v0.a().set(i10, searchApplicantDetails);
                            addApplicantBookAppointmentActivity.h2().j();
                        } else {
                            Toast.makeText(addApplicantBookAppointmentActivity.c1(), addApplicantBookAppointmentActivity.getString(R.string.applicant_added_successfully), 1).show();
                            g.a aVar = com.vfs.italyglobal.utilities.g.f9512a;
                            Activity c12 = addApplicantBookAppointmentActivity.c1();
                            String string = addApplicantBookAppointmentActivity.getString(R.string.appointment_URN);
                            n.d(string, "getString(...)");
                            aVar.g(c12, string, String.valueOf(applicantListResponseData.getUrn()));
                            List<ApplicantResponseList> applicantList = applicantListResponseData.getApplicantList();
                            n.b(applicantList);
                            List<ApplicantResponseList> applicantList2 = applicantListResponseData.getApplicantList();
                            n.b(applicantList2);
                            searchApplicantDetails.setArn(String.valueOf(applicantList2.get(applicantList.size() - 1).getArn()));
                            a aVar2 = AddApplicantBookAppointmentActivity.f9247v0;
                            searchApplicantDetails.setPos(Integer.valueOf(aVar2.a().size()));
                            searchApplicantDetails.setUrn(String.valueOf(applicantListResponseData.getUrn()));
                            aVar2.a().add(searchApplicantDetails);
                        }
                        addApplicantBookAppointmentActivity.P2(AddApplicantBookAppointmentActivity.f9247v0.a());
                        AlertDialog i22 = addApplicantBookAppointmentActivity.i2();
                        xVar2 = null;
                        if (i22 != null) {
                            i22.dismiss();
                            addApplicantBookAppointmentActivity.f9251h0 = null;
                            xVar2 = nb.x.f15721a;
                        }
                    } else if (applicantListResponseData.getError() != null) {
                        Activity c13 = addApplicantBookAppointmentActivity.c1();
                        ErrorMessage error = applicantListResponseData.getError();
                        n.b(error);
                        companion.w(c13, String.valueOf(error.getDescription()));
                        xVar2 = nb.x.f15721a;
                    } else {
                        Activity c14 = addApplicantBookAppointmentActivity.c1();
                        String string2 = addApplicantBookAppointmentActivity.c1().getString(R.string.generic_error);
                        n.d(string2, "getString(...)");
                        companion.w(c14, string2);
                        xVar2 = nb.x.f15721a;
                    }
                    if (xVar2 != null) {
                        return;
                    }
                }
                AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity2 = this.f9267a;
                com.vfs.italyglobal.utilities.c.h(xVar, addApplicantBookAppointmentActivity2.c1(), new t(addApplicantBookAppointmentActivity2));
            } catch (Exception e10) {
                e10.toString();
            }
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Utility.Companion companion = Utility.f9497a;
            companion.c();
            String localizedMessage = th.getLocalizedMessage();
            if (localizedMessage != null) {
                companion.w(this.f9267a.c1(), localizedMessage);
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class d implements be.f {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AddApplicantBookAppointmentActivity f9270a;

        /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
        public static final class a implements Comparator {
            public final int compare(Object obj, Object obj2) {
                return qb.a.a(((Nationality) obj).getNationalityName(), ((Nationality) obj2).getNationalityName());
            }
        }

        d(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity) {
            this.f9270a = addApplicantBookAppointmentActivity;
        }

        public void a(be.d dVar, be.x xVar) {
            n.e(dVar, "call");
            n.e(xVar, "response");
            Utility.f9497a.c();
            try {
                List list = (List) xVar.a();
                ArrayList arrayList = null;
                if (list != null) {
                    if (!list.isEmpty()) {
                        ArrayList arrayList2 = new ArrayList();
                        int size = list.size();
                        for (int i10 = 0; i10 < size; i10++) {
                            arrayList2.add(list.get(i10));
                            ArrayList U1 = this.f9270a.f9262s0;
                            if (U1 == null) {
                                n.o("nationalityListName");
                                U1 = null;
                            }
                            U1.add(String.valueOf(((Nationality) list.get(i10)).getNationalityName()));
                        }
                        ArrayList T1 = this.f9270a.f9261r0;
                        if (T1 == null) {
                            n.o("nationalityList");
                            T1 = null;
                        }
                        T1.addAll(ob.p.c0(arrayList2, new a()));
                        ArrayList U12 = this.f9270a.f9262s0;
                        if (U12 == null) {
                            n.o("nationalityListName");
                        } else {
                            arrayList = U12;
                        }
                        ob.p.v(arrayList);
                        return;
                    }
                }
                com.vfs.italyglobal.utilities.c.i(xVar, this.f9270a.c1(), (p) null, 2, (Object) null);
            } catch (Exception e10) {
                e10.toString();
            }
        }

        public void b(be.d dVar, Throwable th) {
            n.e(dVar, "call");
            n.e(th, "t");
            Objects.toString(th);
            Utility.f9497a.c();
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class e extends s {

        /* renamed from: d  reason: collision with root package name */
        final /* synthetic */ AddApplicantBookAppointmentActivity f9271d;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        e(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity) {
            super(true);
            this.f9271d = addApplicantBookAppointmentActivity;
        }

        public void d() {
            this.f9271d.finish();
            this.f9271d.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class g extends WebChromeClient {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AddApplicantBookAppointmentActivity f9274a;

        g(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity) {
            this.f9274a = addApplicantBookAppointmentActivity;
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            String str;
            if (consoleMessage != null) {
                str = consoleMessage.message();
            } else {
                str = null;
            }
            n.b(str);
            if (kc.n.K(str, "captchatoken: ", false, 2, (Object) null)) {
                String substring = str.substring(14);
                n.d(substring, "substring(...)");
                if (substring.length() == 0) {
                    Utility.Companion companion = Utility.f9497a;
                    AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity = this.f9274a;
                    String string = addApplicantBookAppointmentActivity.getString(R.string.appmnt_login_reCaptcha_required);
                    n.d(string, "getString(...)");
                    companion.w(addApplicantBookAppointmentActivity, string);
                } else {
                    AlertDialog i22 = this.f9274a.i2();
                    n.b(i22);
                    i22.dismiss();
                    g.a aVar = com.vfs.italyglobal.utilities.g.f9512a;
                    Activity c12 = this.f9274a.c1();
                    String string2 = this.f9274a.getString(R.string.appointment_URN);
                    n.d(string2, "getString(...)");
                    a aVar2 = AddApplicantBookAppointmentActivity.f9247v0;
                    aVar.g(c12, string2, String.valueOf(((SearchApplicantDetails) aVar2.a().get(0)).getUrn()));
                    if (this.f9274a.f9253j0 || this.f9274a.f9254k0) {
                        this.f9274a.r2(String.valueOf(((SearchApplicantDetails) aVar2.a().get(0)).getUrn()));
                    } else {
                        this.f9274a.q2();
                    }
                }
            }
            return super.onConsoleMessage(consoleMessage);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class h implements za.d {

        /* renamed from: a  reason: collision with root package name */
        final /* synthetic */ AddApplicantBookAppointmentActivity f9275a;

        h(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity) {
            this.f9275a = addApplicantBookAppointmentActivity;
        }

        public void a(SearchApplicantDetails searchApplicantDetails, boolean z10) {
            n.e(searchApplicantDetails, "applicantList");
            if (z10) {
                this.f9275a.H2(searchApplicantDetails.getArn(), searchApplicantDetails.getPos());
            } else {
                this.f9275a.z2(false, searchApplicantDetails, searchApplicantDetails.getPos());
            }
        }
    }

    public AddApplicantBookAppointmentActivity() {
        super(false, false, 3, (bc.h) null);
    }

    /* access modifiers changed from: private */
    public static final void A2(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, View view) {
        com.vfs.italyglobal.utilities.c.l("tech5_capture_retake", (Bundle) null, 2, (Object) null);
        addApplicantBookAppointmentActivity.s2();
    }

    /* access modifiers changed from: private */
    public static final void B2(EditText editText, EditText editText2, EditText editText3, EditText editText4, EditText editText5, Button button, TextView textView, AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, View view, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, View view2) {
        editText.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
        button.setVisibility(8);
        textView.setVisibility(8);
        addApplicantBookAppointmentActivity.f9256m0 = null;
        ((LinearLayoutCompat) view.findViewById(R.id.faceImageLL)).setVisibility(8);
        radioButton.setChecked(false);
        radioButton2.setChecked(false);
        radioButton3.setChecked(false);
    }

    /* access modifiers changed from: private */
    public static final void C2(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, EditText editText, View view, View view2) {
        n.b(editText);
        addApplicantBookAppointmentActivity.u2(editText, 1, view);
    }

    /* access modifiers changed from: private */
    public static final void D2(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, EditText editText, View view, View view2) {
        n.b(editText);
        addApplicantBookAppointmentActivity.u2(editText, 2, view);
    }

    /* access modifiers changed from: private */
    public static final void E2(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, View view) {
        AlertDialog alertDialog = addApplicantBookAppointmentActivity.f9250g0;
        n.b(alertDialog);
        alertDialog.dismiss();
        addApplicantBookAppointmentActivity.f9251h0 = null;
    }

    /* access modifiers changed from: private */
    public static final void F2(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, SearchApplicantDetails searchApplicantDetails, EditText editText, EditText editText2, EditText editText3, EditText editText4, EditText editText5, Button button, bc.x xVar, String str, String str2, bc.x xVar2, bc.x xVar3, bc.x xVar4, Integer num, View view) {
        bc.x xVar5 = xVar2;
        if (addApplicantBookAppointmentActivity.f9252i0) {
            n.b(searchApplicantDetails);
        } else {
            searchApplicantDetails = new SearchApplicantDetails();
        }
        addApplicantBookAppointmentActivity.f9257n0 = searchApplicantDetails;
        String obj = editText.getText().toString();
        String obj2 = editText2.getText().toString();
        String obj3 = editText3.getText().toString();
        String obj4 = editText4.getText().toString();
        String obj5 = editText5.getText().toString();
        n.b(button);
        if (button.getVisibility() == 0 && addApplicantBookAppointmentActivity.f9256m0 == null) {
            Utility.Companion companion = Utility.f9497a;
            Activity c12 = addApplicantBookAppointmentActivity.c1();
            String string = addApplicantBookAppointmentActivity.getString(R.string.add_applicant_image_mandatory);
            n.d(string, "getString(...)");
            companion.w(c12, string);
        } else if (obj4.length() == 0) {
            Utility.Companion companion2 = Utility.f9497a;
            Activity c13 = addApplicantBookAppointmentActivity.c1();
            String string2 = addApplicantBookAppointmentActivity.getString(R.string.add_applicant_first_name_mandatory);
            n.d(string2, "getString(...)");
            companion2.w(c13, string2);
        } else if (obj5.length() == 0) {
            Utility.Companion companion3 = Utility.f9497a;
            Activity c14 = addApplicantBookAppointmentActivity.c1();
            String string3 = addApplicantBookAppointmentActivity.getString(R.string.add_applicant_last_name_mandatory);
            n.d(string3, "getString(...)");
            companion3.w(c14, string3);
        } else if (obj2.length() == 0) {
            Utility.Companion companion4 = Utility.f9497a;
            Activity c15 = addApplicantBookAppointmentActivity.c1();
            String string4 = addApplicantBookAppointmentActivity.getString(R.string.add_applicant_dob_mandatory);
            n.d(string4, "getString(...)");
            companion4.w(c15, string4);
        } else if (obj.length() == 0) {
            Utility.Companion companion5 = Utility.f9497a;
            Activity c16 = addApplicantBookAppointmentActivity.c1();
            String string5 = addApplicantBookAppointmentActivity.getString(R.string.add_applicant_passport_no_mandatory);
            n.d(string5, "getString(...)");
            companion5.w(c16, string5);
        } else if (obj3.length() == 0) {
            Utility.Companion companion6 = Utility.f9497a;
            Activity c17 = addApplicantBookAppointmentActivity.c1();
            String string6 = addApplicantBookAppointmentActivity.getString(R.string.add_applicant_passport_expiry_mandatory);
            n.d(string6, "getString(...)");
            companion6.w(c17, string6);
        } else if (((CharSequence) xVar.f4725b).length() == 0) {
            Utility.Companion companion7 = Utility.f9497a;
            Activity c18 = addApplicantBookAppointmentActivity.c1();
            String string7 = addApplicantBookAppointmentActivity.getString(R.string.add_applicant_nationality_mandatory);
            n.d(string7, "getString(...)");
            companion7.w(c18, string7);
        } else if (str.length() == 0) {
            Utility.Companion companion8 = Utility.f9497a;
            Activity c19 = addApplicantBookAppointmentActivity.c1();
            String string8 = addApplicantBookAppointmentActivity.getString(R.string.add_applicant_contact);
            n.d(string8, "getString(...)");
            companion8.w(c19, string8);
        } else if (str2.length() == 0) {
            Utility.Companion companion9 = Utility.f9497a;
            Activity c110 = addApplicantBookAppointmentActivity.c1();
            String string9 = addApplicantBookAppointmentActivity.getString(R.string.add_applicant_email_mandatory);
            n.d(string9, "getString(...)");
            companion9.w(c110, string9);
        } else if (n.a(xVar5.f4725b, "")) {
            Utility.Companion companion10 = Utility.f9497a;
            Activity c111 = addApplicantBookAppointmentActivity.c1();
            String string10 = addApplicantBookAppointmentActivity.getString(R.string.gender_mandatory);
            n.d(string10, "getString(...)");
            companion10.w(c111, string10);
        } else {
            String str3 = (String) xVar5.f4725b;
            if (n.a(str3, "Male")) {
                addApplicantBookAppointmentActivity.f9257n0.setGender(1);
            } else if (n.a(str3, "Female")) {
                addApplicantBookAppointmentActivity.f9257n0.setGender(2);
            } else {
                addApplicantBookAppointmentActivity.f9257n0.setGender(3);
            }
            addApplicantBookAppointmentActivity.f9257n0.setPassportNumber(obj);
            addApplicantBookAppointmentActivity.f9257n0.setDateOfBirth(obj2);
            addApplicantBookAppointmentActivity.f9257n0.setPassportExpiryDate(obj3);
            addApplicantBookAppointmentActivity.f9257n0.setNationalityCode((String) xVar.f4725b);
            addApplicantBookAppointmentActivity.f9257n0.setFirstName(obj4);
            addApplicantBookAppointmentActivity.f9257n0.setLastName(obj5);
            addApplicantBookAppointmentActivity.f9257n0.setContactNumber(str);
            String str4 = str2;
            addApplicantBookAppointmentActivity.f9257n0.setEmailId(str2);
            addApplicantBookAppointmentActivity.f9257n0.setArn((String) xVar3.f4725b);
            addApplicantBookAppointmentActivity.f9257n0.setDialCode((String) xVar4.f4725b);
            Utility.f9497a.y(addApplicantBookAppointmentActivity.c1());
            SearchApplicantDetails searchApplicantDetails2 = addApplicantBookAppointmentActivity.f9257n0;
            n.b(num);
            addApplicantBookAppointmentActivity.c2(searchApplicantDetails2, num.intValue());
        }
    }

    /* access modifiers changed from: private */
    public static final void G2(View view, bc.x xVar, RadioGroup radioGroup, int i10) {
        n.e(radioGroup, "<unused var>");
        View findViewById = view.findViewById(i10);
        n.d(findViewById, "findViewById(...)");
        xVar.f4725b = ((RadioButton) findViewById).getText().toString();
    }

    /* access modifiers changed from: private */
    public static final void I2(AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
    }

    /* access modifiers changed from: private */
    public static final void J2(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, String str, Integer num, AlertDialog alertDialog, View view) {
        addApplicantBookAppointmentActivity.b2(str, num);
        Utility.f9497a.y(addApplicantBookAppointmentActivity.c1());
        alertDialog.dismiss();
    }

    private final void K2() {
        o d10 = com.vfs.italyglobal.utilities.c.d(this, R.layout.custom_alert_book_download);
        AlertDialog alertDialog = (AlertDialog) d10.c();
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_centerText)).setText(getString(R.string.lbl_applicant_back_msg));
        AppCompatButton appCompatButton = (AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog_no_cancel);
        appCompatButton.setText(getString(R.string.lbl_go_back));
        appCompatButton.setOnClickListener(new ua.n(alertDialog, this));
        AppCompatButton appCompatButton2 = (AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog_yes_cancel);
        appCompatButton2.setText(getString(R.string.lbl_cancel));
        appCompatButton2.setOnClickListener(new ua.o(alertDialog));
        alertDialog.show();
    }

    /* access modifiers changed from: private */
    public static final void L2(AlertDialog alertDialog, AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, View view) {
        alertDialog.dismiss();
        addApplicantBookAppointmentActivity.b().k();
    }

    /* access modifiers changed from: private */
    public static final void M2(AlertDialog alertDialog, View view) {
        alertDialog.dismiss();
    }

    private final void N2() {
        o d10 = com.vfs.italyglobal.utilities.c.d(this, R.layout.custom_webview_dialog);
        this.f9250g0 = (AlertDialog) d10.c();
        ((ImageView) ((View) d10.d()).findViewById(R.id.ivClose)).setOnClickListener(new ua.e(this));
        WebView webView = (WebView) ((View) d10.d()).findViewById(R.id.reCaptchaWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(true);
        webView.setHorizontalScrollBarEnabled(true);
        k3.c b10 = new c.b().c("localhost").a("/assets/", new c.a(this)).b();
        n.d(b10, "build(...)");
        webView.setWebViewClient(new com.vfs.italyglobal.utilities.f(b10));
        webView.loadUrl("https://localhost/assets/reCaptcha.html");
        webView.setWebChromeClient(new g(this));
        AlertDialog alertDialog = this.f9250g0;
        n.b(alertDialog);
        alertDialog.show();
    }

    /* access modifiers changed from: private */
    public static final void O2(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, View view) {
        AlertDialog alertDialog = addApplicantBookAppointmentActivity.f9250g0;
        n.b(alertDialog);
        alertDialog.dismiss();
    }

    private final void b2(String str, Integer num) {
        String str2;
        String str3;
        String appointmentCfmLift;
        j jVar = new j();
        Utility.Companion companion = Utility.f9497a;
        String q10 = companion.q();
        String k10 = companion.k(c1());
        jVar.r("missionCode", q10);
        jVar.r("countryCode", k10);
        g.a aVar = com.vfs.italyglobal.utilities.g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appmnt_login_loggedInUser);
        n.d(string, "getString(...)");
        String str4 = "";
        jVar.r("loginUser", aVar.b(c12, string, str4));
        jVar.r("arn", str);
        Activity c13 = c1();
        String string2 = getString(R.string.appmnt_login_authToken);
        n.d(string2, "getString(...)");
        String b10 = aVar.b(c13, string2, str4);
        String e10 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        hashMap.put("Authorize", b10);
        AppConfigModel e11 = d1().e();
        if (e11 == null || (str2 = e11.getAppointmentUrlOrigin()) == null) {
            str2 = str4;
        }
        hashMap.put("referer", str2);
        AppConfigModel e12 = d1().e();
        if (e12 == null || (str3 = e12.getAppointmentUrlOrigin()) == null) {
            str3 = str4;
        }
        hashMap.put("origin", str3);
        AppConfigModel e13 = d1().e();
        if (!(e13 == null || (appointmentCfmLift = e13.getAppointmentCfmLift()) == null)) {
            str4 = appointmentCfmLift;
        }
        hashMap.put("cfmlift", str4);
        hashMap.put("ClientSource", e10);
        f1().b(hashMap, jVar).A(new b(num, this));
    }

    private final void c2(SearchApplicantDetails searchApplicantDetails, int i10) {
        String str;
        String str2;
        String appointmentUrlOrigin;
        j jVar = new j();
        Utility.Companion companion = Utility.f9497a;
        String q10 = companion.q();
        String k10 = companion.k(c1());
        jVar.r("missionCode", q10);
        jVar.r("countryCode", k10);
        jVar.r("languageCode", "en-US");
        g.a aVar = com.vfs.italyglobal.utilities.g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appmnt_center_code);
        n.d(string, "getString(...)");
        String str3 = "";
        jVar.r("centerCode", aVar.b(c12, string, str3));
        Activity c13 = c1();
        String string2 = getString(R.string.appmnt_login_loggedInUser);
        n.d(string2, "getString(...)");
        jVar.r("loginUser", aVar.b(c13, string2, str3));
        Activity c14 = c1();
        String string3 = getString(R.string.appmnt_visa_sub_category_code);
        n.d(string3, "getString(...)");
        jVar.r("visaCategoryCode", aVar.b(c14, string3, str3));
        jVar.r("feeEntryTypeCode", str3);
        jVar.r("feeExemptionDetailsCode", str3);
        jVar.r("feeExemptionTypeCode", str3);
        Boolean bool = Boolean.FALSE;
        jVar.o("isWaitlist", bool);
        Activity c15 = c1();
        String string4 = getString(R.string.appointment_URN);
        n.d(string4, "getString(...)");
        String b10 = aVar.b(c15, string4, str3);
        if (b10.length() == 0) {
            b10 = str3;
        }
        j jVar2 = new j();
        jVar2.r("urn", b10);
        if (this.f9252i0) {
            jVar.o("isEdit", Boolean.TRUE);
            jVar2.r("arn", searchApplicantDetails.getArn());
        } else {
            jVar.o("isEdit", bool);
            jVar2.r("arn", str3);
        }
        Activity c16 = c1();
        String string5 = getString(R.string.appmnt_login_loggedInUser);
        n.d(string5, "getString(...)");
        jVar2.r("loginUser", aVar.b(c16, string5, str3));
        jVar2.r("firstName", searchApplicantDetails.getFirstName());
        jVar2.r("lastName", searchApplicantDetails.getLastName());
        jVar2.r("salutation", str3);
        jVar2.q("gender", Integer.valueOf(Integer.parseInt(String.valueOf(searchApplicantDetails.getGender()))));
        jVar2.r("contactNumber", searchApplicantDetails.getContactNumber());
        jVar2.r("dialCode", searchApplicantDetails.getDialCode());
        jVar2.r("passportNumber", searchApplicantDetails.getPassportNumber());
        jVar2.r("confirmPassportNumber", searchApplicantDetails.getPassportNumber());
        jVar2.r("passportExpirtyDate", String.valueOf(searchApplicantDetails.getPassportExpiryDate()));
        jVar2.r("dateOfBirth", searchApplicantDetails.getDateOfBirth());
        jVar2.r("emailId", searchApplicantDetails.getEmailId());
        jVar2.r("nationalityCode", searchApplicantDetails.getNationalityCode());
        jVar2.o("isEndorsedChild", bool);
        jVar2.q("applicantType", 0);
        jVar2.r("referenceNumber", str3);
        jVar2.q("applicantGroupId", 0);
        jVar2.r("parentPassportNumber", str3);
        jVar2.r("parentPassportExpiry", str3);
        jVar2.r("addressline1", str3);
        jVar2.r("addressline2", str3);
        jVar2.r("pincode", str3);
        jVar2.r("dateOfDeparture", str3);
        jVar2.r("ipAddress", companion.p());
        jVar2.r("state", str3);
        jVar2.r("city", str3);
        jVar2.r("applicantImage", "applicantImage.png");
        k2(jVar2);
        jVar2.r("employerContactNumber", str3);
        jVar2.r("employerContactNumber", str3);
        jVar2.r("employerDialCode", str3);
        jVar2.r("employerEmailId", str3);
        jVar2.r("employerFirstName", str3);
        jVar2.r("employerLastName", str3);
        jVar2.r("entryType", str3);
        jVar2.r("eoiVisaType", str3);
        jVar2.r("familyReunificationCerificateNumber", str3);
        jVar2.r("gwfNumber", str3);
        jVar2.r("middleName", str3);
        jVar2.r("nationalId", str3);
        jVar2.r("passportType", str3);
        jVar2.r("vfsReferenceNumber", str3);
        jVar2.r("VisaToken", str3);
        jVar2.r("vlnNumber", str3);
        com.google.gson.f fVar = new com.google.gson.f();
        fVar.n(jVar2);
        jVar.n("applicantList", fVar);
        Activity c17 = c1();
        String string6 = getString(R.string.appmnt_login_authToken);
        n.d(string6, "getString(...)");
        String b11 = aVar.b(c17, string6, str3);
        String e10 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        hashMap.put("Authorize", b11);
        hashMap.put("Content-Type", "application/json");
        AppConfigModel e11 = d1().e();
        if (e11 == null || (str = e11.getAppointmentUrlOrigin()) == null) {
            str = str3;
        }
        hashMap.put("origin", str);
        AppConfigModel e12 = d1().e();
        if (e12 == null || (str2 = e12.getAppointmentCfmLift()) == null) {
            str2 = str3;
        }
        hashMap.put("cfmlift", str2);
        AppConfigModel e13 = d1().e();
        if (!(e13 == null || (appointmentUrlOrigin = e13.getAppointmentUrlOrigin()) == null)) {
            str3 = appointmentUrlOrigin;
        }
        hashMap.put("referer", str3);
        hashMap.put("ClientSource", e10);
        f1().u(hashMap, jVar).A(new c(this, i10, searchApplicantDetails));
    }

    private final void d2() {
        String str;
        String str2;
        String appointmentCfmLift;
        Utility.Companion companion = Utility.f9497a;
        companion.y(c1());
        String e10 = companion.e(this, companion.l());
        HashMap hashMap = new HashMap();
        AppConfigModel e11 = d1().e();
        String str3 = "";
        if (e11 == null || (str = e11.getAppointmentUrlOrigin()) == null) {
            str = str3;
        }
        hashMap.put("referer", str);
        AppConfigModel e12 = d1().e();
        if (e12 == null || (str2 = e12.getAppointmentUrlOrigin()) == null) {
            str2 = str3;
        }
        hashMap.put("origin", str2);
        AppConfigModel e13 = d1().e();
        if (!(e13 == null || (appointmentCfmLift = e13.getAppointmentCfmLift()) == null)) {
            str3 = appointmentCfmLift;
        }
        hashMap.put("cfmlift", str3);
        hashMap.put("ClientSource", e10);
        f1().o(hashMap, "en-US").A(new d(this));
    }

    private final void e2() {
        if (!f9247v0.a().isEmpty()) {
            K2();
        } else {
            b().k();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0028 A[SYNTHETIC, Splitter:B:20:0x0028] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0033 A[SYNTHETIC, Splitter:B:26:0x0033] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final byte[] g2(android.graphics.Bitmap r5) {
        /*
            r4 = this;
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0021, all -> 0x001f }
            r1.<init>()     // Catch:{ Exception -> 0x0021, all -> 0x001f }
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x001d }
            r3 = 100
            r5.compress(r2, r3, r1)     // Catch:{ Exception -> 0x001d }
            byte[] r5 = r1.toByteArray()     // Catch:{ Exception -> 0x001d }
            r1.close()     // Catch:{ IOException -> 0x0015 }
            return r5
        L_0x0015:
            r0 = move-exception
            r0.getMessage()
            return r5
        L_0x001a:
            r5 = move-exception
            r0 = r1
            goto L_0x0031
        L_0x001d:
            r5 = move-exception
            goto L_0x0023
        L_0x001f:
            r5 = move-exception
            goto L_0x0031
        L_0x0021:
            r5 = move-exception
            r1 = r0
        L_0x0023:
            r5.getMessage()     // Catch:{ all -> 0x001a }
            if (r1 == 0) goto L_0x0030
            r1.close()     // Catch:{ IOException -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r5 = move-exception
            r5.getMessage()
        L_0x0030:
            return r0
        L_0x0031:
            if (r0 == 0) goto L_0x003b
            r0.close()     // Catch:{ IOException -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r0 = move-exception
            r0.getMessage()
        L_0x003b:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.AddApplicantBookAppointmentActivity.g2(android.graphics.Bitmap):byte[]");
    }

    private final xa.e j2() {
        return (xa.e) this.f9249f0.getValue();
    }

    private final void k2(j jVar) {
        try {
            byte[] bArr = this.f9256m0;
            if (bArr == null) {
                jVar.r("applicantImageData", "null");
                jVar.r("signatureData", "null");
                return;
            }
            n.b(bArr);
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            if (decodeByteArray != null) {
                com.vfs.italyglobal.utilities.d dVar = com.vfs.italyglobal.utilities.d.f9508a;
                String keyService1 = KeyProvider.getKeyService1();
                n.d(keyService1, "getKeyService1(...)");
                Map e10 = dVar.e(decodeByteArray, this.f9257n0.getFirstName() + this.f9257n0.getLastName() + ';' + Utility.f9497a.m(), keyService1);
                String str = (String) e10.get("imageData");
                if (str != null) {
                    String str2 = (String) e10.get("signatureData");
                    if (str2 != null) {
                        jVar.r("applicantImageData", str);
                        jVar.r("signatureData", str2);
                        return;
                    }
                    throw new IllegalStateException("Missing signatureData");
                }
                throw new IllegalStateException("Missing imageData");
            }
            throw new IllegalStateException("Bitmap conversion failed");
        } catch (Exception e11) {
            e11.getMessage();
            e11.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public static final void m2(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, View view) {
        if (f9247v0.a().size() < addApplicantBookAppointmentActivity.f9259p0) {
            addApplicantBookAppointmentActivity.z2(true, addApplicantBookAppointmentActivity.f9257n0, 0);
            return;
        }
        Utility.Companion companion = Utility.f9497a;
        Activity c12 = addApplicantBookAppointmentActivity.c1();
        companion.w(c12, addApplicantBookAppointmentActivity.getString(R.string.you_cannot_add_more_than) + addApplicantBookAppointmentActivity.f9259p0 + addApplicantBookAppointmentActivity.getString(R.string.applicants));
    }

    /* access modifiers changed from: private */
    public static final void n2(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, View view) {
        addApplicantBookAppointmentActivity.e2();
    }

    /* access modifiers changed from: private */
    public static final void o2(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, View view) {
        a aVar = f9247v0;
        if (!aVar.a().isEmpty()) {
            AppConfigModel.CountryConfig j10 = addApplicantBookAppointmentActivity.d1().j();
            if (j10 == null || !j10.getRecaptchaAppointmentAccess()) {
                g.a aVar2 = com.vfs.italyglobal.utilities.g.f9512a;
                Activity c12 = addApplicantBookAppointmentActivity.c1();
                String string = addApplicantBookAppointmentActivity.getString(R.string.appointment_URN);
                n.d(string, "getString(...)");
                aVar2.g(c12, string, String.valueOf(((SearchApplicantDetails) aVar.a().get(0)).getUrn()));
                Activity c13 = addApplicantBookAppointmentActivity.c1();
                String string2 = addApplicantBookAppointmentActivity.getString(R.string.applicants_count);
                n.d(string2, "getString(...)");
                aVar2.f(c13, string2, aVar.a().size());
                if (addApplicantBookAppointmentActivity.f9253j0 || addApplicantBookAppointmentActivity.f9254k0) {
                    addApplicantBookAppointmentActivity.r2(String.valueOf(((SearchApplicantDetails) aVar.a().get(0)).getUrn()));
                } else {
                    addApplicantBookAppointmentActivity.q2();
                }
            } else {
                addApplicantBookAppointmentActivity.N2();
            }
        } else {
            Utility.Companion companion = Utility.f9497a;
            String string3 = addApplicantBookAppointmentActivity.getString(R.string.please_add_applicant);
            n.d(string3, "getString(...)");
            companion.w(addApplicantBookAppointmentActivity, string3);
        }
    }

    /* access modifiers changed from: private */
    public static final xa.e p2(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity) {
        xa.e c10 = xa.e.c(addApplicantBookAppointmentActivity.getLayoutInflater());
        n.d(c10, "inflate(...)");
        return c10;
    }

    /* access modifiers changed from: private */
    public final void r2(String str) {
        Intent intent = new Intent(this, OtpVerification.class);
        intent.putExtra(getString(R.string.bundle_urnNumber), str);
        intent.putExtra(getString(R.string.bundle_isFromApplicant), true);
        intent.putExtra(getString(R.string.isToReschedule), false);
        intent.putExtra(getString(R.string.no_of_applicants), f9247v0.a().size());
        startActivity(intent);
    }

    private final void s2() {
        d.b b10 = d.b.b();
        b10.q(false);
        b10.d(false);
        b10.o(true);
        b10.i(true);
        b10.m(true);
        b10.s(false);
        b10.n(6);
        b10.j(23);
        b10.e(30);
        b10.p(true);
        b10.k(true);
        b10.f(true);
        b10.g(80);
        b10.l(false);
        d.a aVar = new d.a();
        aVar.s(15);
        aVar.v(15);
        aVar.t(10);
        aVar.m(60);
        aVar.r(0.5d);
        aVar.u(0.5d);
        aVar.n(0.4d);
        aVar.q(0.5d);
        Float valueOf = Float.valueOf(10.0f);
        aVar.o(valueOf);
        aVar.p(valueOf);
        b10.c(aVar);
        b10.h(6);
        if (Y0()) {
            b10.r("", this, this);
        } else {
            m1(new ua.g(b10, this));
        }
        com.vfs.italyglobal.utilities.c.l("tech5_capture_initiate", (Bundle) null, 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final nb.x t2(d.b bVar, AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity) {
        bVar.r("", addApplicantBookAppointmentActivity, addApplicantBookAppointmentActivity);
        return nb.x.f15721a;
    }

    private final void u2(EditText editText, int i10, View view) {
        View view2 = view;
        Locale.setDefault(Locale.ENGLISH);
        Calendar instance = Calendar.getInstance();
        n.d(instance, "getInstance(...)");
        this.f9260q0 = instance;
        Calendar calendar = null;
        if (instance == null) {
            n.o("myCalendar");
            instance = null;
        }
        int i11 = instance.get(1);
        Calendar calendar2 = this.f9260q0;
        if (calendar2 == null) {
            n.o("myCalendar");
            calendar2 = null;
        }
        int i12 = calendar2.get(2);
        Calendar calendar3 = this.f9260q0;
        if (calendar3 == null) {
            n.o("myCalendar");
            calendar3 = null;
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new ua.f(this, i10, (Button) view2.findViewById(R.id.btn_add_photo), (TextView) view2.findViewById(R.id.txt_add_photo_desc), view2, editText), i11, i12, calendar3.get(5));
        Calendar calendar4 = this.f9260q0;
        if (calendar4 == null) {
            n.o("myCalendar");
            calendar4 = null;
        }
        Date time = calendar4.getTime();
        if (i10 == 1) {
            datePickerDialog.getDatePicker().setMaxDate(time.getTime());
        } else {
            datePickerDialog.getDatePicker().setMinDate(time.getTime());
            Calendar calendar5 = this.f9260q0;
            if (calendar5 == null) {
                n.o("myCalendar");
                calendar5 = null;
            }
            calendar5.add(1, 10);
            DatePicker datePicker = datePickerDialog.getDatePicker();
            Calendar calendar6 = this.f9260q0;
            if (calendar6 == null) {
                n.o("myCalendar");
            } else {
                calendar = calendar6;
            }
            datePicker.setMaxDate(calendar.getTime().getTime());
        }
        datePickerDialog.show();
    }

    /* access modifiers changed from: private */
    public static final void v2(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, int i10, Button button, TextView textView, View view, EditText editText, DatePicker datePicker, int i11, int i12, int i13) {
        AppConfigModel.CountryConfig j10;
        Calendar calendar = addApplicantBookAppointmentActivity.f9260q0;
        Calendar calendar2 = null;
        if (calendar == null) {
            n.o("myCalendar");
            calendar = null;
        }
        calendar.set(1, i11);
        Calendar calendar3 = addApplicantBookAppointmentActivity.f9260q0;
        if (calendar3 == null) {
            n.o("myCalendar");
            calendar3 = null;
        }
        calendar3.set(2, i12);
        Calendar calendar4 = addApplicantBookAppointmentActivity.f9260q0;
        if (calendar4 == null) {
            n.o("myCalendar");
            calendar4 = null;
        }
        calendar4.set(5, i13);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        if (i10 == 1) {
            if (!addApplicantBookAppointmentActivity.f9255l0 || (j10 = addApplicantBookAppointmentActivity.d1().j()) == null || !j10.getFaceLivelinessAccess() || Utility.f9497a.g(i11, i12, i13) < 18) {
                button.setVisibility(8);
                textView.setVisibility(8);
                addApplicantBookAppointmentActivity.f9256m0 = null;
                ((LinearLayoutCompat) view.findViewById(R.id.faceImageLL)).setVisibility(8);
            } else {
                button.setVisibility(0);
                textView.setVisibility(0);
                button.setOnClickListener(new ua.h(addApplicantBookAppointmentActivity));
            }
        }
        Calendar calendar5 = addApplicantBookAppointmentActivity.f9260q0;
        if (calendar5 == null) {
            n.o("myCalendar");
        } else {
            calendar2 = calendar5;
        }
        editText.setText(simpleDateFormat.format(calendar2.getTime()));
    }

    /* access modifiers changed from: private */
    public static final void w2(AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity, View view) {
        addApplicantBookAppointmentActivity.s2();
    }

    private final void y2() {
        g.a aVar = com.vfs.italyglobal.utilities.g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.appointment_URN);
        n.d(string, "getString(...)");
        aVar.g(c12, string, "");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v0, resolved type: com.vfs.italyglobal.pojo.Nationality} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v1, resolved type: com.vfs.italyglobal.pojo.Nationality} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v5, resolved type: com.vfs.italyglobal.pojo.Nationality} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r22v3, resolved type: com.vfs.italyglobal.pojo.Nationality} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void z2(boolean r40, com.vfs.italyglobal.pojo.SearchApplicantDetails r41, java.lang.Integer r42) {
        /*
            r39 = this;
            r1 = r39
            r0 = 2131558473(0x7f0d0049, float:1.8742263E38)
            nb.o r0 = com.vfs.italyglobal.utilities.c.d(r1, r0)
            java.lang.Object r2 = r0.c()
            android.app.AlertDialog r2 = (android.app.AlertDialog) r2
            r1.f9250g0 = r2
            java.lang.Object r0 = r0.d()
            r9 = r0
            android.view.View r9 = (android.view.View) r9
            r1.f9251h0 = r9
            if (r9 == 0) goto L_0x036e
            bc.x r13 = new bc.x
            r13.<init>()
            java.lang.String r0 = ""
            r13.f4725b = r0
            bc.x r14 = new bc.x
            r14.<init>()
            r14.f4725b = r0
            r2 = 2131362171(0x7f0a017b, float:1.8344115E38)
            android.view.View r2 = r9.findViewById(r2)
            r3 = r2
            android.widget.EditText r3 = (android.widget.EditText) r3
            r2 = 2131362157(0x7f0a016d, float:1.8344087E38)
            android.view.View r2 = r9.findViewById(r2)
            r4 = r2
            android.widget.EditText r4 = (android.widget.EditText) r4
            r2 = 2131362162(0x7f0a0172, float:1.8344097E38)
            android.view.View r2 = r9.findViewById(r2)
            android.widget.EditText r2 = (android.widget.EditText) r2
            r5 = 2131362160(0x7f0a0170, float:1.8344093E38)
            android.view.View r5 = r9.findViewById(r5)
            r6 = r5
            android.widget.EditText r6 = (android.widget.EditText) r6
            r5 = 2131362161(0x7f0a0171, float:1.8344095E38)
            android.view.View r5 = r9.findViewById(r5)
            android.widget.EditText r5 = (android.widget.EditText) r5
            r7 = 2131361961(0x7f0a00a9, float:1.834369E38)
            android.view.View r7 = r9.findViewById(r7)
            android.widget.RadioGroup r7 = (android.widget.RadioGroup) r7
            r8 = 2131362158(0x7f0a016e, float:1.8344089E38)
            android.view.View r8 = r9.findViewById(r8)
            r15 = r8
            android.widget.EditText r15 = (android.widget.EditText) r15
            r8 = 2131362159(0x7f0a016f, float:1.834409E38)
            android.view.View r8 = r9.findViewById(r8)
            android.widget.EditText r8 = (android.widget.EditText) r8
            r10 = 2131362804(0x7f0a03f4, float:1.8345399E38)
            android.view.View r10 = r9.findViewById(r10)
            android.widget.TextView r10 = (android.widget.TextView) r10
            com.vfs.italyglobal.utilities.g$a r11 = com.vfs.italyglobal.utilities.g.f9512a
            android.app.Activity r12 = r1.c1()
            r16 = r2
            r2 = 2131886166(0x7f120056, float:1.9406903E38)
            java.lang.String r2 = r1.getString(r2)
            r17 = r3
            java.lang.String r3 = "getString(...)"
            bc.n.d(r2, r3)
            java.lang.String r2 = r11.b(r12, r2, r0)
            android.app.Activity r12 = r1.c1()
            r18 = r2
            r2 = 2131886169(0x7f120059, float:1.940691E38)
            java.lang.String r2 = r1.getString(r2)
            bc.n.d(r2, r3)
            java.lang.String r2 = r11.b(r12, r2, r0)
            com.vfs.italyglobal.utilities.Utility$Companion r12 = com.vfs.italyglobal.utilities.Utility.f9497a
            r19 = r2
            android.app.Activity r2 = r1.c1()
            java.lang.String r2 = r12.k(r2)
            bc.x r12 = new bc.x
            r12.<init>()
            r20 = r4
            android.app.Activity r4 = r1.c1()
            r21 = r5
            r5 = 2131886167(0x7f120057, float:1.9406905E38)
            java.lang.String r5 = r1.getString(r5)
            bc.n.d(r5, r3)
            java.lang.String r3 = r11.b(r4, r5, r0)
            r12.f4725b = r3
            java.lang.String r4 = "+"
            r5 = 0
            kc.n.E(r3, r4, r0, r5)
            java.lang.Object r3 = r12.f4725b
            boolean r3 = bc.n.a(r3, r0)
            java.lang.String r4 = "DZA"
            if (r3 == 0) goto L_0x00f2
            boolean r3 = bc.n.a(r2, r4)
            if (r3 == 0) goto L_0x00f2
            java.lang.String r3 = "213"
            r12.f4725b = r3
        L_0x00f2:
            r3 = 2131362615(0x7f0a0337, float:1.8345016E38)
            android.view.View r3 = r9.findViewById(r3)
            androidx.appcompat.widget.AppCompatSpinner r3 = (androidx.appcompat.widget.AppCompatSpinner) r3
            com.vfs.italyglobal.activities.AppointmentGetEarliestDates$b r11 = new com.vfs.italyglobal.activities.AppointmentGetEarliestDates$b
            java.util.ArrayList r5 = r1.f9262s0
            r22 = 0
            if (r5 != 0) goto L_0x010a
            java.lang.String r5 = "nationalityListName"
            bc.n.o(r5)
            r5 = r22
        L_0x010a:
            r23 = r4
            r4 = 2131558623(0x7f0d00df, float:1.8742567E38)
            r11.<init>(r1, r4, r5)
            r4 = 2131558622(0x7f0d00de, float:1.8742565E38)
            r11.setDropDownViewResource(r4)
            r3.setAdapter((android.widget.SpinnerAdapter) r11)
            java.util.ArrayList r4 = r1.f9261r0
            java.lang.String r24 = "nationalityList"
            if (r4 != 0) goto L_0x0126
            bc.n.o(r24)
            r4 = r22
        L_0x0126:
            java.util.Iterator r4 = r4.iterator()
            r5 = 0
        L_0x012b:
            boolean r25 = r4.hasNext()
            if (r25 == 0) goto L_0x0149
            java.lang.Object r25 = r4.next()
            com.vfs.italyglobal.pojo.Nationality r25 = (com.vfs.italyglobal.pojo.Nationality) r25
            r26 = r4
            java.lang.String r4 = r25.getIsoCode()
            boolean r4 = bc.n.a(r4, r2)
            if (r4 == 0) goto L_0x0144
            goto L_0x014a
        L_0x0144:
            int r5 = r5 + 1
            r4 = r26
            goto L_0x012b
        L_0x0149:
            r5 = -1
        L_0x014a:
            if (r5 < 0) goto L_0x014f
            r3.setSelection(r5)
        L_0x014f:
            com.vfs.italyglobal.activities.AddApplicantBookAppointmentActivity$f r4 = new com.vfs.italyglobal.activities.AddApplicantBookAppointmentActivity$f
            r4.<init>(r14, r1)
            r3.setOnItemSelectedListener(r4)
            r4 = 2131362529(0x7f0a02e1, float:1.8344841E38)
            android.view.View r4 = r9.findViewById(r4)
            android.widget.RadioButton r4 = (android.widget.RadioButton) r4
            r5 = 2131362528(0x7f0a02e0, float:1.834484E38)
            android.view.View r5 = r9.findViewById(r5)
            android.widget.RadioButton r5 = (android.widget.RadioButton) r5
            r25 = r2
            r2 = 2131362530(0x7f0a02e2, float:1.8344843E38)
            android.view.View r2 = r9.findViewById(r2)
            android.widget.RadioButton r2 = (android.widget.RadioButton) r2
            r26 = r14
            bc.x r14 = new bc.x
            r14.<init>()
            r14.f4725b = r0
            ua.p r0 = new ua.p
            r0.<init>(r9, r14)
            r7.setOnCheckedChangeListener(r0)
            r0 = 2131362009(0x7f0a00d9, float:1.8343786E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.Button r0 = (android.widget.Button) r0
            r7 = 2131362026(0x7f0a00ea, float:1.834382E38)
            android.view.View r7 = r9.findViewById(r7)
            android.widget.Button r7 = (android.widget.Button) r7
            r27 = r0
            r0 = 2131362001(0x7f0a00d1, float:1.834377E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.Button r0 = (android.widget.Button) r0
            r28 = r0
            r0 = 2131362002(0x7f0a00d2, float:1.8343772E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.Button r0 = (android.widget.Button) r0
            r29 = r0
            r0 = 2131362802(0x7f0a03f2, float:1.8345395E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.TextView r0 = (android.widget.TextView) r0
            r30 = r0
            r0 = 2131362027(0x7f0a00eb, float:1.8343823E38)
            android.view.View r0 = r9.findViewById(r0)
            android.widget.Button r0 = (android.widget.Button) r0
            r31 = r2
            ua.q r2 = new ua.q
            r2.<init>(r1)
            r0.setOnClickListener(r2)
            ua.r r0 = new ua.r
            r2 = r20
            r20 = r3
            r3 = r2
            r2 = r11
            r11 = r5
            r5 = r21
            r21 = r2
            r35 = r12
            r32 = r14
            r2 = r16
            r33 = r18
            r34 = r19
            r14 = r23
            r36 = r27
            r37 = r28
            r12 = r31
            r19 = r8
            r16 = r13
            r18 = r15
            r13 = r25
            r8 = r1
            r15 = r7
            r1 = r17
            r7 = r30
            r17 = r10
            r10 = r4
            r4 = r6
            r6 = r29
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r6 = r4
            r4 = r3
            r3 = r1
            r1 = r8
            r15.setOnClickListener(r0)
            ua.s r0 = new ua.s
            r0.<init>(r1, r4, r9)
            r4.setOnClickListener(r0)
            ua.b r0 = new ua.b
            r0.<init>(r1, r2, r9)
            r2.setOnClickListener(r0)
            boolean r0 = bc.n.a(r13, r14)
            if (r0 == 0) goto L_0x022e
            java.lang.String r0 = "ALGERIA"
            r7 = r21
            int r0 = r7.getPosition(r0)
            r8 = r20
            r8.setSelection(r0)
            goto L_0x0232
        L_0x022e:
            r8 = r20
            r7 = r21
        L_0x0232:
            if (r40 != 0) goto L_0x031c
            bc.n.b(r41)
            java.lang.String r0 = r41.getPassportNumber()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r3.setText(r0)
            java.lang.String r0 = r41.getPassportExpiryDate()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r2.setText(r0)
            java.lang.String r0 = r41.getDateOfBirth()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r4.setText(r0)
            java.lang.String r0 = r41.getFirstName()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r6.setText(r0)
            java.lang.String r0 = r41.getLastName()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r5.setText(r0)
            java.lang.String r0 = r41.getEmailId()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r9 = r19
            r9.setText(r0)
            java.lang.String r0 = r41.getContactNumber()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r13 = r18
            r13.setText(r0)
            java.lang.String r0 = r41.getNationalityCode()
            if (r0 == 0) goto L_0x02c8
            java.util.ArrayList r0 = r1.f9261r0
            if (r0 != 0) goto L_0x0297
            bc.n.o(r24)
            r0 = r22
        L_0x0297:
            java.util.Iterator r0 = r0.iterator()
        L_0x029b:
            boolean r9 = r0.hasNext()
            if (r9 == 0) goto L_0x02b8
            java.lang.Object r9 = r0.next()
            r13 = r9
            com.vfs.italyglobal.pojo.Nationality r13 = (com.vfs.italyglobal.pojo.Nationality) r13
            java.lang.String r13 = r13.getIsoCode()
            java.lang.String r14 = r41.getNationalityCode()
            boolean r13 = bc.n.a(r13, r14)
            if (r13 == 0) goto L_0x029b
            r22 = r9
        L_0x02b8:
            com.vfs.italyglobal.pojo.Nationality r22 = (com.vfs.italyglobal.pojo.Nationality) r22
            bc.n.b(r22)
            java.lang.String r0 = r22.getNationalityName()
            int r0 = r7.getPosition(r0)
            r8.setSelection(r0)
        L_0x02c8:
            java.lang.Integer r0 = r41.getGender()
            r7 = 1
            if (r0 != 0) goto L_0x02d0
            goto L_0x02da
        L_0x02d0:
            int r0 = r0.intValue()
            if (r0 != r7) goto L_0x02da
            r10.setChecked(r7)
            goto L_0x02ef
        L_0x02da:
            java.lang.Integer r0 = r41.getGender()
            if (r0 != 0) goto L_0x02e1
            goto L_0x02ec
        L_0x02e1:
            int r0 = r0.intValue()
            r8 = 2
            if (r0 != r8) goto L_0x02ec
            r11.setChecked(r7)
            goto L_0x02ef
        L_0x02ec:
            r12.setChecked(r7)
        L_0x02ef:
            r1.f9252i0 = r7
            r0 = 2131886212(0x7f120084, float:1.9406996E38)
            java.lang.String r0 = r1.getString(r0)
            r7 = r37
            r7.setText(r0)
            r0 = 2131886778(0x7f1202ba, float:1.9408144E38)
            java.lang.String r0 = r1.getString(r0)
            r10 = r17
            r10.setText(r0)
            java.lang.String r0 = r41.getArn()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r8 = r16
            r8.f4725b = r0
            r10 = r33
            r11 = r34
            r14 = r35
            goto L_0x034b
        L_0x031c:
            r8 = r16
            r13 = r18
            r9 = r19
            r7 = r37
            r0 = 0
            r1.f9252i0 = r0
            r11 = r34
            r9.setText(r11)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r14 = r35
            java.lang.Object r9 = r14.f4725b
            java.lang.String r9 = (java.lang.String) r9
            r0.append(r9)
            java.lang.String r9 = " - "
            r0.append(r9)
            r10 = r33
            r0.append(r10)
            java.lang.String r0 = r0.toString()
            r13.setText(r0)
        L_0x034b:
            ua.c r0 = new ua.c
            r0.<init>(r1)
            r9 = r36
            r9.setOnClickListener(r0)
            ua.d r0 = new ua.d
            r15 = r42
            r38 = r7
            r13 = r8
            r9 = r26
            r8 = r29
            r12 = r32
            r7 = r5
            r5 = r2
            r2 = r41
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            r7 = r38
            r7.setOnClickListener(r0)
        L_0x036e:
            android.app.AlertDialog r0 = r1.f9250g0
            bc.n.b(r0)
            r0.show()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.AddApplicantBookAppointmentActivity.z2(boolean, com.vfs.italyglobal.pojo.SearchApplicantDetails, java.lang.Integer):void");
    }

    public final void H2(String str, Integer num) {
        o d10 = com.vfs.italyglobal.utilities.c.d(this, R.layout.custom_alert_cancel_appmnt_dialog);
        AlertDialog alertDialog = (AlertDialog) d10.c();
        ((TextView) ((View) d10.d()).findViewById(R.id.txt_centerText)).setText(getString(R.string.delete_applicant_alert));
        ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog_no_cancel)).setOnClickListener(new ua.i(alertDialog));
        ((AppCompatButton) ((View) d10.d()).findViewById(R.id.btn_dialog_yes_cancel)).setOnClickListener(new ua.j(this, str, num, alertDialog));
        alertDialog.show();
    }

    public final void P2(ArrayList arrayList) {
        n.e(arrayList, "arrApplicantList");
        x2(new wa.e(arrayList, true, new h(this)));
        j2().f20364f.setLayoutManager(new LinearLayoutManager(this));
        j2().f20364f.t0();
        j2().f20364f.setAdapter(h2());
        f2();
    }

    public final void f2() {
        if (!f9247v0.a().isEmpty()) {
            j2().f20363e.setVisibility(8);
            j2().f20364f.setVisibility(0);
            return;
        }
        j2().f20363e.setVisibility(0);
        j2().f20364f.setVisibility(8);
        y2();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        if (r6 == null) goto L_0x0026;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void h(byte[] r5, byte[] r6, com.phoenixcapture.camerakit.FaceBox r7) {
        /*
            r4 = this;
            java.lang.String r6 = "getString(...)"
            if (r7 == 0) goto L_0x008e
            float r0 = r7.mLiveness
            java.lang.String.valueOf(r0)
            float r0 = r7.mLiveness
            double r0 = (double) r0
            r2 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L_0x007a
            android.graphics.Bitmap r6 = r7.mPortalImageSegmented
            r0 = 1
            if (r6 == 0) goto L_0x0026
            int r7 = r7.mHasPortalImageSegmented
            if (r7 != r0) goto L_0x0023
            byte[] r6 = r4.g2(r6)
            bc.n.b(r6)
            goto L_0x0024
        L_0x0023:
            r6 = r5
        L_0x0024:
            if (r6 != 0) goto L_0x0027
        L_0x0026:
            r6 = r5
        L_0x0027:
            r4.f9256m0 = r6
            android.view.View r6 = r4.f9251h0
            bc.n.b(r6)
            r7 = 2131362197(0x7f0a0195, float:1.8344168E38)
            android.view.View r6 = r6.findViewById(r7)
            androidx.appcompat.widget.LinearLayoutCompat r6 = (androidx.appcompat.widget.LinearLayoutCompat) r6
            android.view.View r7 = r4.f9251h0
            bc.n.b(r7)
            r1 = 2131362303(0x7f0a01ff, float:1.8344383E38)
            android.view.View r7 = r7.findViewById(r1)
            android.widget.ImageView r7 = (android.widget.ImageView) r7
            if (r5 == 0) goto L_0x0079
            int r5 = r5.length
            r1 = 0
            if (r5 != 0) goto L_0x004c
            goto L_0x004d
        L_0x004c:
            r0 = r1
        L_0x004d:
            if (r0 != 0) goto L_0x0079
            r6.setVisibility(r1)
            android.view.View r5 = r4.f9251h0
            bc.n.b(r5)
            r6 = 2131362002(0x7f0a00d2, float:1.8343772E38)
            android.view.View r5 = r5.findViewById(r6)
            android.widget.Button r5 = (android.widget.Button) r5
            r6 = 8
            r5.setVisibility(r6)
            com.bumptech.glide.l r5 = com.bumptech.glide.b.u(r4)
            byte[] r6 = r4.f9256m0
            com.bumptech.glide.k r5 = r5.u(r6)
            r5.z0(r7)
            java.lang.String r5 = "tech5_capture_success"
            r6 = 2
            r7 = 0
            com.vfs.italyglobal.utilities.c.l(r5, r7, r6, r7)
        L_0x0079:
            return
        L_0x007a:
            com.vfs.italyglobal.utilities.Utility$Companion r5 = com.vfs.italyglobal.utilities.Utility.f9497a
            android.app.Activity r7 = r4.c1()
            r0 = 2131886124(0x7f12002c, float:1.9406818E38)
            java.lang.String r0 = r4.getString(r0)
            bc.n.d(r0, r6)
            r5.w(r7, r0)
            return
        L_0x008e:
            com.vfs.italyglobal.utilities.Utility$Companion r5 = com.vfs.italyglobal.utilities.Utility.f9497a
            android.app.Activity r7 = r4.c1()
            r0 = 2131886350(0x7f12010e, float:1.9407276E38)
            java.lang.String r0 = r4.getString(r0)
            bc.n.d(r0, r6)
            r5.w(r7, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vfs.italyglobal.activities.AddApplicantBookAppointmentActivity.h(byte[], byte[], com.phoenixcapture.camerakit.FaceBox):void");
    }

    public final wa.e h2() {
        wa.e eVar = this.f9258o0;
        if (eVar != null) {
            return eVar;
        }
        n.o("applicantListAdapter");
        return null;
    }

    public final AlertDialog i2() {
        return this.f9250g0;
    }

    public final void l2() {
        y2();
        g.a aVar = com.vfs.italyglobal.utilities.g.f9512a;
        Activity c12 = c1();
        String string = getString(R.string.no_of_applicants);
        n.d(string, "getString(...)");
        this.f9259p0 = aVar.a(c12, string, 0);
        Bundle extras = getIntent().getExtras();
        n.b(extras);
        this.f9253j0 = extras.getBoolean(getString(R.string.bundle_isOTPEnabled));
        this.f9254k0 = extras.getBoolean(getString(R.string.bundle_isApplicantOTPEnabled));
        this.f9255l0 = extras.getBoolean(getString(R.string.bundle_isFaceLivenessEnabled));
        f9247v0.b(new ArrayList());
        j2().f20360b.setOnClickListener(new k(this));
        j2().f20362d.setOnClickListener(new l(this));
        j2().f20361c.setOnClickListener(new m(this));
        this.f9261r0 = new ArrayList();
        this.f9262s0 = new ArrayList();
        d2();
        b().h(this, new e(this));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((View) j2().b());
        l2();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        Utility.Companion companion = Utility.f9497a;
        companion.c();
        companion.d();
        AlertDialog alertDialog = this.f9250g0;
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
            this.f9251h0 = null;
        }
    }

    public boolean onKeyDown(int i10, KeyEvent keyEvent) {
        if (i10 != 4) {
            return false;
        }
        e2();
        return false;
    }

    public final void q2() {
        Intent intent = new Intent(this, AppointmentDateAndTimeListActivity.class);
        String string = getString(R.string.booked_appmnt_applcnt_details);
        SearchAppointmentDetails searchAppointmentDetails = this.f9264u0;
        n.c(searchAppointmentDetails, "null cannot be cast to non-null type java.io.Serializable");
        intent.putExtra(string, searchAppointmentDetails);
        intent.putExtra(getString(R.string.no_of_applicants), f9247v0.a().size());
        intent.putExtra(getString(R.string.isToReschedule), this.f9263t0);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
    }

    public void u() {
        com.vfs.italyglobal.utilities.c.l("tech5_capture_cancelled", (Bundle) null, 2, (Object) null);
    }

    public void x(String str) {
        n.e(str, "errorMessage");
        com.vfs.italyglobal.utilities.c.l("tech5_capture_failed", (Bundle) null, 2, (Object) null);
        Utility.f9497a.w(c1(), str);
    }

    public final void x2(wa.e eVar) {
        n.e(eVar, "<set-?>");
        this.f9258o0 = eVar;
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static final class f implements AdapterView.OnItemSelectedListener {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ bc.x f9272b;

        /* renamed from: n  reason: collision with root package name */
        final /* synthetic */ AddApplicantBookAppointmentActivity f9273n;

        f(bc.x xVar, AddApplicantBookAppointmentActivity addApplicantBookAppointmentActivity) {
            this.f9272b = xVar;
            this.f9273n = addApplicantBookAppointmentActivity;
        }

        public void onItemSelected(AdapterView adapterView, View view, int i10, long j10) {
            bc.x xVar = this.f9272b;
            ArrayList T1 = this.f9273n.f9261r0;
            if (T1 == null) {
                n.o("nationalityList");
                T1 = null;
            }
            xVar.f4725b = String.valueOf(((Nationality) T1.get(i10)).getIsoCode());
        }

        public void onNothingSelected(AdapterView adapterView) {
        }
    }

    public void v(byte[] bArr) {
    }
}
