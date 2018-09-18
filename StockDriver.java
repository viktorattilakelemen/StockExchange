import java.util.*;
import java.io.*;

/**
 * Class to make changes to initial stock prices, based on input chronological
 * price changes, using an Array List data structure
 *
 */
public class StockDriver
{

    public static void main(String[] args) throws IOException, FileNotFoundException{
        long timeStart = System.currentTimeMillis();
        //create ArrayList to hold stock objects
        ArrayList<Stock> stockList = new ArrayList<Stock>();
        //populate it with newStock objects
        File file = new File("shuffled_stocks.csv");
        Scanner scan = new Scanner(file);

        String ticker = "hello";
        while (scan.hasNextLine()){
            scan.useDelimiter(",|\\n");
            double price = 0;
            String holder = scan.next();

            //prevents duplicate stocks from being added to the arraylist
            if (!ticker.equals(holder)){
                ticker = holder;
                price = scan.nextDouble();
                Stock currStock = new Stock(ticker, price);
                stockList.add(currStock);

            }
            scan.nextLine();
        }
        //read in text stock change data
        //System.out.println("Please enter the stock change data file name with its extension: ");
        //Scanner scan2 = new Scanner(System.in);
        File file2 = new File("changes.txt");
        Scanner scan2 = new Scanner(file2);

        //update stockList data
        while (scan2.hasNextLine()){
            String currStock = scan2.next();
            for (Stock element: stockList){
                if (element.getTicker().equals(currStock)){
                    double stockChange = scan2.nextDouble();                    
                    element.updatePrice(stockChange);
                }
            }
            if (scan2.hasNextLine()){
                scan2.nextLine();
            }
        }
        long timeEnd = System.currentTimeMillis();
        long duration = timeEnd - timeStart;
        System.out.println("Runtime: " + duration + "ms");

        File output = new File("updated_stocks.csv");
        FileWriter w = new FileWriter(output, true);
        BufferedWriter bw = new BufferedWriter(w);
        for (Stock element: stockList){
            bw.write(element.getTicker() + "," + element.getPrice());
            bw.newLine();
            bw.flush();
        }
    }

}
