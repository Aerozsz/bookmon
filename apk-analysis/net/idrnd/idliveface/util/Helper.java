package net.idrnd.idliveface.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import net.idrnd.idliveface.Validation;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
public class Helper {
    public static final NativeLibrary NATIVE_LIBRARY = new NativeLibrary();
    private static final Validation[] VALIDATION_VALUES = Validation.values();

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static class NativeLibrary {
        private NativeLibrary() {
            System.loadLibrary("idliveface");
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    public static class ToStringGenerator {
        private static final String GLUE = ", ";
        private final StringBuilder builder;

        private ToStringGenerator(Class<?> cls) {
            StringBuilder sb2 = new StringBuilder();
            this.builder = sb2;
            sb2.append(cls.getSimpleName());
            sb2.append('(');
        }

        public ToStringGenerator field(String str, float f10) {
            StringBuilder sb2 = this.builder;
            sb2.append(str);
            sb2.append('=');
            sb2.append(f10);
            sb2.append(GLUE);
            return this;
        }

        public String generate() {
            StringBuilder sb2 = this.builder;
            sb2.setLength(sb2.length() - 2);
            this.builder.append(')');
            return this.builder.toString();
        }

        public ToStringGenerator field(String str, int i10) {
            StringBuilder sb2 = this.builder;
            sb2.append(str);
            sb2.append('=');
            sb2.append(i10);
            sb2.append(GLUE);
            return this;
        }

        public ToStringGenerator field(String str, Object obj) {
            StringBuilder sb2 = this.builder;
            sb2.append(str);
            sb2.append('=');
            sb2.append(obj);
            sb2.append(GLUE);
            return this;
        }
    }

    public static /* synthetic */ Validation a(int i10) {
        return VALIDATION_VALUES[i10];
    }

    public static Set<Validation> createValidationSet(int[] iArr) {
        return Collections.unmodifiableSet((Set) Arrays.stream(iArr).mapToObj(new a()).collect(Collectors.toCollection(new b())));
    }

    public static String[] flattenStringMap(Map<String, String> map) {
        String[] strArr = new String[(map.size() * 2)];
        int i10 = 0;
        for (Map.Entry next : map.entrySet()) {
            int i11 = i10 + 1;
            strArr[i10] = (String) next.getKey();
            i10 += 2;
            strArr[i11] = (String) next.getValue();
        }
        return strArr;
    }

    public static ToStringGenerator toStringGenerator(Object obj) {
        return new ToStringGenerator(obj.getClass());
    }

    public static <T> List<T> wrapArray(T[] tArr) {
        return Collections.unmodifiableList(Arrays.asList(tArr));
    }
}
