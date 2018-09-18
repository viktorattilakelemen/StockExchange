
/** Class to store comparable stock objects
*/
class Stock implements Comparable<Stock> {

      private String ticker_symbol = null;
      private double price = 0.0;

      public Stock(String ticker_symbol, double starting_price) {
          this.ticker_symbol = ticker_symbol;
          this.price = starting_price;
      }

      public void updatePrice(double change) {
          this.price = price + change;
      }

      public int compareTo(Stock other) {
          if(this.price < other.getPrice()){
              return -1;
          }
          else if(this.price == other.getPrice()){
              return 0;
          }
          else{
              return 1;
          }
      }
      public double getPrice(){
          return this.price;
      }
      public String getTicker(){
          return this.ticker_symbol;
      }
    }
