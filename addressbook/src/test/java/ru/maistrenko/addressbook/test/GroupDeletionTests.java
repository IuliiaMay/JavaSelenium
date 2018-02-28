package ru.maistrenko.addressbook.test;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.maistrenko.addressbook.model.GroupData;
import ru.maistrenko.addressbook.model.Groups;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;


/**
 * Created by User on 22.01.2018.
 */
public class GroupDeletionTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();

        if(app.db().groups().size()==0){
            GroupData group = new GroupData().withName("test1").withHeader("precondition").withFooter("test3");
            app.group().create(group);
        }
    }

    @Test
    public void testGroupDeletion(){
        Groups before = app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size()-1));
        Groups after = app.db().groups();
        assertThat(after, equalTo(before.without(deletedGroup)));
        verifyGroupListInUI();
    }


}
