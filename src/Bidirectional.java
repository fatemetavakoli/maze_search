import java.util.*;

public class Bidirectional {

    private ArrayList<Integer> visited = new ArrayList<>();
    private Queue<String> fromStart = new LinkedList<>();
    private Queue<String> fromGoal = new LinkedList<>();
    private int[][] arr;
    private int goal;
    private int start;
    private int[] invalid;
    
    public Bidirectional(int[][] arr, int goal, int start, int[] invalid) {
        this.arr = arr;
        this.goal = goal;
        this.start = start;
        this.invalid = invalid;
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
    
    private boolean CheckInvalid(int column){//to chech invalid kids
        boolean flag = false;
        for (int invalidNodes = 0; invalidNodes < invalid.length; invalidNodes++) {
            if(invalid[invalidNodes] != column)
                flag = false;
            else{
                flag = true;
                break;
            }
        }
        return flag;
    }
    
    private boolean CheckVisited(int column){
        boolean flag = false;
        for (int rowVisited = 0; rowVisited < visited.size(); rowVisited++)
            if(visited.get(rowVisited) != column)
                flag = false;
            else{
                flag = true;
                break;
            }
        return flag;
    }
    
    private String MakeReverse(String s){
        String[] st = s.split(",");
        String str = "";
        for (int i = 1; i < st.length; i++)
            str = st[i] + "," + str;
        return str;
    }
    
    public String Bidirectional_search(){
        String startKidPath = "";
        String goalKidPath = "";
        String begin = "," + Integer.toString(start);
        fromStart.add(begin);
        String end = "," + Integer.toString(goal);
        fromGoal.add(end);
        if(start == goal)
            return begin + end;
        while(!fromStart.isEmpty() && !fromGoal.isEmpty()){
            String startPath = fromStart.poll();//to store the path from start node to joint node
            String goalPath = fromGoal.poll();//to store the path from goal node to joint node
            int startKid = Last(startPath);//for last node that is visited from start to goal
            int goalKid = Last(goalPath);//for last node that is visited from goal to start
            
            for (int startColumn = 0; startColumn < goal; startColumn++) {//to getting kids from start
                if(arr[startKid][startColumn] == 1 && startKid != startColumn){//to getting kids from start
                    if(!CheckInvalid(startColumn)){
                        for (int goalColumn = 0; goalColumn < goal; goalColumn++) {
                            if(arr[goalKid][goalColumn] == 1 && goalKid != goalColumn){//to getting kids from goal
                                if(!CheckInvalid(goalColumn)){
                                    if(startColumn == goalColumn)//if both paths meet each other
                                        return startPath + "," + Integer.toString(startColumn) + "," + MakeReverse(goalPath);
                                }
                                if(!CheckVisited(goalColumn)){//check if the node was visited in last loop
                                    goalKidPath = goalPath + "," + Integer.toString(goalColumn);
                                    fromGoal.add(goalKidPath);
                                }
                            }
                        }
                        startKidPath = startPath + "," + Integer.toString(startColumn);
                        fromStart.add(startKidPath);
                    }
                }
            }
            visited.clear();
        }
        return "fail";
    }
   
}
