import java.util.ArrayList;
import java.util.Arrays;

/**
 * Stock Price Analysis Program
 * This program analyzes stock prices using both arrays and ArrayLists
 * Author: Data Analysis Project
 * Date: September 2025
 */
public class StockPriceAnalyzer {
    
    // Sample stock price data for 10 days (as float array)
    private static float[] stockPricesArray = {45.5f, 47.2f, 44.8f, 46.3f, 48.1f, 
                                               45.5f, 49.7f, 46.3f, 50.2f, 47.9f};
    
    // Sample stock price data as ArrayList
    private static ArrayList<Float> stockPricesArrayList = new ArrayList<>(
        Arrays.asList(45.5f, 47.2f, 44.8f, 46.3f, 48.1f, 
                      45.5f, 49.7f, 46.3f, 50.2f, 47.9f)
    );

    /**
     * Calculates the average stock price from an array
     * @param prices Array of stock prices
     * @return Average price as float
     */
    public static float calculateAveragePrice(float[] prices) {
        if (prices == null || prices.length == 0) {
            throw new IllegalArgumentException("Price array cannot be null or empty");
        }
        
        float sum = 0.0f;
        for (int i = 0; i < prices.length; i++) {
            sum += prices[i];
        }
        return sum / prices.length;
    }
    
    /**
     * Calculates the average stock price from an ArrayList
     * @param prices ArrayList of stock prices
     * @return Average price as float
     */
    public static float calculateAveragePrice(ArrayList<Float> prices) {
        if (prices == null || prices.isEmpty()) {
            throw new IllegalArgumentException("Price ArrayList cannot be null or empty");
        }
        
        float sum = 0.0f;
        for (int i = 0; i < prices.size(); i++) {
            sum += prices.get(i);
        }
        return sum / prices.size();
    }
    
