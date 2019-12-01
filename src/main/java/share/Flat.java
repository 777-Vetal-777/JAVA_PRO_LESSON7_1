package share;

public class Flat {
    private String address;
    private String district;
    private String space;
    private int quantityRooms;
    private int price;
    private int phone;

    public Flat(String address, String district, String space, int quantityRooms, int price, int phone) {
        this.address = address;
        this.district = district;
        this.space = space;
        this.quantityRooms = quantityRooms;
        this.price = price;
        this.phone = phone;
    }

    public Flat() {
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public int getQuantityRooms() {
        return quantityRooms;
    }

    public void setQuantityRooms(int quantityRooms) {
        this.quantityRooms = quantityRooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Servlets.servlets.Flat{" +
                "address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", space='" + space + '\'' +
                ", quantityRooms=" + quantityRooms +
                ", price=" + price +
                ", phone=" + phone +
                '}';
    }
}
