package mantis.appmanager;



import org.apache.commons.net.ftp.FTPClient;
import sun.net.ftp.FtpProtocolException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by User on 19.02.2018.
 */
public class FtpHelper {
    private final AppManager app;
    private FTPClient ftp;

    public FtpHelper(AppManager app) {
        this.app = app;
        ftp = new FTPClient();
    }
    public void upload(File file, String target, String backup) throws IOException, FtpProtocolException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(backup);
        ftp.rename(target, backup);
        ftp.enterLocalPassiveMode();
        ftp.storeFile(target, new FileInputStream(file));
        ftp.disconnect();
    }
    public void restore(String target, String backup) throws IOException, FtpProtocolException {
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(target);
        ftp.rename(target, backup);
        ftp.disconnect();
    }
}
