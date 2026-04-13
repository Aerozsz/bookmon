package org.apache.http.impl.auth;

import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.apache.http.Consts;

/* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
final class NTLMEngineImpl implements NTLMEngine {
    /* access modifiers changed from: private */
    public static final Charset DEFAULT_CHARSET = Consts.ASCII;
    static final int FLAG_DOMAIN_PRESENT = 4096;
    static final int FLAG_REQUEST_128BIT_KEY_EXCH = 536870912;
    static final int FLAG_REQUEST_56BIT_ENCRYPTION = Integer.MIN_VALUE;
    static final int FLAG_REQUEST_ALWAYS_SIGN = 32768;
    static final int FLAG_REQUEST_EXPLICIT_KEY_EXCH = 1073741824;
    static final int FLAG_REQUEST_LAN_MANAGER_KEY = 128;
    static final int FLAG_REQUEST_NTLM2_SESSION = 524288;
    static final int FLAG_REQUEST_NTLMv1 = 512;
    static final int FLAG_REQUEST_OEM_ENCODING = 2;
    static final int FLAG_REQUEST_SEAL = 32;
    static final int FLAG_REQUEST_SIGN = 16;
    static final int FLAG_REQUEST_TARGET = 4;
    static final int FLAG_REQUEST_UNICODE_ENCODING = 1;
    static final int FLAG_REQUEST_VERSION = 33554432;
    static final int FLAG_TARGETINFO_PRESENT = 8388608;
    static final int FLAG_WORKSTATION_PRESENT = 8192;
    /* access modifiers changed from: private */
    public static final byte[] MAGIC_TLS_SERVER_ENDPOINT = "tls-server-end-point:".getBytes(Consts.ASCII);
    static final int MSV_AV_CHANNEL_BINDINGS = 10;
    static final int MSV_AV_DNS_COMPUTER_NAME = 3;
    static final int MSV_AV_DNS_DOMAIN_NAME = 4;
    static final int MSV_AV_DNS_TREE_NAME = 5;
    static final int MSV_AV_EOL = 0;
    static final int MSV_AV_FLAGS = 6;
    static final int MSV_AV_FLAGS_ACCOUNT_AUTH_CONSTAINED = 1;
    static final int MSV_AV_FLAGS_MIC = 2;
    static final int MSV_AV_FLAGS_UNTRUSTED_TARGET_SPN = 4;
    static final int MSV_AV_NB_COMPUTER_NAME = 1;
    static final int MSV_AV_NB_DOMAIN_NAME = 2;
    static final int MSV_AV_SINGLE_HOST = 8;
    static final int MSV_AV_TARGET_NAME = 9;
    static final int MSV_AV_TIMESTAMP = 7;
    /* access modifiers changed from: private */
    public static final SecureRandom RND_GEN;
    /* access modifiers changed from: private */
    public static final byte[] SEAL_MAGIC_CLIENT = getNullTerminatedAsciiString("session key to client-to-server sealing key magic constant");
    /* access modifiers changed from: private */
    public static final byte[] SEAL_MAGIC_SERVER = getNullTerminatedAsciiString("session key to server-to-client sealing key magic constant");
    /* access modifiers changed from: private */
    public static final byte[] SIGNATURE = getNullTerminatedAsciiString("NTLMSSP");
    /* access modifiers changed from: private */
    public static final byte[] SIGN_MAGIC_CLIENT = getNullTerminatedAsciiString("session key to client-to-server signing key magic constant");
    /* access modifiers changed from: private */
    public static final byte[] SIGN_MAGIC_SERVER = getNullTerminatedAsciiString("session key to server-to-client signing key magic constant");
    private static final String TYPE_1_MESSAGE = new Type1Message().getResponse();
    /* access modifiers changed from: private */
    public static final Charset UNICODE_LITTLE_UNMARKED = Charset.forName("UnicodeLittleUnmarked");

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    protected static class CipherGen {
        protected final byte[] challenge;
        protected byte[] clientChallenge;
        protected byte[] clientChallenge2;
        protected final long currentTime;
        protected final String domain;
        protected byte[] lanManagerSessionKey;
        protected byte[] lm2SessionResponse;
        protected byte[] lmHash;
        protected byte[] lmResponse;
        protected byte[] lmUserSessionKey;
        protected byte[] lmv2Hash;
        protected byte[] lmv2Response;
        protected byte[] ntlm2SessionResponse;
        protected byte[] ntlm2SessionResponseUserSessionKey;
        protected byte[] ntlmHash;
        protected byte[] ntlmResponse;
        protected byte[] ntlmUserSessionKey;
        protected byte[] ntlmv2Blob;
        protected byte[] ntlmv2Hash;
        protected byte[] ntlmv2Response;
        protected byte[] ntlmv2UserSessionKey;
        protected final String password;
        protected final Random random;
        protected byte[] secondaryKey;
        protected final String target;
        protected final byte[] targetInformation;
        protected byte[] timestamp;
        protected final String user;

        @Deprecated
        public CipherGen(String str, String str2, String str3, byte[] bArr, String str4, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
            this(NTLMEngineImpl.RND_GEN, System.currentTimeMillis(), str, str2, str3, bArr, str4, bArr2, bArr3, bArr4, bArr5, bArr6);
        }

        public byte[] getClientChallenge() {
            if (this.clientChallenge == null) {
                this.clientChallenge = NTLMEngineImpl.makeRandomChallenge(this.random);
            }
            return this.clientChallenge;
        }

        public byte[] getClientChallenge2() {
            if (this.clientChallenge2 == null) {
                this.clientChallenge2 = NTLMEngineImpl.makeRandomChallenge(this.random);
            }
            return this.clientChallenge2;
        }

        public byte[] getLM2SessionResponse() {
            if (this.lm2SessionResponse == null) {
                byte[] clientChallenge3 = getClientChallenge();
                byte[] bArr = new byte[24];
                this.lm2SessionResponse = bArr;
                System.arraycopy(clientChallenge3, 0, bArr, 0, clientChallenge3.length);
                byte[] bArr2 = this.lm2SessionResponse;
                Arrays.fill(bArr2, clientChallenge3.length, bArr2.length, (byte) 0);
            }
            return this.lm2SessionResponse;
        }

        public byte[] getLMHash() {
            if (this.lmHash == null) {
                this.lmHash = NTLMEngineImpl.lmHash(this.password);
            }
            return this.lmHash;
        }

        public byte[] getLMResponse() {
            if (this.lmResponse == null) {
                this.lmResponse = NTLMEngineImpl.lmResponse(getLMHash(), this.challenge);
            }
            return this.lmResponse;
        }

        public byte[] getLMUserSessionKey() {
            if (this.lmUserSessionKey == null) {
                this.lmUserSessionKey = new byte[16];
                System.arraycopy(getLMHash(), 0, this.lmUserSessionKey, 0, 8);
                Arrays.fill(this.lmUserSessionKey, 8, 16, (byte) 0);
            }
            return this.lmUserSessionKey;
        }

        public byte[] getLMv2Hash() {
            if (this.lmv2Hash == null) {
                this.lmv2Hash = NTLMEngineImpl.lmv2Hash(this.domain, this.user, getNTLMHash());
            }
            return this.lmv2Hash;
        }

        public byte[] getLMv2Response() {
            if (this.lmv2Response == null) {
                this.lmv2Response = NTLMEngineImpl.lmv2Response(getLMv2Hash(), this.challenge, getClientChallenge());
            }
            return this.lmv2Response;
        }

        public byte[] getLanManagerSessionKey() {
            if (this.lanManagerSessionKey == null) {
                try {
                    byte[] bArr = new byte[14];
                    System.arraycopy(getLMHash(), 0, bArr, 0, 8);
                    Arrays.fill(bArr, 8, 14, (byte) -67);
                    Key access$1000 = NTLMEngineImpl.createDESKey(bArr, 0);
                    Key access$10002 = NTLMEngineImpl.createDESKey(bArr, 7);
                    byte[] bArr2 = new byte[8];
                    System.arraycopy(getLMResponse(), 0, bArr2, 0, 8);
                    Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
                    instance.init(1, access$1000);
                    byte[] doFinal = instance.doFinal(bArr2);
                    Cipher instance2 = Cipher.getInstance("DES/ECB/NoPadding");
                    instance2.init(1, access$10002);
                    byte[] doFinal2 = instance2.doFinal(bArr2);
                    byte[] bArr3 = new byte[16];
                    this.lanManagerSessionKey = bArr3;
                    System.arraycopy(doFinal, 0, bArr3, 0, doFinal.length);
                    System.arraycopy(doFinal2, 0, this.lanManagerSessionKey, doFinal.length, doFinal2.length);
                } catch (Exception e10) {
                    throw new NTLMEngineException(e10.getMessage(), e10);
                }
            }
            return this.lanManagerSessionKey;
        }

        public byte[] getNTLM2SessionResponse() {
            if (this.ntlm2SessionResponse == null) {
                this.ntlm2SessionResponse = NTLMEngineImpl.ntlm2SessionResponse(getNTLMHash(), this.challenge, getClientChallenge());
            }
            return this.ntlm2SessionResponse;
        }

        public byte[] getNTLM2SessionResponseUserSessionKey() {
            if (this.ntlm2SessionResponseUserSessionKey == null) {
                byte[] lM2SessionResponse = getLM2SessionResponse();
                byte[] bArr = this.challenge;
                byte[] bArr2 = new byte[(bArr.length + lM2SessionResponse.length)];
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                System.arraycopy(lM2SessionResponse, 0, bArr2, this.challenge.length, lM2SessionResponse.length);
                this.ntlm2SessionResponseUserSessionKey = NTLMEngineImpl.hmacMD5(bArr2, getNTLMUserSessionKey());
            }
            return this.ntlm2SessionResponseUserSessionKey;
        }

        public byte[] getNTLMHash() {
            if (this.ntlmHash == null) {
                this.ntlmHash = NTLMEngineImpl.ntlmHash(this.password);
            }
            return this.ntlmHash;
        }

        public byte[] getNTLMResponse() {
            if (this.ntlmResponse == null) {
                this.ntlmResponse = NTLMEngineImpl.lmResponse(getNTLMHash(), this.challenge);
            }
            return this.ntlmResponse;
        }

        public byte[] getNTLMUserSessionKey() {
            if (this.ntlmUserSessionKey == null) {
                MD4 md4 = new MD4();
                md4.update(getNTLMHash());
                this.ntlmUserSessionKey = md4.getOutput();
            }
            return this.ntlmUserSessionKey;
        }

        public byte[] getNTLMv2Blob() {
            if (this.ntlmv2Blob == null) {
                this.ntlmv2Blob = NTLMEngineImpl.createBlob(getClientChallenge2(), this.targetInformation, getTimestamp());
            }
            return this.ntlmv2Blob;
        }

        public byte[] getNTLMv2Hash() {
            if (this.ntlmv2Hash == null) {
                this.ntlmv2Hash = NTLMEngineImpl.ntlmv2Hash(this.domain, this.user, getNTLMHash());
            }
            return this.ntlmv2Hash;
        }

        public byte[] getNTLMv2Response() {
            if (this.ntlmv2Response == null) {
                this.ntlmv2Response = NTLMEngineImpl.lmv2Response(getNTLMv2Hash(), this.challenge, getNTLMv2Blob());
            }
            return this.ntlmv2Response;
        }

        public byte[] getNTLMv2UserSessionKey() {
            if (this.ntlmv2UserSessionKey == null) {
                byte[] nTLMv2Hash = getNTLMv2Hash();
                byte[] bArr = new byte[16];
                System.arraycopy(getNTLMv2Response(), 0, bArr, 0, 16);
                this.ntlmv2UserSessionKey = NTLMEngineImpl.hmacMD5(bArr, nTLMv2Hash);
            }
            return this.ntlmv2UserSessionKey;
        }

        public byte[] getSecondaryKey() {
            if (this.secondaryKey == null) {
                this.secondaryKey = NTLMEngineImpl.makeSecondaryKey(this.random);
            }
            return this.secondaryKey;
        }

        public byte[] getTimestamp() {
            if (this.timestamp == null) {
                long j10 = (this.currentTime + 11644473600000L) * 10000;
                this.timestamp = new byte[8];
                for (int i10 = 0; i10 < 8; i10++) {
                    this.timestamp[i10] = (byte) ((int) j10);
                    j10 >>>= 8;
                }
            }
            return this.timestamp;
        }

        public CipherGen(Random random2, long j10, String str, String str2, String str3, byte[] bArr, String str4, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[] bArr6) {
            this.lmHash = null;
            this.lmResponse = null;
            this.ntlmHash = null;
            this.ntlmResponse = null;
            this.ntlmv2Hash = null;
            this.lmv2Hash = null;
            this.lmv2Response = null;
            this.ntlmv2Blob = null;
            this.ntlmv2Response = null;
            this.ntlm2SessionResponse = null;
            this.lm2SessionResponse = null;
            this.lmUserSessionKey = null;
            this.ntlmUserSessionKey = null;
            this.ntlmv2UserSessionKey = null;
            this.ntlm2SessionResponseUserSessionKey = null;
            this.lanManagerSessionKey = null;
            this.random = random2;
            this.currentTime = j10;
            this.domain = str;
            this.target = str4;
            this.user = str2;
            this.password = str3;
            this.challenge = bArr;
            this.targetInformation = bArr2;
            this.clientChallenge = bArr3;
            this.clientChallenge2 = bArr4;
            this.secondaryKey = bArr5;
            this.timestamp = bArr6;
        }

        @Deprecated
        public CipherGen(String str, String str2, String str3, byte[] bArr, String str4, byte[] bArr2) {
            this(NTLMEngineImpl.RND_GEN, System.currentTimeMillis(), str, str2, str3, bArr, str4, bArr2);
        }

        public CipherGen(Random random2, long j10, String str, String str2, String str3, byte[] bArr, String str4, byte[] bArr2) {
            this(random2, j10, str, str2, str3, bArr, str4, bArr2, (byte[]) null, (byte[]) null, (byte[]) null, (byte[]) null);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    static class HMACMD5 {
        protected final byte[] ipad = new byte[64];
        protected final MessageDigest md5;
        protected final byte[] opad = new byte[64];

        HMACMD5(byte[] bArr) {
            MessageDigest md52 = NTLMEngineImpl.getMD5();
            this.md5 = md52;
            int length = bArr.length;
            if (length > 64) {
                md52.update(bArr);
                bArr = md52.digest();
                length = bArr.length;
            }
            int i10 = 0;
            while (i10 < length) {
                this.ipad[i10] = (byte) (54 ^ bArr[i10]);
                this.opad[i10] = (byte) (92 ^ bArr[i10]);
                i10++;
            }
            while (i10 < 64) {
                this.ipad[i10] = 54;
                this.opad[i10] = 92;
                i10++;
            }
            this.md5.reset();
            this.md5.update(this.ipad);
        }

        /* access modifiers changed from: package-private */
        public byte[] getOutput() {
            byte[] digest = this.md5.digest();
            this.md5.update(this.opad);
            return this.md5.digest(digest);
        }

        /* access modifiers changed from: package-private */
        public void update(byte[] bArr) {
            this.md5.update(bArr);
        }

        /* access modifiers changed from: package-private */
        public void update(byte[] bArr, int i10, int i11) {
            this.md5.update(bArr, i10, i11);
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    static class Handle {
        private final byte[] exportedSessionKey;
        private final boolean isConnection;
        final Mode mode;
        private final Cipher rc4;
        private byte[] sealingKey;
        int sequenceNumber = 0;
        private byte[] signingKey;

        Handle(byte[] bArr, Mode mode2, boolean z10) {
            this.exportedSessionKey = bArr;
            this.isConnection = z10;
            this.mode = mode2;
            try {
                MessageDigest md5 = NTLMEngineImpl.getMD5();
                MessageDigest md52 = NTLMEngineImpl.getMD5();
                md5.update(bArr);
                md52.update(bArr);
                if (mode2 == Mode.CLIENT) {
                    md5.update(NTLMEngineImpl.SIGN_MAGIC_CLIENT);
                    md52.update(NTLMEngineImpl.SEAL_MAGIC_CLIENT);
                } else {
                    md5.update(NTLMEngineImpl.SIGN_MAGIC_SERVER);
                    md52.update(NTLMEngineImpl.SEAL_MAGIC_SERVER);
                }
                this.signingKey = md5.digest();
                this.sealingKey = md52.digest();
                this.rc4 = initCipher();
            } catch (Exception e10) {
                throw new NTLMEngineException(e10.getMessage(), e10);
            }
        }

        private void advanceMessageSequence() {
            if (!this.isConnection) {
                MessageDigest md5 = NTLMEngineImpl.getMD5();
                md5.update(this.sealingKey);
                byte[] bArr = new byte[4];
                NTLMEngineImpl.writeULong(bArr, this.sequenceNumber, 0);
                md5.update(bArr);
                this.sealingKey = md5.digest();
                initCipher();
            }
            this.sequenceNumber++;
        }

        private byte[] computeSignature(byte[] bArr) {
            byte[] bArr2 = new byte[16];
            bArr2[0] = 1;
            bArr2[1] = 0;
            bArr2[2] = 0;
            bArr2[3] = 0;
            HMACMD5 hmacmd5 = new HMACMD5(this.signingKey);
            hmacmd5.update(NTLMEngineImpl.encodeLong(this.sequenceNumber));
            hmacmd5.update(bArr);
            byte[] bArr3 = new byte[8];
            System.arraycopy(hmacmd5.getOutput(), 0, bArr3, 0, 8);
            System.arraycopy(encrypt(bArr3), 0, bArr2, 4, 8);
            NTLMEngineImpl.encodeLong(bArr2, 12, this.sequenceNumber);
            return bArr2;
        }

        private byte[] decrypt(byte[] bArr) {
            return this.rc4.update(bArr);
        }

        private byte[] encrypt(byte[] bArr) {
            return this.rc4.update(bArr);
        }

        private Cipher initCipher() {
            try {
                Cipher instance = Cipher.getInstance("RC4");
                if (this.mode == Mode.CLIENT) {
                    instance.init(1, new SecretKeySpec(this.sealingKey, "RC4"));
                    return instance;
                }
                instance.init(2, new SecretKeySpec(this.sealingKey, "RC4"));
                return instance;
            } catch (Exception e10) {
                throw new NTLMEngineException(e10.getMessage(), e10);
            }
        }

        private boolean validateSignature(byte[] bArr, byte[] bArr2) {
            return Arrays.equals(bArr, computeSignature(bArr2));
        }

        public byte[] decryptAndVerifySignedMessage(byte[] bArr) {
            byte[] bArr2 = new byte[16];
            System.arraycopy(bArr, 0, bArr2, 0, 16);
            int length = bArr.length - 16;
            byte[] bArr3 = new byte[length];
            System.arraycopy(bArr, 16, bArr3, 0, length);
            byte[] decrypt = decrypt(bArr3);
            if (validateSignature(bArr2, decrypt)) {
                advanceMessageSequence();
                return decrypt;
            }
            throw new NTLMEngineException("Wrong signature");
        }

        public byte[] getSealingKey() {
            return this.sealingKey;
        }

        public byte[] getSigningKey() {
            return this.signingKey;
        }

        public byte[] signAndEncryptMessage(byte[] bArr) {
            byte[] encrypt = encrypt(bArr);
            byte[] computeSignature = computeSignature(bArr);
            byte[] bArr2 = new byte[(computeSignature.length + encrypt.length)];
            System.arraycopy(computeSignature, 0, bArr2, 0, computeSignature.length);
            System.arraycopy(encrypt, 0, bArr2, computeSignature.length, encrypt.length);
            advanceMessageSequence();
            return bArr2;
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    static class MD4 {
        protected int A = 1732584193;
        protected int B = -271733879;
        protected int C = -1732584194;
        protected int D = 271733878;
        protected long count = 0;
        protected final byte[] dataBuffer = new byte[64];

        MD4() {
        }

        /* access modifiers changed from: package-private */
        public byte[] getOutput() {
            int i10;
            int i11 = (int) (this.count & 63);
            if (i11 < 56) {
                i10 = 56 - i11;
            } else {
                i10 = 120 - i11;
            }
            byte[] bArr = new byte[(i10 + 8)];
            bArr[0] = Byte.MIN_VALUE;
            for (int i12 = 0; i12 < 8; i12++) {
                bArr[i10 + i12] = (byte) ((int) ((this.count * 8) >>> (i12 * 8)));
            }
            update(bArr);
            byte[] bArr2 = new byte[16];
            NTLMEngineImpl.writeULong(bArr2, this.A, 0);
            NTLMEngineImpl.writeULong(bArr2, this.B, 4);
            NTLMEngineImpl.writeULong(bArr2, this.C, 8);
            NTLMEngineImpl.writeULong(bArr2, this.D, 12);
            return bArr2;
        }

        /* access modifiers changed from: protected */
        public void processBuffer() {
            int[] iArr = new int[16];
            for (int i10 = 0; i10 < 16; i10++) {
                byte[] bArr = this.dataBuffer;
                int i11 = i10 * 4;
                iArr[i10] = (bArr[i11] & 255) + ((bArr[i11 + 1] & 255) << 8) + ((bArr[i11 + 2] & 255) << 16) + ((bArr[i11 + 3] & 255) << 24);
            }
            int i12 = this.A;
            int i13 = this.B;
            int i14 = this.C;
            int i15 = this.D;
            round1(iArr);
            round2(iArr);
            round3(iArr);
            this.A += i12;
            this.B += i13;
            this.C += i14;
            this.D += i15;
        }

        /* access modifiers changed from: protected */
        public void round1(int[] iArr) {
            int rotintlft = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + iArr[0], 3);
            this.A = rotintlft;
            int rotintlft2 = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(rotintlft, this.B, this.C) + iArr[1], 7);
            this.D = rotintlft2;
            int rotintlft3 = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(rotintlft2, this.A, this.B) + iArr[2], 11);
            this.C = rotintlft3;
            int rotintlft4 = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(rotintlft3, this.D, this.A) + iArr[3], 19);
            this.B = rotintlft4;
            int rotintlft5 = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(rotintlft4, this.C, this.D) + iArr[4], 3);
            this.A = rotintlft5;
            int rotintlft6 = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(rotintlft5, this.B, this.C) + iArr[5], 7);
            this.D = rotintlft6;
            int rotintlft7 = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(rotintlft6, this.A, this.B) + iArr[6], 11);
            this.C = rotintlft7;
            int rotintlft8 = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(rotintlft7, this.D, this.A) + iArr[7], 19);
            this.B = rotintlft8;
            int rotintlft9 = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(rotintlft8, this.C, this.D) + iArr[8], 3);
            this.A = rotintlft9;
            int rotintlft10 = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(rotintlft9, this.B, this.C) + iArr[9], 7);
            this.D = rotintlft10;
            int rotintlft11 = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(rotintlft10, this.A, this.B) + iArr[10], 11);
            this.C = rotintlft11;
            int rotintlft12 = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(rotintlft11, this.D, this.A) + iArr[11], 19);
            this.B = rotintlft12;
            int rotintlft13 = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(rotintlft12, this.C, this.D) + iArr[12], 3);
            this.A = rotintlft13;
            int rotintlft14 = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(rotintlft13, this.B, this.C) + iArr[13], 7);
            this.D = rotintlft14;
            int rotintlft15 = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(rotintlft14, this.A, this.B) + iArr[14], 11);
            this.C = rotintlft15;
            this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(rotintlft15, this.D, this.A) + iArr[15], 19);
        }

        /* access modifiers changed from: protected */
        public void round2(int[] iArr) {
            int rotintlft = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + iArr[0] + 1518500249, 3);
            this.A = rotintlft;
            int rotintlft2 = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.G(rotintlft, this.B, this.C) + iArr[4] + 1518500249, 5);
            this.D = rotintlft2;
            int rotintlft3 = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.G(rotintlft2, this.A, this.B) + iArr[8] + 1518500249, 9);
            this.C = rotintlft3;
            int rotintlft4 = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.G(rotintlft3, this.D, this.A) + iArr[12] + 1518500249, 13);
            this.B = rotintlft4;
            int rotintlft5 = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.G(rotintlft4, this.C, this.D) + iArr[1] + 1518500249, 3);
            this.A = rotintlft5;
            int rotintlft6 = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.G(rotintlft5, this.B, this.C) + iArr[5] + 1518500249, 5);
            this.D = rotintlft6;
            int rotintlft7 = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.G(rotintlft6, this.A, this.B) + iArr[9] + 1518500249, 9);
            this.C = rotintlft7;
            int rotintlft8 = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.G(rotintlft7, this.D, this.A) + iArr[13] + 1518500249, 13);
            this.B = rotintlft8;
            int rotintlft9 = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.G(rotintlft8, this.C, this.D) + iArr[2] + 1518500249, 3);
            this.A = rotintlft9;
            int rotintlft10 = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.G(rotintlft9, this.B, this.C) + iArr[6] + 1518500249, 5);
            this.D = rotintlft10;
            int rotintlft11 = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.G(rotintlft10, this.A, this.B) + iArr[10] + 1518500249, 9);
            this.C = rotintlft11;
            int rotintlft12 = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.G(rotintlft11, this.D, this.A) + iArr[14] + 1518500249, 13);
            this.B = rotintlft12;
            int rotintlft13 = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.G(rotintlft12, this.C, this.D) + iArr[3] + 1518500249, 3);
            this.A = rotintlft13;
            int rotintlft14 = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.G(rotintlft13, this.B, this.C) + iArr[7] + 1518500249, 5);
            this.D = rotintlft14;
            int rotintlft15 = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.G(rotintlft14, this.A, this.B) + iArr[11] + 1518500249, 9);
            this.C = rotintlft15;
            this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.G(rotintlft15, this.D, this.A) + iArr[15] + 1518500249, 13);
        }

        /* access modifiers changed from: protected */
        public void round3(int[] iArr) {
            int rotintlft = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + iArr[0] + 1859775393, 3);
            this.A = rotintlft;
            int rotintlft2 = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.H(rotintlft, this.B, this.C) + iArr[8] + 1859775393, 9);
            this.D = rotintlft2;
            int rotintlft3 = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.H(rotintlft2, this.A, this.B) + iArr[4] + 1859775393, 11);
            this.C = rotintlft3;
            int rotintlft4 = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.H(rotintlft3, this.D, this.A) + iArr[12] + 1859775393, 15);
            this.B = rotintlft4;
            int rotintlft5 = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.H(rotintlft4, this.C, this.D) + iArr[2] + 1859775393, 3);
            this.A = rotintlft5;
            int rotintlft6 = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.H(rotintlft5, this.B, this.C) + iArr[10] + 1859775393, 9);
            this.D = rotintlft6;
            int rotintlft7 = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.H(rotintlft6, this.A, this.B) + iArr[6] + 1859775393, 11);
            this.C = rotintlft7;
            int rotintlft8 = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.H(rotintlft7, this.D, this.A) + iArr[14] + 1859775393, 15);
            this.B = rotintlft8;
            int rotintlft9 = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.H(rotintlft8, this.C, this.D) + iArr[1] + 1859775393, 3);
            this.A = rotintlft9;
            int rotintlft10 = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.H(rotintlft9, this.B, this.C) + iArr[9] + 1859775393, 9);
            this.D = rotintlft10;
            int rotintlft11 = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.H(rotintlft10, this.A, this.B) + iArr[5] + 1859775393, 11);
            this.C = rotintlft11;
            int rotintlft12 = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.H(rotintlft11, this.D, this.A) + iArr[13] + 1859775393, 15);
            this.B = rotintlft12;
            int rotintlft13 = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.H(rotintlft12, this.C, this.D) + iArr[3] + 1859775393, 3);
            this.A = rotintlft13;
            int rotintlft14 = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.H(rotintlft13, this.B, this.C) + iArr[11] + 1859775393, 9);
            this.D = rotintlft14;
            int rotintlft15 = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.H(rotintlft14, this.A, this.B) + iArr[7] + 1859775393, 11);
            this.C = rotintlft15;
            this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.H(rotintlft15, this.D, this.A) + iArr[15] + 1859775393, 15);
        }

        /* access modifiers changed from: package-private */
        public void update(byte[] bArr) {
            byte[] bArr2;
            int i10 = (int) (this.count & 63);
            int i11 = 0;
            while (true) {
                int length = (bArr.length - i11) + i10;
                bArr2 = this.dataBuffer;
                if (length < bArr2.length) {
                    break;
                }
                int length2 = bArr2.length - i10;
                System.arraycopy(bArr, i11, bArr2, i10, length2);
                this.count += (long) length2;
                i11 += length2;
                processBuffer();
                i10 = 0;
            }
            if (i11 < bArr.length) {
                int length3 = bArr.length - i11;
                System.arraycopy(bArr, i11, bArr2, i10, length3);
                this.count += (long) length3;
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    enum Mode {
        CLIENT,
        SERVER
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    static class Type1Message extends NTLMMessage {
        private final byte[] domainBytes;
        private final int flags;
        private final byte[] hostBytes;

        Type1Message(String str, String str2) {
            this(str, str2, (Integer) null);
        }

        private int getDefaultFlags() {
            return -1576500735;
        }

        /* access modifiers changed from: protected */
        public void buildMessage() {
            int i10;
            byte[] bArr = this.domainBytes;
            int i11 = 0;
            if (bArr != null) {
                i10 = bArr.length;
            } else {
                i10 = 0;
            }
            byte[] bArr2 = this.hostBytes;
            if (bArr2 != null) {
                i11 = bArr2.length;
            }
            prepareResponse(i11 + 40 + i10, 1);
            addULong(this.flags);
            addUShort(i10);
            addUShort(i10);
            addULong(i11 + 40);
            addUShort(i11);
            addUShort(i11);
            addULong(40);
            addUShort(261);
            addULong(2600);
            addUShort(3840);
            byte[] bArr3 = this.hostBytes;
            if (bArr3 != null) {
                addBytes(bArr3);
            }
            byte[] bArr4 = this.domainBytes;
            if (bArr4 != null) {
                addBytes(bArr4);
            }
        }

        Type1Message(String str, String str2, Integer num) {
            this.flags = num == null ? getDefaultFlags() : num.intValue();
            String access$2200 = NTLMEngineImpl.convertHost(str2);
            String access$2300 = NTLMEngineImpl.convertDomain(str);
            byte[] bArr = null;
            this.hostBytes = access$2200 != null ? access$2200.getBytes(NTLMEngineImpl.UNICODE_LITTLE_UNMARKED) : null;
            this.domainBytes = access$2300 != null ? access$2300.toUpperCase(Locale.ROOT).getBytes(NTLMEngineImpl.UNICODE_LITTLE_UNMARKED) : bArr;
        }

        Type1Message() {
            this.hostBytes = null;
            this.domainBytes = null;
            this.flags = getDefaultFlags();
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    static class Type2Message extends NTLMMessage {
        protected final byte[] challenge;
        protected final int flags;
        protected String target;
        protected byte[] targetInfo;

        Type2Message(String str) {
            this(Base64.decodeBase64(str.getBytes(NTLMEngineImpl.DEFAULT_CHARSET)));
        }

        /* access modifiers changed from: package-private */
        public byte[] getChallenge() {
            return this.challenge;
        }

        /* access modifiers changed from: package-private */
        public int getFlags() {
            return this.flags;
        }

        /* access modifiers changed from: package-private */
        public String getTarget() {
            return this.target;
        }

        /* access modifiers changed from: package-private */
        public byte[] getTargetInfo() {
            return this.targetInfo;
        }

        Type2Message(byte[] bArr) {
            super(bArr, 2);
            byte[] bArr2 = new byte[8];
            this.challenge = bArr2;
            readBytes(bArr2, 24);
            int readULong = readULong(20);
            this.flags = readULong;
            this.target = null;
            if (getMessageLength() >= 20) {
                byte[] readSecurityBuffer = readSecurityBuffer(12);
                if (readSecurityBuffer.length != 0) {
                    this.target = new String(readSecurityBuffer, NTLMEngineImpl.getCharset(readULong));
                }
            }
            this.targetInfo = null;
            if (getMessageLength() >= 48) {
                byte[] readSecurityBuffer2 = readSecurityBuffer(40);
                if (readSecurityBuffer2.length != 0) {
                    this.targetInfo = readSecurityBuffer2;
                }
            }
        }
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    static class Type3Message extends NTLMMessage {
        protected final boolean computeMic;
        protected final byte[] domainBytes;
        protected final byte[] exportedSessionKey;
        protected final byte[] hostBytes;
        protected byte[] lmResp;
        protected byte[] ntResp;
        protected final byte[] sessionKey;
        protected final byte[] type1Message;
        protected final int type2Flags;
        protected final byte[] type2Message;
        protected final byte[] userBytes;

        Type3Message(String str, String str2, String str3, String str4, byte[] bArr, int i10, String str5, byte[] bArr2) {
            this(str, str2, str3, str4, bArr, i10, str5, bArr2, (Certificate) null, (byte[]) null, (byte[]) null);
        }

        private byte[] addGssMicAvsToTargetInfo(byte[] bArr, Certificate certificate) {
            byte[] bArr2 = new byte[(bArr.length + 28)];
            int length = bArr.length;
            int i10 = length - 4;
            System.arraycopy(bArr, 0, bArr2, 0, i10);
            NTLMEngineImpl.writeUShort(bArr2, 6, i10);
            NTLMEngineImpl.writeUShort(bArr2, 4, length - 2);
            NTLMEngineImpl.writeULong(bArr2, 2, length);
            NTLMEngineImpl.writeUShort(bArr2, 10, length + 4);
            NTLMEngineImpl.writeUShort(bArr2, 16, length + 6);
            try {
                byte[] digest = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256).digest(certificate.getEncoded());
                byte[] bArr3 = new byte[(NTLMEngineImpl.MAGIC_TLS_SERVER_ENDPOINT.length + 20 + digest.length)];
                NTLMEngineImpl.writeULong(bArr3, 53, 16);
                System.arraycopy(NTLMEngineImpl.MAGIC_TLS_SERVER_ENDPOINT, 0, bArr3, 20, NTLMEngineImpl.MAGIC_TLS_SERVER_ENDPOINT.length);
                System.arraycopy(digest, 0, bArr3, NTLMEngineImpl.MAGIC_TLS_SERVER_ENDPOINT.length + 20, digest.length);
                System.arraycopy(NTLMEngineImpl.getMD5().digest(bArr3), 0, bArr2, length + 8, 16);
                return bArr2;
            } catch (CertificateEncodingException e10) {
                throw new NTLMEngineException(e10.getMessage(), e10);
            } catch (NoSuchAlgorithmException e11) {
                throw new NTLMEngineException(e11.getMessage(), e11);
            }
        }

        /* access modifiers changed from: protected */
        public void buildMessage() {
            int i10;
            int i11;
            int i12;
            int i13;
            int i14;
            int length = this.ntResp.length;
            int length2 = this.lmResp.length;
            byte[] bArr = this.domainBytes;
            if (bArr != null) {
                i10 = bArr.length;
            } else {
                i10 = 0;
            }
            byte[] bArr2 = this.hostBytes;
            if (bArr2 != null) {
                i11 = bArr2.length;
            } else {
                i11 = 0;
            }
            int length3 = this.userBytes.length;
            byte[] bArr3 = this.sessionKey;
            if (bArr3 != null) {
                i12 = bArr3.length;
            } else {
                i12 = 0;
            }
            if (this.computeMic) {
                i13 = 16;
            } else {
                i13 = 0;
            }
            int i15 = i13 + 72;
            int i16 = i15 + length2;
            int i17 = i16 + length;
            int i18 = i17 + i10;
            int i19 = i18 + length3;
            int i20 = i19 + i11;
            prepareResponse(i20 + i12, 3);
            addUShort(length2);
            addUShort(length2);
            addULong(i15);
            addUShort(length);
            addUShort(length);
            addULong(i16);
            addUShort(i10);
            addUShort(i10);
            addULong(i17);
            addUShort(length3);
            addUShort(length3);
            addULong(i18);
            addUShort(i11);
            addUShort(i11);
            addULong(i19);
            addUShort(i12);
            addUShort(i12);
            addULong(i20);
            addULong(this.type2Flags);
            addUShort(261);
            addULong(2600);
            addUShort(3840);
            if (this.computeMic) {
                i14 = this.currentOutputPosition;
                this.currentOutputPosition = i14 + 16;
            } else {
                i14 = -1;
            }
            addBytes(this.lmResp);
            addBytes(this.ntResp);
            addBytes(this.domainBytes);
            addBytes(this.userBytes);
            addBytes(this.hostBytes);
            byte[] bArr4 = this.sessionKey;
            if (bArr4 != null) {
                addBytes(bArr4);
            }
            if (this.computeMic) {
                HMACMD5 hmacmd5 = new HMACMD5(this.exportedSessionKey);
                hmacmd5.update(this.type1Message);
                hmacmd5.update(this.type2Message);
                hmacmd5.update(this.messageContents);
                byte[] output = hmacmd5.getOutput();
                System.arraycopy(output, 0, this.messageContents, i14, output.length);
            }
        }

        public byte[] getEncryptedRandomSessionKey() {
            return this.sessionKey;
        }

        public byte[] getExportedSessionKey() {
            return this.exportedSessionKey;
        }

        Type3Message(Random random, long j10, String str, String str2, String str3, String str4, byte[] bArr, int i10, String str5, byte[] bArr2) {
            this(random, j10, str, str2, str3, str4, bArr, i10, str5, bArr2, (Certificate) null, (byte[]) null, (byte[]) null);
        }

        Type3Message(String str, String str2, String str3, String str4, byte[] bArr, int i10, String str5, byte[] bArr2, Certificate certificate, byte[] bArr3, byte[] bArr4) {
            this(NTLMEngineImpl.RND_GEN, System.currentTimeMillis(), str, str2, str3, str4, bArr, i10, str5, bArr2, certificate, bArr3, bArr4);
        }

        Type3Message(Random random, long j10, String str, String str2, String str3, String str4, byte[] bArr, int i10, String str5, byte[] bArr2, Certificate certificate, byte[] bArr3, byte[] bArr4) {
            byte[] bArr5;
            byte[] bArr6;
            int i11 = i10;
            byte[] bArr7 = bArr2;
            Certificate certificate2 = certificate;
            if (random != null) {
                this.type2Flags = i11;
                this.type1Message = bArr3;
                this.type2Message = bArr4;
                String access$2200 = NTLMEngineImpl.convertHost(str2);
                String access$2300 = NTLMEngineImpl.convertDomain(str);
                if (certificate2 != null) {
                    byte[] addGssMicAvsToTargetInfo = addGssMicAvsToTargetInfo(bArr7, certificate2);
                    this.computeMic = true;
                    bArr5 = addGssMicAvsToTargetInfo;
                } else {
                    this.computeMic = false;
                    bArr5 = bArr7;
                }
                CipherGen cipherGen = new CipherGen(random, j10, access$2300, str3, str4, bArr, str5, bArr5);
                if ((NTLMEngineImpl.FLAG_TARGETINFO_PRESENT & i11) != 0 && bArr7 != null && str5 != null) {
                    try {
                        this.ntResp = cipherGen.getNTLMv2Response();
                        this.lmResp = cipherGen.getLMv2Response();
                        if ((i11 & NTLMEngineImpl.FLAG_REQUEST_LAN_MANAGER_KEY) != 0) {
                            bArr6 = cipherGen.getLanManagerSessionKey();
                        } else {
                            bArr6 = cipherGen.getNTLMv2UserSessionKey();
                        }
                    } catch (NTLMEngineException unused) {
                        this.ntResp = new byte[0];
                        this.lmResp = cipherGen.getLMResponse();
                        if ((i11 & NTLMEngineImpl.FLAG_REQUEST_LAN_MANAGER_KEY) != 0) {
                            bArr6 = cipherGen.getLanManagerSessionKey();
                        } else {
                            bArr6 = cipherGen.getLMUserSessionKey();
                        }
                    }
                } else if ((NTLMEngineImpl.FLAG_REQUEST_NTLM2_SESSION & i11) != 0) {
                    this.ntResp = cipherGen.getNTLM2SessionResponse();
                    this.lmResp = cipherGen.getLM2SessionResponse();
                    if ((i11 & NTLMEngineImpl.FLAG_REQUEST_LAN_MANAGER_KEY) != 0) {
                        bArr6 = cipherGen.getLanManagerSessionKey();
                    } else {
                        bArr6 = cipherGen.getNTLM2SessionResponseUserSessionKey();
                    }
                } else {
                    this.ntResp = cipherGen.getNTLMResponse();
                    this.lmResp = cipherGen.getLMResponse();
                    if ((i11 & NTLMEngineImpl.FLAG_REQUEST_LAN_MANAGER_KEY) != 0) {
                        bArr6 = cipherGen.getLanManagerSessionKey();
                    } else {
                        bArr6 = cipherGen.getNTLMUserSessionKey();
                    }
                }
                byte[] bArr8 = null;
                if ((i11 & 16) != 0) {
                    if ((NTLMEngineImpl.FLAG_REQUEST_EXPLICIT_KEY_EXCH & i11) != 0) {
                        byte[] secondaryKey = cipherGen.getSecondaryKey();
                        this.exportedSessionKey = secondaryKey;
                        this.sessionKey = NTLMEngineImpl.RC4(secondaryKey, bArr6);
                    } else {
                        this.sessionKey = bArr6;
                        this.exportedSessionKey = bArr6;
                    }
                } else if (!this.computeMic) {
                    this.sessionKey = null;
                    this.exportedSessionKey = null;
                } else {
                    throw new NTLMEngineException("Cannot sign/seal: no exported session key");
                }
                Charset access$2500 = NTLMEngineImpl.getCharset(i11);
                this.hostBytes = access$2200 != null ? access$2200.getBytes(access$2500) : null;
                this.domainBytes = access$2300 != null ? access$2300.toUpperCase(Locale.ROOT).getBytes(access$2500) : bArr8;
                this.userBytes = str3.getBytes(access$2500);
                return;
            }
            throw new NTLMEngineException("Random generator not available");
        }
    }

    static {
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstance("SHA1PRNG");
        } catch (Exception unused) {
            secureRandom = null;
        }
        RND_GEN = secureRandom;
    }

    NTLMEngineImpl() {
    }

    static int F(int i10, int i11, int i12) {
        return ((~i10) & i12) | (i11 & i10);
    }

    static int G(int i10, int i11, int i12) {
        return (i10 & (i11 | i12)) | (i11 & i12);
    }

    static int H(int i10, int i11, int i12) {
        return (i10 ^ i11) ^ i12;
    }

    static byte[] RC4(byte[] bArr, byte[] bArr2) {
        try {
            Cipher instance = Cipher.getInstance("RC4");
            instance.init(1, new SecretKeySpec(bArr2, "RC4"));
            return instance.doFinal(bArr);
        } catch (Exception e10) {
            throw new NTLMEngineException(e10.getMessage(), e10);
        }
    }

    /* access modifiers changed from: private */
    public static String convertDomain(String str) {
        return stripDotSuffix(str);
    }

    /* access modifiers changed from: private */
    public static String convertHost(String str) {
        return stripDotSuffix(str);
    }

    /* access modifiers changed from: private */
    public static byte[] createBlob(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[(bArr3.length + 20 + bArr2.length + 4)];
        System.arraycopy(new byte[]{1, 1, 0, 0}, 0, bArr4, 0, 4);
        System.arraycopy(new byte[]{0, 0, 0, 0}, 0, bArr4, 4, 4);
        System.arraycopy(bArr3, 0, bArr4, 8, bArr3.length);
        int length = bArr3.length;
        System.arraycopy(bArr, 0, bArr4, 8 + length, 8);
        System.arraycopy(new byte[]{0, 0, 0, 0}, 0, bArr4, length + 16, 4);
        int i10 = length + 20;
        System.arraycopy(bArr2, 0, bArr4, i10, bArr2.length);
        System.arraycopy(new byte[]{0, 0, 0, 0}, 0, bArr4, i10 + bArr2.length, 4);
        return bArr4;
    }

    /* access modifiers changed from: private */
    public static Key createDESKey(byte[] bArr, int i10) {
        byte[] bArr2 = new byte[7];
        System.arraycopy(bArr, i10, bArr2, 0, 7);
        byte b10 = bArr2[0];
        byte b11 = bArr2[1];
        byte b12 = bArr2[2];
        byte b13 = bArr2[3];
        byte b14 = bArr2[4];
        byte b15 = bArr2[5];
        byte b16 = bArr2[6];
        byte[] bArr3 = {b10, (byte) ((b10 << 7) | ((b11 & 255) >>> 1)), (byte) ((b11 << 6) | ((b12 & 255) >>> 2)), (byte) ((b12 << 5) | ((b13 & 255) >>> 3)), (byte) ((b13 << 4) | ((b14 & 255) >>> 4)), (byte) (((b15 & 255) >>> 5) | (b14 << 3)), (byte) ((b15 << 2) | ((b16 & 255) >>> 6)), (byte) (b16 << 1)};
        oddParity(bArr3);
        return new SecretKeySpec(bArr3, "DES");
    }

    /* access modifiers changed from: private */
    public static byte[] encodeLong(int i10) {
        byte[] bArr = new byte[4];
        encodeLong(bArr, 0, i10);
        return bArr;
    }

    /* access modifiers changed from: private */
    public static Charset getCharset(int i10) {
        if ((i10 & 1) == 0) {
            return DEFAULT_CHARSET;
        }
        Charset charset = UNICODE_LITTLE_UNMARKED;
        if (charset != null) {
            return charset;
        }
        throw new NTLMEngineException("Unicode not supported");
    }

    static MessageDigest getMD5() {
        try {
            return MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
        } catch (NoSuchAlgorithmException e10) {
            throw new RuntimeException("MD5 message digest doesn't seem to exist - fatal error: " + e10.getMessage(), e10);
        }
    }

    private static byte[] getNullTerminatedAsciiString(String str) {
        byte[] bytes = str.getBytes(Consts.ASCII);
        byte[] bArr = new byte[(bytes.length + 1)];
        System.arraycopy(bytes, 0, bArr, 0, bytes.length);
        bArr[bytes.length] = 0;
        return bArr;
    }

    static String getType1Message(String str, String str2) {
        return TYPE_1_MESSAGE;
    }

    static String getType3Message(String str, String str2, String str3, String str4, byte[] bArr, int i10, String str5, byte[] bArr2) {
        return new Type3Message(str4, str3, str, str2, bArr, i10, str5, bArr2).getResponse();
    }

    static byte[] hmacMD5(byte[] bArr, byte[] bArr2) {
        HMACMD5 hmacmd5 = new HMACMD5(bArr2);
        hmacmd5.update(bArr);
        return hmacmd5.getOutput();
    }

    /* access modifiers changed from: private */
    public static byte[] lmHash(String str) {
        try {
            String upperCase = str.toUpperCase(Locale.ROOT);
            Charset charset = Consts.ASCII;
            byte[] bytes = upperCase.getBytes(charset);
            byte[] bArr = new byte[14];
            System.arraycopy(bytes, 0, bArr, 0, Math.min(bytes.length, 14));
            Key createDESKey = createDESKey(bArr, 0);
            Key createDESKey2 = createDESKey(bArr, 7);
            byte[] bytes2 = "KGS!@#$%".getBytes(charset);
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(1, createDESKey);
            byte[] doFinal = instance.doFinal(bytes2);
            instance.init(1, createDESKey2);
            byte[] doFinal2 = instance.doFinal(bytes2);
            byte[] bArr2 = new byte[16];
            System.arraycopy(doFinal, 0, bArr2, 0, 8);
            System.arraycopy(doFinal2, 0, bArr2, 8, 8);
            return bArr2;
        } catch (Exception e10) {
            throw new NTLMEngineException(e10.getMessage(), e10);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] lmResponse(byte[] bArr, byte[] bArr2) {
        try {
            byte[] bArr3 = new byte[21];
            System.arraycopy(bArr, 0, bArr3, 0, 16);
            Key createDESKey = createDESKey(bArr3, 0);
            Key createDESKey2 = createDESKey(bArr3, 7);
            Key createDESKey3 = createDESKey(bArr3, 14);
            Cipher instance = Cipher.getInstance("DES/ECB/NoPadding");
            instance.init(1, createDESKey);
            byte[] doFinal = instance.doFinal(bArr2);
            instance.init(1, createDESKey2);
            byte[] doFinal2 = instance.doFinal(bArr2);
            instance.init(1, createDESKey3);
            byte[] doFinal3 = instance.doFinal(bArr2);
            byte[] bArr4 = new byte[24];
            System.arraycopy(doFinal, 0, bArr4, 0, 8);
            System.arraycopy(doFinal2, 0, bArr4, 8, 8);
            System.arraycopy(doFinal3, 0, bArr4, 16, 8);
            return bArr4;
        } catch (Exception e10) {
            throw new NTLMEngineException(e10.getMessage(), e10);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] lmv2Hash(String str, String str2, byte[] bArr) {
        Charset charset = UNICODE_LITTLE_UNMARKED;
        if (charset != null) {
            HMACMD5 hmacmd5 = new HMACMD5(bArr);
            Locale locale = Locale.ROOT;
            hmacmd5.update(str2.toUpperCase(locale).getBytes(charset));
            if (str != null) {
                hmacmd5.update(str.toUpperCase(locale).getBytes(charset));
            }
            return hmacmd5.getOutput();
        }
        throw new NTLMEngineException("Unicode not supported");
    }

    /* access modifiers changed from: private */
    public static byte[] lmv2Response(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        HMACMD5 hmacmd5 = new HMACMD5(bArr);
        hmacmd5.update(bArr2);
        hmacmd5.update(bArr3);
        byte[] output = hmacmd5.getOutput();
        byte[] bArr4 = new byte[(output.length + bArr3.length)];
        System.arraycopy(output, 0, bArr4, 0, output.length);
        System.arraycopy(bArr3, 0, bArr4, output.length, bArr3.length);
        return bArr4;
    }

    /* access modifiers changed from: private */
    public static byte[] makeRandomChallenge(Random random) {
        byte[] bArr = new byte[8];
        synchronized (random) {
            random.nextBytes(bArr);
        }
        return bArr;
    }

    /* access modifiers changed from: private */
    public static byte[] makeSecondaryKey(Random random) {
        byte[] bArr = new byte[16];
        synchronized (random) {
            random.nextBytes(bArr);
        }
        return bArr;
    }

    static byte[] ntlm2SessionResponse(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        try {
            MessageDigest md5 = getMD5();
            md5.update(bArr2);
            md5.update(bArr3);
            byte[] digest = md5.digest();
            byte[] bArr4 = new byte[8];
            System.arraycopy(digest, 0, bArr4, 0, 8);
            return lmResponse(bArr, bArr4);
        } catch (Exception e10) {
            if (e10 instanceof NTLMEngineException) {
                throw ((NTLMEngineException) e10);
            }
            throw new NTLMEngineException(e10.getMessage(), e10);
        }
    }

    /* access modifiers changed from: private */
    public static byte[] ntlmHash(String str) {
        Charset charset = UNICODE_LITTLE_UNMARKED;
        if (charset != null) {
            byte[] bytes = str.getBytes(charset);
            MD4 md4 = new MD4();
            md4.update(bytes);
            return md4.getOutput();
        }
        throw new NTLMEngineException("Unicode not supported");
    }

    /* access modifiers changed from: private */
    public static byte[] ntlmv2Hash(String str, String str2, byte[] bArr) {
        Charset charset = UNICODE_LITTLE_UNMARKED;
        if (charset != null) {
            HMACMD5 hmacmd5 = new HMACMD5(bArr);
            hmacmd5.update(str2.toUpperCase(Locale.ROOT).getBytes(charset));
            if (str != null) {
                hmacmd5.update(str.getBytes(charset));
            }
            return hmacmd5.getOutput();
        }
        throw new NTLMEngineException("Unicode not supported");
    }

    private static void oddParity(byte[] bArr) {
        for (int i10 = 0; i10 < bArr.length; i10++) {
            byte b10 = bArr[i10];
            if (((((((((b10 >>> 7) ^ (b10 >>> 6)) ^ (b10 >>> 5)) ^ (b10 >>> 4)) ^ (b10 >>> 3)) ^ (b10 >>> 2)) ^ (b10 >>> 1)) & 1) == 0) {
                bArr[i10] = (byte) (b10 | 1);
            } else {
                bArr[i10] = (byte) (b10 & -2);
            }
        }
    }

    /* access modifiers changed from: private */
    public static byte[] readSecurityBuffer(byte[] bArr, int i10) {
        int readUShort = readUShort(bArr, i10);
        int readULong = readULong(bArr, i10 + 4);
        if (bArr.length < readULong + readUShort) {
            return new byte[readUShort];
        }
        byte[] bArr2 = new byte[readUShort];
        System.arraycopy(bArr, readULong, bArr2, 0, readUShort);
        return bArr2;
    }

    /* access modifiers changed from: private */
    public static int readULong(byte[] bArr, int i10) {
        if (bArr.length < i10 + 4) {
            return 0;
        }
        return ((bArr[i10 + 3] & 255) << 24) | (bArr[i10] & 255) | ((bArr[i10 + 1] & 255) << 8) | ((bArr[i10 + 2] & 255) << 16);
    }

    /* access modifiers changed from: private */
    public static int readUShort(byte[] bArr, int i10) {
        if (bArr.length < i10 + 2) {
            return 0;
        }
        return ((bArr[i10 + 1] & 255) << 8) | (bArr[i10] & 255);
    }

    static int rotintlft(int i10, int i11) {
        return (i10 >>> (32 - i11)) | (i10 << i11);
    }

    private static String stripDotSuffix(String str) {
        if (str == null) {
            return null;
        }
        int indexOf = str.indexOf(46);
        if (indexOf != -1) {
            return str.substring(0, indexOf);
        }
        return str;
    }

    static void writeULong(byte[] bArr, int i10, int i11) {
        bArr[i11] = (byte) (i10 & 255);
        bArr[i11 + 1] = (byte) ((i10 >> 8) & 255);
        bArr[i11 + 2] = (byte) ((i10 >> 16) & 255);
        bArr[i11 + 3] = (byte) ((i10 >> 24) & 255);
    }

    static void writeUShort(byte[] bArr, int i10, int i11) {
        bArr[i11] = (byte) (i10 & 255);
        bArr[i11 + 1] = (byte) ((i10 >> 8) & 255);
    }

    public String generateType1Msg(String str, String str2) {
        return getType1Message(str2, str);
    }

    public String generateType3Msg(String str, String str2, String str3, String str4, String str5) {
        Type2Message type2Message = new Type2Message(str5);
        return getType3Message(str, str2, str4, str3, type2Message.getChallenge(), type2Message.getFlags(), type2Message.getTarget(), type2Message.getTargetInfo());
    }

    static String getType3Message(String str, String str2, String str3, String str4, byte[] bArr, int i10, String str5, byte[] bArr2, Certificate certificate, byte[] bArr3, byte[] bArr4) {
        return new Type3Message(str4, str3, str, str2, bArr, i10, str5, bArr2, certificate, bArr3, bArr4).getResponse();
    }

    /* compiled from: r8-map-id-a7f622bc48ed2f9e9b35772ed8c22523c686361cbbd0147472b908c53aa1a55b */
    static class NTLMMessage {
        protected int currentOutputPosition;
        protected byte[] messageContents;

        NTLMMessage() {
            this.messageContents = null;
            this.currentOutputPosition = 0;
        }

        /* access modifiers changed from: protected */
        public void addByte(byte b10) {
            byte[] bArr = this.messageContents;
            int i10 = this.currentOutputPosition;
            bArr[i10] = b10;
            this.currentOutputPosition = i10 + 1;
        }

        /* access modifiers changed from: protected */
        public void addBytes(byte[] bArr) {
            if (bArr != null) {
                for (byte b10 : bArr) {
                    byte[] bArr2 = this.messageContents;
                    int i10 = this.currentOutputPosition;
                    bArr2[i10] = b10;
                    this.currentOutputPosition = i10 + 1;
                }
            }
        }

        /* access modifiers changed from: protected */
        public void addULong(int i10) {
            addByte((byte) (i10 & 255));
            addByte((byte) ((i10 >> 8) & 255));
            addByte((byte) ((i10 >> 16) & 255));
            addByte((byte) ((i10 >> 24) & 255));
        }

        /* access modifiers changed from: protected */
        public void addUShort(int i10) {
            addByte((byte) (i10 & 255));
            addByte((byte) ((i10 >> 8) & 255));
        }

        /* access modifiers changed from: protected */
        public void buildMessage() {
            throw new RuntimeException("Message builder not implemented for " + getClass().getName());
        }

        public byte[] getBytes() {
            if (this.messageContents == null) {
                buildMessage();
            }
            byte[] bArr = this.messageContents;
            int length = bArr.length;
            int i10 = this.currentOutputPosition;
            if (length > i10) {
                byte[] bArr2 = new byte[i10];
                System.arraycopy(bArr, 0, bArr2, 0, i10);
                this.messageContents = bArr2;
            }
            return this.messageContents;
        }

        /* access modifiers changed from: protected */
        public int getMessageLength() {
            return this.currentOutputPosition;
        }

        /* access modifiers changed from: protected */
        public int getPreambleLength() {
            return NTLMEngineImpl.SIGNATURE.length + 4;
        }

        public String getResponse() {
            return new String(Base64.encodeBase64(getBytes()), Consts.ASCII);
        }

        /* access modifiers changed from: protected */
        public void prepareResponse(int i10, int i11) {
            this.messageContents = new byte[i10];
            this.currentOutputPosition = 0;
            addBytes(NTLMEngineImpl.SIGNATURE);
            addULong(i11);
        }

        /* access modifiers changed from: protected */
        public byte readByte(int i10) {
            byte[] bArr = this.messageContents;
            if (bArr.length >= i10 + 1) {
                return bArr[i10];
            }
            throw new NTLMEngineException("NTLM: Message too short");
        }

        /* access modifiers changed from: protected */
        public void readBytes(byte[] bArr, int i10) {
            byte[] bArr2 = this.messageContents;
            if (bArr2.length >= bArr.length + i10) {
                System.arraycopy(bArr2, i10, bArr, 0, bArr.length);
                return;
            }
            throw new NTLMEngineException("NTLM: Message too short");
        }

        /* access modifiers changed from: protected */
        public byte[] readSecurityBuffer(int i10) {
            return NTLMEngineImpl.readSecurityBuffer(this.messageContents, i10);
        }

        /* access modifiers changed from: protected */
        public int readULong(int i10) {
            return NTLMEngineImpl.readULong(this.messageContents, i10);
        }

        /* access modifiers changed from: protected */
        public int readUShort(int i10) {
            return NTLMEngineImpl.readUShort(this.messageContents, i10);
        }

        NTLMMessage(String str, int i10) {
            this(Base64.decodeBase64(str.getBytes(NTLMEngineImpl.DEFAULT_CHARSET)), i10);
        }

        NTLMMessage(byte[] bArr, int i10) {
            int i11 = 0;
            this.currentOutputPosition = 0;
            this.messageContents = bArr;
            if (bArr.length >= NTLMEngineImpl.SIGNATURE.length) {
                while (i11 < NTLMEngineImpl.SIGNATURE.length) {
                    if (this.messageContents[i11] == NTLMEngineImpl.SIGNATURE[i11]) {
                        i11++;
                    } else {
                        throw new NTLMEngineException("NTLM message expected - instead got unrecognized bytes");
                    }
                }
                int readULong = readULong(NTLMEngineImpl.SIGNATURE.length);
                if (readULong == i10) {
                    this.currentOutputPosition = this.messageContents.length;
                    return;
                }
                throw new NTLMEngineException("NTLM type " + Integer.toString(i10) + " message expected - instead got type " + Integer.toString(readULong));
            }
            throw new NTLMEngineException("NTLM message decoding error - packet too short");
        }
    }

    /* access modifiers changed from: private */
    public static void encodeLong(byte[] bArr, int i10, int i11) {
        bArr[i10] = (byte) (i11 & 255);
        bArr[i10 + 1] = (byte) ((i11 >> 8) & 255);
        bArr[i10 + 2] = (byte) ((i11 >> 16) & 255);
        bArr[i10 + 3] = (byte) ((i11 >> 24) & 255);
    }
}
