package soap.test;

import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.Test;
import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;


/**
 * Created by User on 26.02.2018.
 */
public class connection {
    @Test (enabled = false)
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc =new MantisConnectLocator()
                .getMantisConnectPort(new URL("http://localhost/mantisbt/api/rest/mantisconnect.php"));
        mc.mc_project_get_id_from_name("administrator", "root", "testProject");
        ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
        System.out.println(projects.length);
    }

    }
