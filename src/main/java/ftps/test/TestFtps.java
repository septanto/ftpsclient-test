package ftps.test;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class TestFtps {

    public static void main(String[] args) {
        try {
            FTPSClient ftpsClient = new FTPSClient("SSL", false);
            ftpsClient.setTrustManager(new CustomTrustManager());

            ftpsClient.connect("some.url.com", 0000);
            if (ftpsClient.isConnected())
                System.out.println("Connected");
            ftpsClient.login("uer", "password");

            ftpsClient.changeWorkingDirectory("server/files/directory/");
            System.out.println(ftpsClient.printWorkingDirectory());
            ftpsClient.enterLocalPassiveMode();

            FTPFile[] ftpFiles = ftpsClient.listFiles();

            if (ftpFiles != null && ftpFiles.length > 0) {
                for (FTPFile file : ftpFiles) {
                    if (!file.isFile()) {
                        continue;
                    }
                    System.out.println("Retrieving file " + file.getName());
                    OutputStream output;
                    output = new FileOutputStream("/output/directory/" + file.getName());
                    ftpsClient.retrieveFile(file.getName(), output);
                    output.close();
                    //delete the file
                    // ftp.deleteFile(file.getName());
                }
            } else {
                System.out.println("No Files");
            }

            ftpsClient.logout();
            ftpsClient.disconnect();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

}
