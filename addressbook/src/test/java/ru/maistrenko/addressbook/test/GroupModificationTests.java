package ru.maistrenko.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.maistrenko.addressbook.model.GroupData;
import ru.maistrenko.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by User on 22.01.2018.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if(app.db().groups().size()==0){
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test (enabled = false)
    public void testGroupModification(){

        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1").withHeader("modified").withFooter("test2");
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

    }

    @Test
    public void testGroupModificationByDb(){
        Groups before = app.db().groups();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test1").withHeader("modified").withFooter("test2");
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();
        verifyGroupListInUI();
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));



    }



}
