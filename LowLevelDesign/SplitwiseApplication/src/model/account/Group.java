package model.account;

import java.util.List;

public class Group {
    String groupName; //groupName is unique identifier
    String description;
    List<User> userList;

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }



    public Group(String groupName, String description) {
        this.groupName = groupName;
        this.description = description;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getDescription() {
        return description;
    }

    public List<User> getUserList() {
        return userList;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                ", userList=" + userList +
                '}';
    }
}
