public final class Order {
   final Product product;
   final int quantity;
   public Order(Product product, int quantity) {
      this.product = product;
      this.quantity = quantity;
   }
   
   @Override
   public String toString() {
      return product+", quantity:"+quantity;
   }
}
