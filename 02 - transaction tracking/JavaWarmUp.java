package comp210.assn02;

import java.util.Scanner;

public class JavaWarmUp {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = Integer.parseInt(scan.nextLine());
        String[][] input = new String[num][7];

        for(int i = 0; i < num; i++) {
            input[i] = scan.nextLine().split("\\s+");
        }

        sales(input);
        averages(input, "book");
        averages(input, "jewelry");
        averages(input, "phone");
        scan.close();
    }
    
    public static void sales(String[][] arr) {
        double maxSale = Double.MIN_VALUE;
        double minSale = Double.MAX_VALUE;
        int maxItem = 0;
        int minItem = 0;

        for(int i = 0; i < arr.length; i++) {
            double price = Double.parseDouble(arr[i][3]);
            if (price >= maxSale) {
                maxSale = price;
                maxItem = i;
            }
            if (price <= minSale) {
                minSale = price;
                minItem = i;
            }
        }

        System.out.println("Highest per unit sale:" + "\n" + "\t" + "When: " + arr[maxItem][0] + " " + arr[maxItem][1] + "\n" + "\t" + "Category: " + arr[maxItem][2] + "\n" + "\t" + "Price: " + String.format("%.2f", Double.parseDouble(arr[maxItem][3])) + "\n" + "\t" + "Rating: " + Double.parseDouble(arr[maxItem][5]));
        System.out.println("Lowest per unit sale:" + "\n" + "\t" + "When: " + arr[minItem][0] + " " + arr[minItem][1] + "\n" + "\t" + "Category: " + arr[minItem][2] + "\n" + "\t" + "Price: " + String.format("%.2f", Double.parseDouble(arr[minItem][3])) + "\n" + "\t" + "Rating: " + Double.parseDouble(arr[minItem][5]));
    }

    public static void averages(String[][] arr, String category) {
            double toAvg = 0.0;
            int quantity = 0;
            double price = 0.0;
            double rating = 0.0;
            double duration = 0.0;

            for (int i = 0; i < arr.length; i++) {
                if(arr[i][2].equals(category)) {
                    toAvg++;
                    quantity += Integer.parseInt(arr[i][4]);
                    price += Double.parseDouble(arr[i][4]) * Double.parseDouble(arr[i][3]);
                    rating += Double.parseDouble(arr[i][5]);
                    duration += Double.parseDouble(arr[i][6]);
                }
            }
            
            System.out.println("Averages by " + category + "\n" + "\t" + "Quantity: " + quantity  + "\n" + "\t" + "Price: " + String.format("%.2f", price / quantity) + "\n" + "\t" + "Rating: " + String.format("%.2f", rating / toAvg) + "\n" + "\t" + "Duration: " + String.format("%.2f", duration / toAvg));
    }
}
