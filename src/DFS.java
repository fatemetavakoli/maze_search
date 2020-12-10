
import java.util.ArrayList;
import java.util.Stack;


public class DFS {
    private ArrayList<Integer> visited = new ArrayList<>();
    private Stack<String> stack = new Stack<>();
    private int[][] arr;
    private int goal;
    private boolean flag;
    private boolean goalFlag = false;
    public DFS(int[][] arr, int goal) {
        this.arr = arr;
        this.goal = goal;
    }
    
    private int Last(String str){
        String findInteger = "";
        for (int i = str.length()-1; i >= 0; i--) {
            if(str.charAt(i) != ',')
                findInteger = str.charAt(i)+findInteger;
            else
                break;
        }
        return Integer.parseInt(findInteger);
    }
    
    public String DFS_Search(){
        stack.push("," + Integer.toString(0));//pushing the start node in stack
        String kidpath = ",0";//to save the solution path
        while(!stack.empty() && Last(kidpath) != goal){
            String path = stack.pop();//last node path that we visited
            int kid = Last(path);//last node that we visited
            visited.add(kid);//to not visit the visited node again and prevent from loop
            for (int column = 0; column <= goal; column++) {
                if(arr[kid][column] == 1 && kid != column){//if the node is kid
                    for (int rowVisited = 0; rowVisited < visited.size(); rowVisited++)//to check whether the kid is valid or not
                        if(visited.get(rowVisited) != column)
                            flag = false;
                        else{
                            flag = true;
                            break;
                        }
                    if(!flag){//if node is vaild kid
                        kidpath = path + "," + Integer.toString(column);//add the valid kid to the solution path
                        stack.push(kidpath);
                    }
                }
            }
        }
        if(stack.empty())
            return "fail";
        kidpath += ",";
        //System.out.println("DFS: "+kidpath);
        return kidpath;
        
    }
    
    public ArrayList<Integer> getVisited() {
        return visited;
    }

    public void setVisited(int[] visited) {
        for (int i = 0; i < visited.length; i++) {
            this.visited.add(visited[i]);
        }
    }
}
