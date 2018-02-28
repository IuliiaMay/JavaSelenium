package ru.maistrenko.addressbook.test;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.maistrenko.addressbook.model.GroupData;
import ru.maistrenko.addressbook.model.Groups;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by maistrenko on 28.07.2017.
 */
public class GroupCreationTests extends TestBase {

 @DataProvider
 public Iterator<Object[]> validGroupsFomXML() throws IOException {
  try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")))) {
   String xml = "";
   String line = reader.readLine();
   while (line != null) {
    xml += line;
    line = reader.readLine();
   }
   XStream xStream = new XStream();
   xStream.processAnnotations(GroupData.class);
   List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
   return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }
 }

 @DataProvider
 public Iterator<Object[]> validGroupsFromJson() throws IOException {
  try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")))) {
   String json = "";
   String line = reader.readLine();
   while (line != null) {
    json += line;
    line = reader.readLine();
   }
   Gson gson = new Gson();
   List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
   }.getType());
   return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }
 }


 @DataProvider
 public Iterator<Object[]> invalidGroups(){
  List<Object[]> list = new ArrayList<Object[]>();
  list.add(new Object[] {new GroupData().withName("name1'").withHeader("header1").withFooter("footer1")});

  return list.iterator();
 }

 /*@Test (dataProvider = "validGroupsFromJson")
    public void testGroupCreation(GroupData group){
     app.goTo().groupPage();
     Groups before = app.group().all();
     app.group().create(group);
     assertThat(app.group().count(), equalTo(before.size()+1));
     Groups after = app.group().all();
     assertThat(after, equalTo(
             before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
 }
*/

  @Test (dataProvider = "validGroupsFromJson")
  public void testGroupCreationByDb(GroupData group){
   app.goTo().groupPage();
   Groups before = app.db().groups();
   app.group().create(group);
   assertThat(app.group().count(), equalTo(before.size()+1));
   Groups after = app.db().groups();
   verifyGroupListInUI();
   assertThat(after, equalTo(
           before.withAdded(group.withId(after.stream().mapToInt((g)->g.getId()).max().getAsInt()))));
  }
    @Test(dataProvider = "invalidGroups")
    public void testBadGroupCreation(GroupData group){
        app.goTo().groupPage();
        Groups before = app.db().groups();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before));



    }
}
