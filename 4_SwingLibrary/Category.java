public class Category extends Product {

    public Category(String name, double price, int quantity, String manufacturer) {
        super(name, price, quantity, manufacturer);
    }

    @Override
    public String getInfo() {
        return super.getInfo() ;
    }
}
