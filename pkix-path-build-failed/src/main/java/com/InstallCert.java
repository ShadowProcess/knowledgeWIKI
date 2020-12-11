package com;

import javax.net.ssl.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;

/**
 * 解决javax.net.ssl.SSLHandshakeException:
 * sun.security.validator.ValidatorException:
 * PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException:
 * unable to find valid certification path to requested target
 * <p>
 * 使用说明：
 * 1.修改domain为要生成的网站名称
 * <p>
 * 2.运行程序
 * <p>
 * 3.在控制台输出停止的时候，在命令行输入：1，生成文件，会在执行目录下生成：jssecacerts
 * <p>
 * 4.这个时候再运行一遍InstallCert就不会报错，因为已经有jssecacerts文件，
 * 直接copy jssecacerts文件到%JAVA_HOME%\jre\lib\security下，就可以愉快的玩耍了。
 * <p>
 * 5.导入之后重启项目(完美解决)
 */

/**
 * 为什么Java同时具有cacerts和jssecacerts文件？
 *
 * 据我了解，cacerts文件是出厂默认文件。
 * 如果有cacerts文件，则仅用于jssecacerts文件。
 * 我的建议：保留cacerts文件，复制到jssecacerts并将所需的任何专用CA /签名证书添加到jssecacerts文件中
 */
public class InstallCert {


    public static void main(String[] args) throws Exception {
        //域名只能一个个的执行，它会以增加的方式添加到文件；
        //dl.google.com
        //storage.flutter-io.cn
        //jcenter.bintray.com
        String[] domains = new String[]{"jcenter.bintray.com"};
        getCert(domains);
    }



