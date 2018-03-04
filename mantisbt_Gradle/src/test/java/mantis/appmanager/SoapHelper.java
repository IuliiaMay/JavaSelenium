package mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import mantis.model.Issue;
import mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by User on 26.02.2018.
 */
public class SoapHelper {
    private final AppManager app;
    private String login = System.getProperty("web.adminLogin");
    private String password = System.getProperty("web.adminPassword");

    public SoapHelper(AppManager app) {
        this.app = app;
    }

     public Set<Project> getProjects() throws MalformedURLException, ServiceException, RemoteException {
     MantisConnectPortType mc = getMantisConnect();
     ProjectData[] projects = mc.mc_projects_get_user_accessible(login, password);
     return Arrays.asList(projects).stream()
             .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName()))
             .collect(Collectors.toSet());
 }

    public MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
        return new MantisConnectLocator()
               // .getMantisConnectPort(new URL(System.getProperty("rest.connectPort")));
                .getMantisConnectPort(new URL("http://localhost/mantisbt/api/rest/mantisconnect.php"));
    }

    public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
        MantisConnectPortType mc = getMantisConnect();
        String[] categories = mc.mc_project_get_categories(login, password, BigInteger.valueOf(issue.getProject().getId()));
        IssueData issueData = new IssueData();
        issueData.setSummary(issue.getSummary());
        issueData.setDescription(issue.getDescription());
        issueData.setCategory(categories[0]);
        issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
        BigInteger issueId = mc.mc_issue_add(login, password, issueData);
        IssueData createdIssueData = mc.mc_issue_get(login, password, issueId);
        return new Issue().withId(createdIssueData.getId().intValue())
                .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
                .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                                          .withName(createdIssueData.getProject().getName()));
    }
}

