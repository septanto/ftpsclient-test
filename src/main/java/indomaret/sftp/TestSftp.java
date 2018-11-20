package indomaret.sftp;

import org.apache.commons.net.ftp.FTPSClient;
import javax.net.ssl.TrustManager;

public class TestSftp {

    public static void main(String[] args) {
        try {
            FTPSClient ftpsClient = new FTPSClient("SSL", false);

            TrustManager trustAllCerts = new CustomTrustManager();
            ftpsClient.setTrustManager(trustAllCerts);
            ftpsClient.connect("someurl", 0000);
            if (ftpsClient.isConnected())
                System.out.println("Connected");
            ftpsClient.login("***", "****");
            System.out.println(ftpsClient.printWorkingDirectory());
            ftpsClient.disconnect();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

}
