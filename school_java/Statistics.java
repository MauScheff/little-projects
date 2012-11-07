import java.lang.Math;
import java.util.Arrays;
import java.util.Comparator;

public class Statistics {

   @Complexity (basic_operation = "Assignment",
		N = "atom.length",
		number_of_steps = "N",
		big_O = "N")
   public static double[] minimum(StockAtom[] atom) {
       double[] min = { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE };
       
       for (int j = 0; j < atom.length; j++) {
	   StockAtom current = atom[j];
	   min[0] = Math.min(min[0], current.getDJI());
	   min[1] = Math.min(min[1], current.getNYSE());
	   min[2] = Math.min(min[2], current.getNASDAQ());
       }
       return min;
   }

   @Complexity (basic_operation = "Assignment",
		N = "atom.length",
		number_of_steps = "N",
		big_O = "N")
   public static double[] maximum(StockAtom[] atom) {
      double[] max = { 0.0, 0.0, 0.0 };
      for (int j = 0; j < atom.length; j++) {
         StockAtom current = atom[j];
         max[0] = Math.max(max[0], current.getDJI());
         max[1] = Math.max(max[1], current.getNYSE());
         max[2] = Math.max(max[2], current.getNASDAQ());
      }
      return max;
   }

   @Complexity (basic_operation = "See maximum and minimum",
		N = "See maximum and minimum",
		number_of_steps = "See maximum and minimum",
		big_O = "See maximum and minimum")
   public static double[] range(StockAtom[] atom) {
      double[] max = maximum(atom);
      double[] min = minimum(atom);
      double[] range = { max[0] - min[0], max[1] - min[1], max[2] - min[2] };
      return range;
   }

   @Complexity (basic_operation = "Assignment",
		N = "atom.length",
		number_of_steps = "N",
		big_O = "N")
   public static double[] mean(StockAtom[] atom) {

      double[] sum = { 0.0, 0.0, 0.0 };
      for (int j = 0; j < atom.length; j++) {
         StockAtom current = atom[j];
         sum[0] += current.getDJI();
         sum[1] += current.getNYSE();
         sum[2] += current.getNASDAQ();
      }
      sum[0] /= atom.length;
      sum[1] /= atom.length;
      sum[2] /= atom.length;
      return sum;
   }

   /*
    * Requires that the list is sorted for each different category
    */
   @Complexity (basic_operation = "Assignment",
		N = "atom.length",
		number_of_steps = "N",
		big_O = "N")
   public static double[] median(StockAtom[] atom) {
      double[] results = new double[3];

      /*
       * Get the Median for the DJI values
       */
      Arrays.sort(atom, new Comparator<StockAtom>() {
         public int compare(StockAtom atomA, StockAtom atomB) {
            return (int) (atomA.getDJI() - atomB.getDJI());
         }
      });
      if (atom.length % 2 == 0) {
         results[0] = (atom[atom.length / 2].getDJI() + atom[(atom.length / 2) - 1]
               .getDJI()) / 2;
      } else {
         results[0] = atom[atom.length / 2].getDJI();
      }

      /*
       * Get the Median for the NYSE values
       */
      Arrays.sort(atom, new Comparator<StockAtom>() {
         public int compare(StockAtom atomA, StockAtom atomB) {
            return (int) (atomA.getNYSE() - atomB.getNYSE());
         }
      });
      if (atom.length % 2 == 0) {
         results[1] = (atom[atom.length / 2].getNYSE() + atom[(atom.length / 2) - 1]
               .getNYSE()) / 2;
      } else {
         results[1] = atom[atom.length / 2].getNYSE();
      }

      /*
       * Get the Median for the NASDAQ values
       */
      Arrays.sort(atom, new Comparator<StockAtom>() {
         public int compare(StockAtom atomA, StockAtom atomB) {
            return (int) (atomA.getNASDAQ() - atomB.getNASDAQ());
         }
      });
      if (atom.length % 2 == 0) {
         results[2] = (atom[atom.length / 2].getNASDAQ() + atom[(atom.length / 2) - 1]
               .getNASDAQ()) / 2;
      } else {
         results[2] = atom[atom.length / 2].getNASDAQ();
      }

      return results;
   }

   @Complexity (basic_operation = "Assignment",
		N = "atom.length",
		number_of_steps = "N*3",
		big_O = "N")
   public static double[] variancePopulation(StockAtom[] atom) {
      double[] mean = Statistics.mean(atom);
      double[] sum = new double[3];
      for (int j = 0; j < atom.length; j++) {
         sum[0] += Math.pow(atom[j].getDJI() - mean[0], 2);
         sum[1] += Math.pow(atom[j].getNYSE() - mean[1], 2);
         sum[2] += Math.pow(atom[j].getNASDAQ() - mean[2], 2);
      }
      sum[0] /= atom.length;
      sum[1] /= atom.length;
      sum[2] /= atom.length;
      return sum;
   }

   @Complexity (basic_operation = "See VariancePopulation",
		N = "See Variance Population",
		number_of_steps = "See Variance Population",
		big_O = "See Variance Population")
   public static double[] standardDeviation(StockAtom[] atom) {
      double[] results = variancePopulation(atom);
      results[0] = Math.sqrt(results[0]);
      results[1] = Math.sqrt(results[1]);
      results[2] = Math.sqrt(results[2]);
      return results;
   }
}