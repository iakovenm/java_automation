package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().deleteSelectedGroups();
        app.getGroupHelper().returnToGroupPage();
    }

}
