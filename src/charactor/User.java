package charactor;

import lombok.Data;

@Data

public class User {

    public int id;

    public String name;

    public String password;

    public User(){

    }

    public User(String name , String password){

        this.name = name;
        this.password = password;
    }
}
