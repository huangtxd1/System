package charactor;

import lombok.Data;

@Data

public class Discuss {

    public int id;

    public int houseId;

    public String userName;

    public String problem;

    public String answer;

    public Discuss(){

    }

    public Discuss(int houseId, String userName, String problem){

        this.houseId = houseId;
        this.userName = userName;
        this.problem = problem;
    }
}
