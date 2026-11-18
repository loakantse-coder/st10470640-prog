package com.mycompany.productssalesapplication;

import java.util.Scanner;

interface IProduct {
    int GetTotalProductsSold();
    double GetAverageProductSold();
    String GetMostPopularProduct();
}

class Product implements IProduct {
    private String[] productNames = {
        "Cosy Nights Monarch mattress", 
        "Restonic Barbados mattress", 
        "Sealy Phuket mattress"
    };

    private int[][] salesData;

    public Product(int[][] salesData) {
        this.salesData = salesData;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int i = 0; i < salesData.length; i++) {
            for (int j = 0; j < salesData[i].length; j++) {
                total += salesData[i][j];
            }
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        int totalEntries = 0;
        int totalSales = 0;

        for (int i = 0; i < salesData.length; i++) {
            for (int j = 0; j < salesData[i].length; j++) {
                totalSales += salesData[i][j];
                totalEntries++;
            }
        }

        if (totalEntries == 0) return 0.0;
        return (double) totalSales / totalEntries;
    }

    @Override
    public String GetMostPopularProduct() {
        int maxSales = -1;
        int bestProductIndex = 0;

        for (int i = 0; i < salesData.length; i++) {
            int productTotal = 0;
            for (int j = 0; j < salesData[i].length; j++) {
                productTotal += salesData[i][j];
            }
            if (productTotal > maxSales) {
                maxSales = productTotal;
                bestProductIndex = i;
            }
        }

        String shortName = productNames[bestProductIndex].replace(" mattress", "");
        return shortName + " (" + maxSales + ")";
    }
}

class PrintProduct {
    private IProduct product;

    public PrintProduct(IProduct product) {
        this.product = product;
    }

    public void printReport() {
        System.out.println("PRODUCT SALES REPORT - 2026");
        System.out.println("**************************************************");
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
        System.out.println("AVERAGE PRODUCT SALES: " + Math.round(product.GetAverageProductSold()));
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
        System.out.println("**************************************************");
    }
}

public class ProductsSalesApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] productNames = {
            "Cosy Nights Monarch mattress", 
            "Restonic Barbados mattress", 
            "Sealy Phuket mattress"
        };
        
        int[][] salesData = new int[3][3];

        for (int i = 0; i < productNames.length; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print("Enter number of sales for month " + (j + 1) + " for " + productNames[i] + ": ");
                salesData[i][j] = scanner.nextInt();
            }
            System.out.println();
        }

        Product productObj = new Product(salesData);
        PrintProduct printer = new PrintProduct(productObj);
        printer.printReport();
        
        scanner.close();
    }
}