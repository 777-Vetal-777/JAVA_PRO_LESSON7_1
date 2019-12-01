package share;

import java.util.ArrayList;
import java.util.List;

public class Flats {
    private List<Flat> list = new ArrayList<>();

    public void add(Flat flat) {
        list.add(flat);
    }

    public void print() {
        for (Flat flat : list) {
            System.out.println("ooo");
            System.out.println(flat);
        }
    }

    public Flat get(int i) {
        return list.get(i);
    }

    public int size() {
        return list.size();
    }

    public ArrayList<Flat> getList() {
        return new ArrayList<>(this.list);
    }

    @Override
    public String toString() {
        return "servlets.Flats{" +
                "list=" + list +
                '}';
    }
}
