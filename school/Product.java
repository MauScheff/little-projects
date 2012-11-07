public final class Product {
   final int volume;
   final int weight;
   final int value;
   public Product(int size, int weight, int value) {
      this.volume=size;
      this.weight=weight;
      this.value=value;
   }
   
   @Override
   public String toString() {
      return "volume: "+volume+", weight: "+weight+", value:"+value;
   }
}
