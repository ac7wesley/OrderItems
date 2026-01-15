package entity;

public class OrderItem {

    private Integer quantity;
    private Double price;

    private Product product;

    public OrderItem(Integer quantity, Double price, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getSubTotal() {
        return quantity * price;
    }

    @Override

    public String toString() {
        return "%s, $%.2f, Quantity: %d, Subtotal: $%.2f".formatted(product.getName(), price, quantity, getSubTotal());
    }

}