package me.chengtx.webapp.model;

/**
 * @author <a href="mailto:chengtingxian@gmail.com">Tingxian Cheng</a>
 * @version 12/10/2014
 */
public class Stock {

    private String id;
    private String name;
    private float current;
    private float highest;
    private float lowest;
    private float open_today;
    private float close_yest;


    public static Stock parseStock(String item){
        Stock s = new Stock();
        String[] items = item.split(",");
        System.out.println(item);
//        s.setId(items[0]);
        s.setName(items[0]);
        s.setCurrent(Float.parseFloat(items[3]));
        s.setHighest(Float.parseFloat(items[4]));
        s.setLowest(Float.parseFloat(items[5]));
        s.setOpen_today(Float.parseFloat(items[1]));
        s.setClose_yest(Float.parseFloat(items[2]));
        return s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getOpen_today() {
        return open_today;
    }

    public void setOpen_today(float open_today) {
        this.open_today = open_today;
    }

    public float getClose_yest() {
        return close_yest;
    }

    public void setClose_yest(float close_yest) {
        this.close_yest = close_yest;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getHighest() {
        return highest;
    }

    public void setHighest(float highest) {
        this.highest = highest;
    }

    public float getLowest() {
        return lowest;
    }

    public void setLowest(float lowest) {
        this.lowest = lowest;
    }
}