    public static void getCert(String[] args) throws Exception {
        String host       = null;
        int    port       = -1;
        char[] passphrase = null;

        // proxy
        boolean           useProxy   = false;
        String            proxyHost  = null;
        int               proxyPort  = -1;
        InetSocketAddress proxyAddr  = null;
        Socket            underlying = null;

        int numArg = 0;
        int nbArgs = args.length;
        boolean invalidArgs = false;
        boolean isQuiet = false;
        while (numArg < nbArgs) {
            String arg = args[numArg++];
            if (arg.startsWith("--proxy=")) {
                String proxy = arg.substring("--proxy=".length());
                useProxy = true;
                String[] c = proxy.split(":");
                proxyHost = c[0];
                proxyPort = Integer.parseInt(c[1]);  // proxy port is mandatory (we don't default to 8080)
            }
            else if (arg.startsWith("--quiet")) {
                isQuiet = true;
            }
            else if (host == null) {  // 1st argument is the "host:port"
                String[] c = arg.split(":");
                host = c[0];
                port = (c.length == 1) ? 443 : Integer.parseInt(c[1]);
            }
            else if (passphrase == null) {  //  2nd argument is the keystore passphrase
                passphrase = arg.toCharArray();
            }
            else {
                invalidArgs = true;  // too many args
            }
        }

        if (host == null) {
            invalidArgs = true;
        }

        if (invalidArgs) {
            System.out.println("Usage: java InstallCert [--proxy=proxyHost:proxyPort] host[:port] [passphrase] [--quiet]");
            return;
        }

        // default values
        if (port       == -1  ) { port       = 443; }
        if (passphrase == null) { passphrase = "changeit".toCharArray(); }

        File file = new File("jssecacerts");
//        if (file.exists() == false){
//            file.createNewFile();
//        }
        if (file.isFile() == false) {
            char SEP = File.separatorChar;
            File dir = new File(System.getProperty("java.home") + SEP + "lib" + SEP + "security");
            file = new File(dir, "jssecacerts");
            if (file.isFile() == false) {
                file = new File(dir, "cacerts");
            }
        }
        System.out.println("Loading KeyStore " + file + "...");
        InputStream in = new FileInputStream(file);
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(in, passphrase);
        in.close();

        if (useProxy) {
            proxyAddr = new InetSocketAddress(proxyHost, proxyPort);
            underlying = new Socket(new Proxy(Proxy.Type.HTTP, proxyAddr));
        }

        SSLContext context = SSLContext.getInstance("TLS");
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(ks);
        X509TrustManager defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        SavingTrustManager tm = new SavingTrustManager(defaultTrustManager);
        context.init(null, new TrustManager[]{tm}, null);
        SSLSocketFactory factory = context.getSocketFactory();

        System.out.println("Opening connection to " + host + ":" + port + (useProxy ? (" via proxy "+proxyHost+":"+proxyPort) : "") + " ...");
        SSLSocket socket;
        if (useProxy) {
            underlying.connect(new InetSocketAddress(host, port));
            socket = (SSLSocket) factory.createSocket(underlying, host, port, true);
        } else {
            socket = (SSLSocket) factory.createSocket(host, port);
        }
        socket.setSoTimeout(10000);
        try {
            System.out.println("Starting SSL handshake...");
            socket.startHandshake();
            socket.close();
            System.out.println();
            System.out.println("No errors, certificate is already trusted");
        } catch (SSLException e) {
            System.out.println();
            e.printStackTrace(System.out);
        }

        X509Certificate[] chain = tm.chain;
        if (chain == null) {
            System.out.println("Could not obtain server certificate chain");
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println();
        System.out.println("Server sent " + chain.length + " certificate(s):");
        System.out.println();
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        for (int i = 0; i < chain.length; i++) {
            X509Certificate cert = chain[i];
            System.out.println(" " + (i + 1) + " Subject " + cert.getSubjectDN());
            System.out.println("   Issuer  " + cert.getIssuerDN());
            sha1.update(cert.getEncoded());
            System.out.println("   sha1    " + toHexString(sha1.digest()));
            md5.update(cert.getEncoded());
            System.out.println("   md5     " + toHexString(md5.digest()));
            System.out.println();
        }

        int k;
        if (isQuiet) {
            System.out.println("Adding first certificate to trusted keystore");
            k = 0;
        }
        else {
            System.out.println("Enter certificate to add to trusted keystore or 'q' to quit: [1]");
            String line = reader.readLine().trim();
            try {
                k = (line.length() == 0) ? 0 : Integer.parseInt(line) - 1;
            } catch (NumberFormatException e) {
                System.out.println("KeyStore not changed");
                return;
            }
        }

        X509Certificate cert = chain[k];
        String alias = host + "-" + (k + 1);
        ks.setCertificateEntry(alias, cert);

        OutputStream out = new FileOutputStream("jssecacerts");
        ks.store(out, passphrase);
        out.close();

        System.out.println();
        System.out.println(cert);
        System.out.println();
        System.out.println("Added certificate to keystore 'jssecacerts' using alias '" + alias + "'");
    }

    private static final char[] HEXDIGITS = "0123456789abcdef".toCharArray();

    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 3);
        for (int b : bytes) {
            b &= 0xff;
            sb.append(HEXDIGITS[b >> 4]);
            sb.append(HEXDIGITS[b & 15]);
            sb.append(' ');
        }
        return sb.toString();
    }

    private static class SavingTrustManager implements X509TrustManager {

        private final X509TrustManager tm;
        private X509Certificate[] chain;

        SavingTrustManager(X509TrustManager tm) {
            this.tm = tm;
        }

        public X509Certificate[] getAcceptedIssuers() {
            // This change has been done due to the following resolution advised for Java 1.7+
            // http://infposs.blogspot.kr/2013/06/installcert-and-java-7.html
            return new X509Certificate[0];
            //throw new UnsupportedOperationException();
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            throw new UnsupportedOperationException();
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            this.chain = chain;
            tm.checkServerTrusted(chain, authType);
        }
    }

}

/**
 * 1.执行命令导出证书：
 * keytool -exportcert -alias jcenter.bintray.com-1(证书别名) -keystore jssecacerts -storepass changeit -file jcenter.bintray.com.cer(证书文件全名)
 *
 * 2.将证书导入cacerts文件
 * keytool -importcert -noprompt -trustcacerts -alias jcenter.bintray.com-1(证书别名) -file jcenter.bintray.com.cer(证书文件全名) -keystore "C:\Program Files\Java\jdk8u252\jre\lib\security\cacerts" -storepass changeit
 */

