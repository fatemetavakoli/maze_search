import java.util.*;

public class TreeMatrix {//include invalid nodes and converting input route to tree route and print final version of path in input route
    
    private ArrayList<Integer> invalid = new ArrayList<>();//to save invalid nodes
    
    public int[][] CreateMatrix(int[][] arr ){//change input route to tree route
        int counter = 0;//to count the size of tree
        for (int row = 0; row < arr.length; row++) 
            for (int column = 0; column < arr[0].length; column++){
                if(arr[row][column] == 0){
                    arr[row][column] = counter;
                    invalid.add(arr[row][column]);
                    counter++;
                }
                else{
                    arr[row][column] = counter;
                    counter++;
                }
            }
        
        int[][] matrix = new int[counter][counter];
        for (int row = 0; row < arr.length; row++) {
            for (int column = 0; column < arr[0].length; column++) {
                matrix[arr[row][column]][arr[row][column]] = 1;
                if(row-1>=0 && row+1<arr.length && column-1>=0 && column+1<arr[0].length){//all
                    matrix[arr[row][column]][arr[row][column-1]] = 1;
                    matrix[arr[row][column]][arr[row][column+1]] = 1;
                    matrix[arr[row][column]][arr[row-1][column]] = 1;
                    matrix[arr[row][column]][arr[row+1][column]] = 1;
                    matrix[arr[row][column-1]][arr[row][column]] = 1;
                    matrix[arr[row][column+1]][arr[row][column]] = 1;
                    matrix[arr[row-1][column]][arr[row][column]] = 1;
                    matrix[arr[row+1][column]][arr[row][column]] = 1;
                }
                else if(row-1>=0 && row+1==arr.length && column-1>=0 && column+1<arr[0].length){//1
                    matrix[arr[row][column]][arr[row-1][column]] = 1;
                    matrix[arr[row][column]][arr[row][column+1]] = 1;
                    matrix[arr[row][column]][arr[row][column-1]] = 1;
                    matrix[arr[row-1][column]][arr[row][column]] = 1;
                    matrix[arr[row][column+1]][arr[row][column]] = 1;
                    matrix[arr[row][column-1]][arr[row][column]] = 1;
                }
                else if(row-1>=0 && row+1==arr.length && column-1>=0 && column+1>=arr[0].length){//2
                    matrix[arr[row][column]][arr[row-1][column]] = 1;
                    matrix[arr[row][column]][arr[row][column-1]] = 1;
                    matrix[arr[row-1][column]][arr[row][column]] = 1;
                    matrix[arr[row][column-1]][arr[row][column]] = 1;
                }
                else if(row-1>=0 && row+1<arr.length && column-1>=0 && column+1>=arr[0].length){//3
                    matrix[arr[row][column]][arr[row+1][column]] = 1;
                    matrix[arr[row][column]][arr[row-1][column]] = 1;
                    matrix[arr[row][column]][arr[row][column-1]] = 1;
                    matrix[arr[row+1][column]][arr[row][column]] = 1;
                    matrix[arr[row-1][column]][arr[row][column]] = 1;
                    matrix[arr[row][column-1]][arr[row][column]] = 1;
                }
                else if(row-1<0 && row+1<arr.length && column-1>=0 && column+1>=arr[0].length){//4
                    matrix[arr[row][column]][arr[row+1][column]] = 1;
                    matrix[arr[row][column]][arr[row][column-1]] = 1;
                    matrix[arr[row+1][column]][arr[row][column]] = 1;
                    matrix[arr[row][column-1]][arr[row][column]] = 1;
                }
                else if(row-1<0 && row+1<arr.length && column-1>=0 && column+1<arr[0].length){//5
                    matrix[arr[row][column]][arr[row+1][column]] = 1;
                    matrix[arr[row][column]][arr[row][column+1]] = 1;
                    matrix[arr[row][column]][arr[row][column-1]] = 1;
                    matrix[arr[row+1][column]][arr[row][column]] = 1;
                    matrix[arr[row][column+1]][arr[row][column]] = 1;
                    matrix[arr[row][column-1]][arr[row][column]] = 1;
                }
                else if(row-1<0 && row+1<arr.length && column-1<0 && column+1<arr[0].length){//6
                    matrix[arr[row][column]][arr[row+1][column]] = 1;
                    matrix[arr[row][column]][arr[row][column+1]] = 1;
                    matrix[arr[row+1][column]][arr[row][column]] = 1;
                    matrix[arr[row][column+1]][arr[row][column]] = 1;
                }
                else if(row-1>=0 && row+1<arr.length && column-1<0 && column+1<arr[0].length){//7
                    matrix[arr[row][column]][arr[row+1][column]] = 1;
                    matrix[arr[row][column]][arr[row-1][column]] = 1;
                    matrix[arr[row][column]][arr[row][column+1]] = 1;
                    matrix[arr[row+1][column]][arr[row][column]] = 1;
                    matrix[arr[row-1][column]][arr[row][column]] = 1;
                    matrix[arr[row][column+1]][arr[row][column]] = 1;
                }
                else if(row-1>=0 && row+1>=arr.length && column-1<0 && column+1<arr[0].length){//8
                    matrix[arr[row][column]][arr[row-1][column]] = 1;
                    matrix[arr[row][column]][arr[row][column+1]] = 1;
                    matrix[arr[row-1][column]][arr[row][column]] = 1;
                    matrix[arr[row][column+1]][arr[row][column]] = 1;
                }
            }
        }
        return matrix;
    }
    
    public void PrintMatrix(int[][] arr, String str){//to print the solution maze
        int count = 0;
        ArrayList<Integer> route = new ArrayList<>();//to turn the route number in to the path that can show it in maze
        String[] path;// to convert string path in to the route number
        for (int row = 0; row < arr.length; row++) {
            for (int column = 0; column < arr[0].length; column++) {
                arr[row][column] = count;//to set the number of each node in to the node
                count++;
                route.add(arr[row][column]);
            }
        }
        path = str.split(",");
        for (int i = 1; i < path.length; i++)
            route.set(Integer.parseInt(path[i]), 0);//each node that is in path, turn it in to 0
        int routeRow = 0;
        int invalidRow = 0;
        for (int row = 0; row < arr.length; row++) {
            for (int column = 0; column < arr[0].length; column++) {
                if(route.get(routeRow) == 0){
                    System.out.print("o ");
                    routeRow++;
                }
                else{
                    System.out.print("x ");
                    routeRow++;
                }
            }
            System.out.println("");
        }
        System.out.print("The cost is: ");
        System.out.println(path.length-1);
        System.out.println("");
    }

    public int[] getInvalid() {
        int[] invalidArray = new int[invalid.size()];
        for (int invalidRow = 0; invalidRow < invalid.size(); invalidRow++) {
            invalidArray[invalidRow] = invalid.get(invalidRow);
        }
        return invalidArray;
    }

    public void setInvalid(ArrayList<Integer> invalid) {
        this.invalid = invalid;
    }
    
    
}
