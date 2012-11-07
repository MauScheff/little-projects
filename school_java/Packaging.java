public abstract class Packaging {
   public static final int MAX_PACKAGES = 30;

   public Packaging(Order[] sale) {
      products = new Product[sale.length];
      int total_packages = 0;
      for (int i = 0; i < sale.length; ++i) {
         products[i] = sale[i].product;
         total_packages += sale[i].quantity;
      }
      if (total_packages > MAX_PACKAGES)
         throw new IllegalArgumentException("too many packages");
   }

   /**
    * Returns trips array that specify how to deliver all packages specified by
    * the sale with the truck that can handle only truck_max_volume and
    * truck_max_weight.
    * 
    * @param truck_max_volume
    *           specify the maximum capacity of the truck
    * @param truck_max_weight
    *           specify the maximum load of the truck
    * @return an array cantianing trip that require for shipping all packages
    *         specified by the sale.
    * @throws IllegalArgumentException
    *            if it impposible to deliver with the given truck capacity.
    */
   public abstract Trip[] getTrips (int truck_max_volume, int truck_max_weight)
         throws IllegalArgumentException;

   public final class Trip {
      // holding quantity of each product to be ship for this trip
      final int[] quantity = new int[products.length];

      /**
       * Calculates value of merchandise of this trip
       * 
       * @return value of merchandise of this trip
       */
      public final int getValue () {
         int value = 0;
         for (int i = 0; i < quantity.length; ++i) {
            value += quantity[i] * products[i].value;
         }
         return value;
      }

      @Override
      public String toString () {
         StringBuilder s = new StringBuilder();
         s.append("value:");
         s.append(getValue());
         s.append(", ");

         for (int i = 0; i < quantity.length; ++i) {
            if (quantity[i] > 0) {
               s.append("#" + i + ":");
               s.append(quantity[i]);
               s.append(" ");
            }
         }
         return s.toString();
      }
   }

   protected final Product[] products;
}