package Perf_package;

import java.util.ArrayList;

public class Steps {
//   public static Boolean publishToExcel=false;
   public static int last_action_num=RuntimeProperties.TOTAL_ACTIONS;
   public static int last_iteration =RuntimeProperties.TOTAL_ITERATIONS;
    public static void theMain(String action, ArrayList requestsList, String timingList, int iteration,int action_num) throws InterruptedException {
       // Performance.Iteration = new ArrayList<ArrayList>();
        if(action_num==1){
            Performance.Iteration = new ArrayList<ArrayList>();
        }
        if(iteration==0 && action_num==1) {
            System.out.println("AddExcelHeaders called ");
            export_Result_to_Excel.writeHeaders();
        }
        NavigateTime.theMain(action,requestsList,timingList,iteration,action_num);
        if(action_num==last_action_num){
            Performance.SummaryIterations.add(Performance.Iteration);
            System.out.println(Performance.SummaryIterations);
        }
    }
    public static void finalcall(int currentCount){
        if(currentCount==last_iteration){
            export_Result_to_Excel.getAggregateTiming();
        }
    }
    }