public class User {
    String nameOfUser;

    public User(String name){
        this.nameOfUser = name;
    }

    public User(){
        this.nameOfUser = "User1";
    }

    public String getUserName(){
        return this.nameOfUser;
    }
}