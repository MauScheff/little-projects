/*
 * Author: Mauricio Zepeda, mzepedas@fit.edu
 * Course: CSE 2010, Section 01, Fall 2008
 * Project: lab03
 */
public class StockAtom {

   private String date;

   private Double DJI;

   private Double NYSE;

   private Double NASDAQ;

   public StockAtom(String date, Double DJI, Double NYSE, Double NASDAQ) {
      this.date = date;
      this.DJI = DJI;
      this.NYSE = NYSE;
      this.NASDAQ = NASDAQ;
   }

   public String getDate() {
      return this.date;
   }

   public Double getDJI() {
      return this.DJI;
   }

   public Double getNYSE() {
      return this.NYSE;
   }

   public Double getNASDAQ() {
      return this.NASDAQ;
   }
}