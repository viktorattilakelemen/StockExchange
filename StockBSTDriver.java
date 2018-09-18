import java.util.*;
import java.io.*;

/**
 * Class to make changes to initial stock prices, based on input chronological
 * price changes, using a Binary Search Tree data structure
 */
public class StockBSTDriver
{

    public static void main(String[] args) throws IOException, FileNotFoundException{
        long timeStart = System.currentTimeMillis();
        //create BST to hold stock objects
        TreeMap<String, Stock> stockBST = new TreeMap<String, Stock>();

        // populate it with newStock objects
        File file = new File("shuffled_stocks.csv");
        Scanner scan = new Scanner(file);

        String ticker = "hello";
        while (scan.hasNextLine()){
            scan.useDelimiter(",|\\n");
            double price = 0;
            String holder = scan.next();
            //prevents duplicate stocks from being added to the BST
            if (!ticker.equals(holder)){
                ticker = holder;
                price = scan.nextDouble();
                Stock currStock = new Stock(ticker, price);
                stockBST.put(ticker, currStock);

            }
            scan.nextLine();
        }
        //read in text stock change data
        //System.out.println("Please enter the stock change data file name with its extension: ");
        //Scanner scan2 = new Scanner(System.in);
        File file2 = new File("changes.txt");
        Scanner scan2 = new Scanner(file2);

        //update stockBST data
        while (scan2.hasNextLine()){
            String scanned = scan2.next();
            //System.out.println(scanned);
            Stock stockUpd = stockBST.get(scanned);
            stockUpd.updatePrice(scan2.nextDouble());
        }
        long timeEnd = System.currentTimeMillis();
        long duration = timeEnd - timeStart;
        System.out.println("Runtime: " + duration + "ms");

        File output = new File("updated_stocks_BST.csv");
        FileWriter w = new FileWriter(output, true);
        BufferedWriter bw = new BufferedWriter(w);
        //obtained from: "http://www.java2novice.com/java-collections-and-util/treemap/iterate/"
        Set<String> keys = stockBST.keySet();
        for(String key: keys){
            bw.write(key + "," + stockBST.get(key).getPrice());
            bw.newLine();
            bw.flush();
        }
    }
}