    /**
     * Finds the maximum stock price from an array
     * @param prices Array of stock prices
     * @return Maximum price as float
     */
    public static float findMaximumPrice(float[] prices) {
        if (prices == null || prices.length == 0) {
            throw new IllegalArgumentException("Price array cannot be null or empty");
        }
        
        float maxPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > maxPrice) {
                maxPrice = prices[i];
            }
        }
        return maxPrice;
    }
    
    /**
     * Finds the maximum stock price from an ArrayList
     * @param prices ArrayList of stock prices
     * @return Maximum price as float
     */
    public static float findMaximumPrice(ArrayList<Float> prices) {
        if (prices == null || prices.isEmpty()) {
            throw new IllegalArgumentException("Price ArrayList cannot be null or empty");
        }
        
        float maxPrice = prices.get(0);
        for (int i = 1; i < prices.size(); i++) {
            if (prices.get(i) > maxPrice) {
                maxPrice = prices.get(i);
            }
        }
        return maxPrice;
    }
    
    /**
     * Counts the occurrences of a specific price in an array
     * @param prices Array of stock prices
     * @param targetPrice The price to search for
     * @return Number of occurrences
     */
    public static int countOccurrences(float[] prices, float targetPrice) {
        if (prices == null) {
            throw new IllegalArgumentException("Price array cannot be null");
        }
        
        int count = 0;
        for (int i = 0; i < prices.length; i++) {
            // Using Float.compare to handle floating point precision issues
            if (Float.compare(prices[i], targetPrice) == 0) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Counts the occurrences of a specific price in an ArrayList
     * @param prices ArrayList of stock prices
     * @param targetPrice The price to search for
     * @return Number of occurrences
     */
    public static int countOccurrences(ArrayList<Float> prices, float targetPrice) {
        if (prices == null) {
            throw new IllegalArgumentException("Price ArrayList cannot be null");
        }
        
        int count = 0;
        for (int i = 0; i < prices.size(); i++) {
            // Using Float.compare to handle floating point precision issues
            if (Float.compare(prices.get(i), targetPrice) == 0) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * Computes the cumulative sum of stock prices from an ArrayList
     * @param prices ArrayList of stock prices
     * @return New ArrayList containing cumulative sums
     */
    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> prices) {
        if (prices == null || prices.isEmpty()) {
            throw new IllegalArgumentException("Price ArrayList cannot be null or empty");
        }
        
        ArrayList<Float> cumulativeSum = new ArrayList<>();
        float runningSum = 0.0f;
        
        for (int i = 0; i < prices.size(); i++) {
            runningSum += prices.get(i);
            cumulativeSum.add(runningSum);
        }
        
        return cumulativeSum;
    }
    
    /**
     * Utility method to display array contents
     * @param prices Array of stock prices
     */
    public static void displayArray(float[] prices) {
        System.out.print("Array: [");
        for (int i = 0; i < prices.length; i++) {
            System.out.printf("%.1f", prices[i]);
            if (i < prices.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    /**
     * Utility method to display ArrayList contents
     * @param prices ArrayList of stock prices
     */
    public static void displayArrayList(ArrayList<Float> prices) {
        System.out.print("ArrayList: [");
        for (int i = 0; i < prices.size(); i++) {
            System.out.printf("%.1f", prices.get(i));
            if (i < prices.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    /**
     * Main method to demonstrate all functionalities
     */
    public static void main(String[] args) {
        System.out.println("=== STOCK PRICE ANALYSIS PROGRAM ===\n");
        
        // Display original data
        System.out.println("Original Stock Price Data (10 days):");
        displayArray(stockPricesArray);
        displayArrayList(stockPricesArrayList);
        System.out.println();
        
        // 1. Calculate Average Price
        System.out.println("1. AVERAGE STOCK PRICE CALCULATION:");
        System.out.println("-----------------------------------");
        float avgArray = calculateAveragePrice(stockPricesArray);
        float avgArrayList = calculateAveragePrice(stockPricesArrayList);
        System.out.printf("Average price (Array): $%.2f\n", avgArray);
        System.out.printf("Average price (ArrayList): $%.2f\n", avgArrayList);
        System.out.println();
        
        // 2. Find Maximum Price
        System.out.println("2. MAXIMUM STOCK PRICE IDENTIFICATION:");
        System.out.println("--------------------------------------");
        float maxArray = findMaximumPrice(stockPricesArray);
        float maxArrayList = findMaximumPrice(stockPricesArrayList);
        System.out.printf("Maximum price (Array): $%.2f\n", maxArray);
        System.out.printf("Maximum price (ArrayList): $%.2f\n", maxArrayList);
        System.out.println();
        
        // 3. Count Occurrences
        System.out.println("3. OCCURRENCE COUNT ANALYSIS:");
        System.out.println("-----------------------------");
        float targetPrice = 46.3f;
        int occurrencesArray = countOccurrences(stockPricesArray, targetPrice);
        int occurrencesArrayList = countOccurrences(stockPricesArrayList, targetPrice);
        System.out.printf("Target price to search: $%.1f\n", targetPrice);
        System.out.printf("Occurrences in Array: %d times\n", occurrencesArray);
        System.out.printf("Occurrences in ArrayList: %d times\n", occurrencesArrayList);
        System.out.println();
        
        // Test with another price
        targetPrice = 45.5f;
        occurrencesArray = countOccurrences(stockPricesArray, targetPrice);
        occurrencesArrayList = countOccurrences(stockPricesArrayList, targetPrice);
        System.out.printf("Target price to search: $%.1f\n", targetPrice);
        System.out.printf("Occurrences in Array: %d times\n", occurrencesArray);
        System.out.printf("Occurrences in ArrayList: %d times\n", occurrencesArrayList);
        System.out.println();
        
        // 4. Cumulative Sum
        System.out.println("4. CUMULATIVE SUM COMPUTATION:");
        System.out.println("------------------------------");
        ArrayList<Float> cumulativeSum = computeCumulativeSum(stockPricesArrayList);
        System.out.println("Original prices:");
        displayArrayList(stockPricesArrayList);
        System.out.println("Cumulative sum:");
        displayArrayList(cumulativeSum);
        System.out.println();
        
        // Additional Analysis
        System.out.println("5. ADDITIONAL STATISTICAL ANALYSIS:");
        System.out.println("-----------------------------------");
        
        // Find minimum price for comparison
        float minPrice = stockPricesArray[0];
        for (int i = 1; i < stockPricesArray.length; i++) {
            if (stockPricesArray[i] < minPrice) {
                minPrice = stockPricesArray[i];
            }
        }
        
        float priceRange = maxArray - minPrice;
        System.out.printf("Minimum price: $%.2f\n", minPrice);
        System.out.printf("Price range (Max - Min): $%.2f\n", priceRange);
        System.out.printf("Total cumulative value: $%.2f\n", 
                         cumulativeSum.get(cumulativeSum.size() - 1));
        
        System.out.println("\n=== ANALYSIS COMPLETE ===");
    }
}

// No code changes needed. To resolve the error, add this file to a Java project or compile using:
// javac StockPriceAnalyzer.java