package charactor;

import lombok.Data;

@Data

public class House {

    public int id;

    public String name;

    public int area;

    public String place;

    public int price;

    public String format;

    public int collections;

    public boolean visit;

    public House(){

        this.collections = 0;
        this.visit = true;
    }

    public House(String name, int area, String place, int price, String format){

        this.name = name;
        this.area = area;
        this.place = place;
        this.price = price;
        this.format = format;
        this.collections = 0;
        this.visit = true;
    }
}
