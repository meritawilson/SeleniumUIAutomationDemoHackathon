package Perf_package;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class export_Result_to_Excel {
    static String project_path =  System.getProperty("user.dir") + "\\ExcelResults";

    public static void writeHeaders() {
        System.out.println("THE PATH: "+project_path);
        File folder = new File(project_path);// + "\\UI_Automation_Result_Detailed" + java.time.LocalDate.now() + ".csv");
        boolean isCreated = folder.mkdirs();
        System.out.println("Created"+isCreated);
        File file = new File(project_path + "\\UI_Automation_Result_Detailed_WithoutCache_1_Iteration" + java.time.LocalDate.now() + ".csv");
        try {
            FileWriter outputfile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputfile);

            String[] header = { "Requests", "Time_Taken _to_proceed_for_next_userAction(msec)", "Size","No of Requests",
                    "Time to First Byte(msec)","DOMContentLoaded(msec)","Page Rendering Time(msec)","Page Load Time(msec)"
                    };
            writer.writeNext(header);
            writer.close();

            File file1 = new File(
                    project_path + "\\UI_Automation_Result_Summary_test" + java.time.LocalDate.now() + ".csv");
            //	file1.mkdir();
            FileWriter outputfile1 = new FileWriter(file1);
            CSVWriter writer1 = new CSVWriter(outputfile1);
            String[] header1 = { "User_Action", "Time_Taken _to_proceed_for_next_userAction(msec)", "Size", "No_of_Requests",
                    "Time to First Byte(msec)","DOMContentLoaded(msec)",
                    "Page Rendering Time(msec)","Page Load Time(msec)",
                    "Releases","Date","Run" };
            writer1.writeNext(header1);
            writer1.close();

            File file2 = new File(
                    project_path + "\\UI_Automation_Result_HotSpots_withoutCache_1_Iteration" + java.time.LocalDate.now() + ".csv");
            //	file2.mkdir();
            FileWriter outputfile2 = new FileWriter(file2);
            CSVWriter writer2 = new CSVWriter(outputfile2);
            String[] header2 = { "User_Action","Type", "Request ", "Time_Taken(msec)", "Size" };
            writer2.writeNext(header2);
            writer2.close();

            File file3 = new File(
                    project_path + "\\UI_Automation_Result_HotSpots_withCache_1_Iteration" + java.time.LocalDate.now() + ".csv");
            //	file2.mkdir();
            FileWriter outputfile3 = new FileWriter(file3);
            CSVWriter writer3 = new CSVWriter(outputfile3);
            String[] header3 = { "User_Action","Type", "Request ", "Time_Taken(msec)", "Size" };
            writer3.writeNext(header3);
            writer3.close();

            File file4 = new File(
                    project_path + "\\UI_Automation_Result_Detailed_WithCache_1_Iteration" + java.time.LocalDate.now() + ".csv");
            //	file2.mkdir();
            FileWriter outputfile4 = new FileWriter(file4);
            CSVWriter writer4 = new CSVWriter(outputfile4);
            String[] header4 = { "Requests", "Time_Taken _to_proceed_for_next_userAction(msec)", "Size","No of Requests",
                    "Time to First Byte(msec)","DOMContentLoaded(msec)","Page Rendering Time(msec)","Page Load Time(msec)"
            };
            writer4.writeNext(header4);
            writer4.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void export_Detailed_Results(String action, ArrayList name, ArrayList timetaken) {
        File file = new File(project_path + "\\UI_Automation_Result_Detailed_WithoutCache_1_Iteration" + java.time.LocalDate.now() + ".csv");
        try {

            FileWriter outputfile = new FileWriter(file, true);

            CSVWriter writer = new CSVWriter(outputfile);

            double totaltimetaken = NavigateTime.ETETime;
            long totalSize = NavigateTime.totalSize;
            long pageloadtime = NavigateTime.totalpageloadtime;
            long renderTime = NavigateTime.pageRenderTime;
            long TTFB =NavigateTime.networkTime;
            long dom=NavigateTime.DOMContentLoaded;
            String[] Total = { action, "" + totaltimetaken, "" + totalSize, "" + name.size(),
                    "" +TTFB, ""+dom, "" + renderTime, "" +pageloadtime};
            writer.writeNext(Total);
            for (int i = 0; i < name.size(); i++) {

                String[] data1 = { (String) name.get(i), "" + (Double) timetaken.get(i)};//,
                //"" + (Integer) transferSize.get(i) };
                writer.writeNext(data1);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void export_Detailed_Results_withCache(String action, ArrayList name, ArrayList timetaken) {
        File file = new File(project_path + "\\UI_Automation_Result_Detailed_WithCache_1_Iteration" + java.time.LocalDate.now() + ".csv");
        try {

            FileWriter outputfile = new FileWriter(file, true);

            CSVWriter writer = new CSVWriter(outputfile);

            double totaltimetaken = NavigateTime.ETETime;
            long totalSize = NavigateTime.totalSize;
            long pageloadtime = NavigateTime.totalpageloadtime;
            long renderTime = NavigateTime.pageRenderTime;
            long TTFB =NavigateTime.networkTime;
            long dom=NavigateTime.DOMContentLoaded;
            String[] Total = { action, "" + totaltimetaken, "" + totalSize, "" + name.size(),
                    "" +TTFB, ""+dom, "" + renderTime, "" +pageloadtime};
            writer.writeNext(Total);
            for (int i = 0; i < name.size(); i++) {

                String[] data1 = { (String) name.get(i), "" + (Double) timetaken.get(i)};//,
                //"" + (Integer) transferSize.get(i) };
                writer.writeNext(data1);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void export_Summary_results(String action,boolean flag) {

        try {
            double totaltimetaken = NavigateTime.ETETime;
            long totalSize = NavigateTime.totalSize;
            int noOfRequest=NavigateTime.noOfRequests;
            long pageloadtime = NavigateTime.totalpageloadtime;
            long renderTime = NavigateTime.pageRenderTime;
            long NetworkDuration =NavigateTime.networkTime;
            long dom=NavigateTime.DOMContentLoaded;
            ArrayList<Object> Summary=new ArrayList<>();
            Summary.add(action);
            Summary.add(totaltimetaken);
            Summary.add(totalSize);
            Summary.add(noOfRequest);
            if(flag) {
                Summary.add(NetworkDuration);
                Summary.add(dom);
                Summary.add(renderTime);
                Summary.add(pageloadtime);

            }
            Performance.Iteration.add(Summary);
            for(int j=0;j<Performance.Iteration.size();j++) {
                System.out.println("ITERATION PRINT action : "+Performance.Iteration.get(j));
            }
           // System.out.println("ITERATION ACTIONS ARE"+Performance.Iteration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void export_hotspot_results(String action) {
        int indexMax1 = 0, indexMax2 = 0, indexMax3 = 0;
        Double maxV1 = 0.0, maxV2 = 0.0, maxV3 = 0.0;
        for (int i = 0; i < NavigateTime.timeTaken.size(); i++) {
            // only if greater than 100ms
            if (NavigateTime.timeTaken.get(i) > 100) {
                if (NavigateTime.timeTaken.get(i) > maxV1) {
                    maxV3 = maxV2;
                    maxV2 = maxV1;
                    maxV1 = NavigateTime.timeTaken.get(i);

                    indexMax3 = indexMax2;
                    indexMax2 = indexMax1;
                    indexMax1 = i;
                } else if (NavigateTime.timeTaken.get(i) > maxV2) {
                    maxV3 = maxV2;
                    maxV2 = NavigateTime.timeTaken.get(i);

                    indexMax3 = indexMax2;
                    indexMax2 = i;
                } else if (NavigateTime.timeTaken.get(i) > maxV3) {
                    maxV3 = NavigateTime.timeTaken.get(i);

                    indexMax3 = i;
                }
            }
        }

        int sizeIndexMax1 = 0, sizeIndexMax2 = 0, sizeIndexMax3 = 0;
        int sizemaxV1 = 0, sizemaxV2 = 0, sizemaxV3 = 0;
        for (int i = 0; i < NavigateTime.transferSize.size(); i++) {
            // not less than 200kb
            if (NavigateTime.transferSize.get(i) > 200000) {
                if (NavigateTime.transferSize.get(i) > sizemaxV1) {
                    sizemaxV3 = sizemaxV2;
                    sizemaxV2 = sizemaxV1;
                    sizemaxV1 = NavigateTime.transferSize.get(i);

                    sizeIndexMax3 = sizeIndexMax2;
                    sizeIndexMax2 = sizeIndexMax1;
                    sizeIndexMax1 = i;
                } else if (NavigateTime.transferSize.get(i) > sizemaxV2) {
                    sizemaxV3 = sizemaxV2;
                    sizemaxV2 = NavigateTime.transferSize.get(i);

                    sizeIndexMax3 = sizeIndexMax2;
                    sizeIndexMax2 = i;
                } else if (NavigateTime.transferSize.get(i) > sizemaxV3) {
                    sizemaxV3 = NavigateTime.transferSize.get(i);

                    sizeIndexMax3 = i;
                }
            }
        }

        System.out.println(maxV3 + " " + maxV2 + " " + maxV1);
        System.out.println(sizemaxV3 + " " + sizemaxV2 + " " + sizemaxV1);

        File file = new File(project_path + "\\UI_Automation_Result_HotSpots_withoutCache_1_Iteration" + java.time.LocalDate.now() + ".csv");
        try {
            FileWriter outputfile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outputfile);

            if (maxV1 != 0) {
                String[] row3 = { action,"Time", NavigateTime.requestUrl.get(indexMax1), "" + maxV1,
                        "" + NavigateTime.transferSize.get(indexMax1) };
                writer.writeNext(row3);
            }
            if (maxV2 != 0) {
                String[] row2 = { action,"Time", NavigateTime.requestUrl.get(indexMax2), "" + maxV2,
                        "" + NavigateTime.transferSize.get(indexMax2) };
                writer.writeNext(row2);
            }
            if (maxV3 != 0) {
                String[] row1 = { action,"Time", NavigateTime.requestUrl.get(indexMax3), "" + maxV3,
                        "" + NavigateTime.transferSize.get(indexMax3) };
                writer.writeNext(row1);
            }

            if (sizemaxV1 != 0) {
                String[] row6 = { action,"Size", NavigateTime.requestUrl.get(sizeIndexMax1),
                        "" + NavigateTime.timeTaken.get(sizeIndexMax1), "" + sizemaxV1 };
                writer.writeNext(row6);
            }
            if (sizemaxV2 != 0) {
                String[] row5 = { action,"Size", NavigateTime.requestUrl.get(sizeIndexMax2),
                        "" + NavigateTime.timeTaken.get(sizeIndexMax2), "" + sizemaxV2, };
                writer.writeNext(row5);
            }

            if (sizemaxV3 != 0) {
                String[] row4 = { action,"Size", NavigateTime.requestUrl.get(sizeIndexMax3),
                        "" + NavigateTime.timeTaken.get(sizeIndexMax3), "" + sizemaxV3, };
                writer.writeNext(row4);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void export_hotspot_results_withCache(String action) {
        int indexMax1 = 0, indexMax2 = 0, indexMax3 = 0;
        Double maxV1 = 0.0, maxV2 = 0.0, maxV3 = 0.0;
        for (int i = 0; i < NavigateTime.timeTaken.size(); i++) {
            // only if greater than 100ms
            if (NavigateTime.timeTaken.get(i) > 100) {
                if (NavigateTime.timeTaken.get(i) > maxV1) {
                    maxV3 = maxV2;
                    maxV2 = maxV1;
                    maxV1 = NavigateTime.timeTaken.get(i);

                    indexMax3 = indexMax2;
                    indexMax2 = indexMax1;
                    indexMax1 = i;
                } else if (NavigateTime.timeTaken.get(i) > maxV2) {
                    maxV3 = maxV2;
                    maxV2 = NavigateTime.timeTaken.get(i);

                    indexMax3 = indexMax2;
                    indexMax2 = i;
                } else if (NavigateTime.timeTaken.get(i) > maxV3) {
                    maxV3 = NavigateTime.timeTaken.get(i);

                    indexMax3 = i;
                }
            }
        }

        int sizeIndexMax1 = 0, sizeIndexMax2 = 0, sizeIndexMax3 = 0;
        int sizemaxV1 = 0, sizemaxV2 = 0, sizemaxV3 = 0;
        for (int i = 0; i < NavigateTime.transferSize.size(); i++) {
            // not less than 200kb
            if (NavigateTime.transferSize.get(i) > 200000) {
                if (NavigateTime.transferSize.get(i) > sizemaxV1) {
                    sizemaxV3 = sizemaxV2;
                    sizemaxV2 = sizemaxV1;
                    sizemaxV1 = NavigateTime.transferSize.get(i);

                    sizeIndexMax3 = sizeIndexMax2;
                    sizeIndexMax2 = sizeIndexMax1;
                    sizeIndexMax1 = i;
                } else if (NavigateTime.transferSize.get(i) > sizemaxV2) {
                    sizemaxV3 = sizemaxV2;
                    sizemaxV2 = NavigateTime.transferSize.get(i);

                    sizeIndexMax3 = sizeIndexMax2;
                    sizeIndexMax2 = i;
                } else if (NavigateTime.transferSize.get(i) > sizemaxV3) {
                    sizemaxV3 = NavigateTime.transferSize.get(i);

                    sizeIndexMax3 = i;
                }
            }
        }

        System.out.println(maxV3 + " " + maxV2 + " " + maxV1);
        System.out.println(sizemaxV3 + " " + sizemaxV2 + " " + sizemaxV1);

        File file = new File(project_path + "\\UI_Automation_Result_HotSpots_withCache_1_Iteration" + java.time.LocalDate.now() + ".csv");
        try {
            FileWriter outputfile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outputfile);

            if (maxV1 != 0) {
                String[] row3 = { action,"Time", NavigateTime.requestUrl.get(indexMax1), "" + maxV1,
                        "" + NavigateTime.transferSize.get(indexMax1) };
                writer.writeNext(row3);
            }
            if (maxV2 != 0) {
                String[] row2 = { action,"Time", NavigateTime.requestUrl.get(indexMax2), "" + maxV2,
                        "" + NavigateTime.transferSize.get(indexMax2) };
                writer.writeNext(row2);
            }
            if (maxV3 != 0) {
                String[] row1 = { action,"Time", NavigateTime.requestUrl.get(indexMax3), "" + maxV3,
                        "" + NavigateTime.transferSize.get(indexMax3) };
                writer.writeNext(row1);
            }

            if (sizemaxV1 != 0) {
                String[] row6 = { action,"Size", NavigateTime.requestUrl.get(sizeIndexMax1),
                        "" + NavigateTime.timeTaken.get(sizeIndexMax1), "" + sizemaxV1 };
                writer.writeNext(row6);
            }
            if (sizemaxV2 != 0) {
                String[] row5 = { action,"Size", NavigateTime.requestUrl.get(sizeIndexMax2),
                        "" + NavigateTime.timeTaken.get(sizeIndexMax2), "" + sizemaxV2, };
                writer.writeNext(row5);
            }

            if (sizemaxV3 != 0) {
                String[] row4 = { action,"Size", NavigateTime.requestUrl.get(sizeIndexMax3),
                        "" + NavigateTime.timeTaken.get(sizeIndexMax3), "" + sizemaxV3, };
                writer.writeNext(row4);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void getAggregateTiming() {
        System.out.println("Performance.SummaryIterations"+Performance.SummaryIterations);
        //printing first iteration without cache to excel
        for(int l = 0; l<Performance.SummaryIterations.get(0).size(); l++){
            ArrayList operation=(ArrayList)Performance.SummaryIterations.get(0).get(l);
            export_Summary_results(operation,true);
        }
        Performance.SummaryIterations.remove(0);
        System.out.println("Performnace Summary Iterations only with Cache"+Performance.SummaryIterations);
        ArrayList userOperations=Performance.SummaryIterations.get(0);
        //System.out.println(Performance.SummaryIterations.get(0));
      //  System.out.println(userOperations);
        //iterate over the user actions - outer loop
        //No of Iterations -Inner loop
        //bcoz comparing the user actions between the iterations
        for(int i=0;i<userOperations.size();i++) {
            System.out.println("useroperations size "+userOperations.size());
            double max=0.0,secondMax=0.0;
            int secondMaxIndex=0,maxIndex=0;
            ArrayList action=null;

            for(int j=0;j<Performance.SummaryIterations.size();j++) {
                System.out.println("size of perf summary iterations are"+ Performance.SummaryIterations.size());
                action=(ArrayList)Performance.SummaryIterations.get(j).get(i);
                System.out.println("action is "+action);
                double actionTime=(double)action.get(1);
                System.out.println("actionTime is " + actionTime);
                if(actionTime>max) {
                    secondMax=max;
                    secondMaxIndex=maxIndex;
                    max=actionTime;
                    maxIndex=j;
                }
                else if(actionTime>secondMax) {
                    secondMax=actionTime;
                    secondMaxIndex=j;
                }
                System.out.println(max+" maxTime "+secondMax+" secondMax "+secondMaxIndex+" secondMaxIndex");
            }
            ArrayList secondMaxvalue=(ArrayList)Performance.SummaryIterations.get(secondMaxIndex).get(i);
            System.out.println(secondMaxvalue.get(0)+" "+secondMaxvalue.get(1)+" "+secondMaxvalue.get(2));
            export_Summary_results(secondMaxvalue,false);
        }

    }


    public static void export_Summary_results(ArrayList operation,Boolean condition) {
        System.out.println("SUMMARY RESULT CALLED");
        File file = new File(project_path + "\\UI_Automation_Result_Summary_test" + java.time.LocalDate.now() + ".csv");
        try {
            System.out.println(operation);
            FileWriter outputfile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outputfile);
            String action = (String) operation.get(0);
            double totaltimetaken = (double) operation.get(1);
            long totalSize = (long) operation.get(2);
            int noOfRequest = (int) operation.get(3);
            long NetworkDuration = 0L;
            long pageloadtime =0L;
            long renderTime = 0L;
            long dom = 0;
            if (operation.size() > 4) {
                NetworkDuration = (long) operation.get(4);
                dom = (long) operation.get(5);
                renderTime = (long) operation.get(6);
                pageloadtime = (long) (long) operation.get(7);


            }
            String Release = RuntimeProperties.RELEASE;
            String Date = RuntimeProperties.DATE;
            String Run;
            if (condition) {
                 Run="WithoutCACHE";
            }
            else {
                Run = RuntimeProperties.RUN;
            }

            if (operation.size()>4){
            String[] data1 = {action, "" + totaltimetaken, "" + totalSize, "" + noOfRequest, "" +
                    NetworkDuration, "" +
                    dom, "" + renderTime, ""   + pageloadtime, "" + Release, "" + Date, "" + Run};
            System.out.println(action + " " + totaltimetaken + " " + totalSize + " " + noOfRequest + " "
                    + NetworkDuration + " " + dom + " " + renderTime + " "  + pageloadtime + " "
                    + Release + " " + Date + " " + Run);
            writer.writeNext(data1);
            writer.close();}
            else {
                String[] data1 = {action, "" + totaltimetaken, "" + totalSize, "" + noOfRequest, "" +" "+" "+" "+" "+" "+" "+
                         Release, "" + Date, "" + Run};
                System.out.println(action + " " + totaltimetaken + " " + totalSize + " " + noOfRequest + " "
                        + Release + " " + Date + " " + Run);
                writer.writeNext(data1);
                writer.close();}


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}