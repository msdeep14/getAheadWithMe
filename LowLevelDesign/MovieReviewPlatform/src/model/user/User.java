package model.user;

public class User {
    String userName;
    UserType userType;

    public int getTotalMovieReviewed() {
        return totalMovieReviewed;
    }

    public void setTotalMovieReviewed(int totalMovieReviewed) {
        this.totalMovieReviewed = totalMovieReviewed;
    }

    int totalMovieReviewed;

    public User(String userName) {
        this.userName = userName;
        this.userType = UserType.VIEWER; //initially set as viewer
        this.totalMovieReviewed = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
