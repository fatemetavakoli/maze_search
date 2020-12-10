
import java.util.ArrayList;


public class Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] cost;
        int[][] arr = {{1,1,1,1,0,0,1,1,1},
                       {1,1,1,1,1,0,1,0,1},
                       {0,1,0,0,1,1,1,1,1},
                       {0,1,1,1,0,1,1,1,1},
                       {1,1,1,1,1,0,0,1,1}};
        TreeMatrix tree = new TreeMatrix();
        int[][] matrix = new int[arr[0].length*arr.length][arr[0].length*arr.length];
        matrix = tree.CreateMatrix(arr);
        //bidirectional search****************************************
        Bidirectional search = new Bidirectional(matrix, arr[0].length*arr.length-1, 0, tree.getInvalid());
        String biString = search.Bidirectional_search();
        if(biString.equals("fail"))
            System.out.println("There is no route from start to goal");
        else{
            System.out.println("bidirectional search");
            tree.PrintMatrix(arr, biString);
        }
        //************************************************************
        //DFS*********************************************************
        DFS dfs = new DFS(matrix, arr[0].length*arr.length-1);
        dfs.setVisited(tree.getInvalid());
        String dfString = dfs.DFS_Search();
        if(biString.equals("fail"))
            System.out.println("There is no route from start to goal");
        else{
            System.out.println("DFS");
            tree.PrintMatrix(arr, dfString);
        }
        
    }
    
}
