package charactor;

import lombok.Data;

@Data

public class Intermediary {

    public int id;

    public String name;

    public String password;

    public Intermediary(){

    }

    public Intermediary(String name , String password){

        this.name = name;
        this.password = password;
    }
}
