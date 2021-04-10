package ru.veryprosto.naumenTask;

public class TaskMain {
    public static void main(String[] args) {
        RouteFinder routeFinder = new RouteFinderImpl();
        char[][] outputArr = routeFinder.findRoute(inputArr);
        printArr(outputArr);
    }
    private static void printArr(char[][] arr) {
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[y].length; x++) {
                System.out.print(arr[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println("__________");
    }

    public static char[][] inputArr = {
            {'#', '#', '#', '#', '@'},
            {'.', '.', '.', '#', '.'},
            {'.', '.', '.', '#', '.'},
            {'.', '#', '.', '.', '.'},
            {'.', '#', '.', '#', '#'},
            {'.', '.', '.', '.', '.'},
            {'.', '#', '#', '#', 'X'}
    };
}
