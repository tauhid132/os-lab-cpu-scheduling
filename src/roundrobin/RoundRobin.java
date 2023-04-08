package roundrobin;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RoundRobin {
    static PriorityQueue<Process> processQueue = new PriorityQueue<Process>(10, new
            Comparator<Process>() {
                public int compare(Process process1, Process process2) {
                    //considering both priority and arrival time
                    return (int)((process1.getPriority()-process2.getPriority()) *(process1.getArrivalTime()-process2.getArrivalTime()));
                }

            });

    static PriorityQueue<Process> readyQueue = new PriorityQueue<Process>(10, new
            Comparator<Process>() {
                public int compare(Process process1, Process process2) {
                    return (int)(process1.getPriority()-process2.getPriority());
                }
            });
    static GlobalTimer globalTimer = new GlobalTimer(0);
    static int quantumTime = 4;

    public static void main(String[] args) {

        processQueue.add(new Process(1,0,2,2,globalTimer));
        processQueue.add(new Process(2,2,1,1,globalTimer));
        processQueue.add(new Process(3,1,8,4,globalTimer));
        processQueue.add(new Process(4,5,4,2,globalTimer));
        processQueue.add(new Process(5,4,5,3,globalTimer));


        while(true){
            while(!processQueue.isEmpty()){
                if(checkRemainingTime()){
//                    System.out.println(processQueue.element().getRemainingTime());
                    readyQueue.add(processQueue.poll());
                }

            }
            if(readyQueue.isEmpty()){
                System.out.println("No process is in Process Queue");
                System.out.println("Global time: "+globalTimer.time);
                globalTimer.time++;
                break;
            }else{
                while(!readyQueue.isEmpty()){
                    runProcessInCpu();
                }
            }
        }
    }

    public static boolean checkRemainingTime(){

        if(!processQueue.isEmpty()){

            if(processQueue.element().getRemainingTime() > 0)
                return true;
        }
        return false;
    }

    public static void runProcessInCpu(){
        Process process = readyQueue.poll();
        process.runProcess();
        if(!(process.remainingTime == 0)) {
           processQueue.add(process);
        }


    }
}
