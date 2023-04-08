package psjf;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PSJF {
    static PriorityQueue<Process> processQueue = new PriorityQueue<Process>(10, new
            Comparator<Process>() {
                public int compare(Process process1, Process process2) {
                    return (int)(process1.getRemainingTime()-process2.getRemainingTime());
                }
            });

    static PriorityQueue<Process> readyQueue = new PriorityQueue<Process>(10, new
            Comparator<Process>() {
                public int compare(Process process1, Process process2) {
                    //comparing both remaining time arrival time
                    return (int)((process1.getRemainingTime()-process2.getRemainingTime()) *(process1.getArrivalTime()-process2.getArrivalTime()));
                }
            });
    static GlobalTimer globalTimer = new GlobalTimer(0);

    public static void main(String[] args) {
        processQueue.add(new Process(1,0,2,globalTimer));
        processQueue.add(new Process(2,2,1,globalTimer));
        processQueue.add(new Process(3,1,8,globalTimer));
        processQueue.add(new Process(4,5,5,globalTimer));
        processQueue.add(new Process(5,4,5,globalTimer));

        System.out.println("Starting");
        while(true){
            while(!processQueue.isEmpty() && processQueue.element().getRemainingTime()>
                    globalTimer.time){

                readyQueue.add(processQueue.poll());
            }

            if(!readyQueue.isEmpty())
                runProcessInCpu();
            else {

                System.out.println("No process is in Ready Queue");
                System.out.println("Global time: "+globalTimer.time);
                globalTimer.time++;
                break;

            }
        }
    }

    public static boolean checkIfNewProcessArrived(){

        if(!processQueue.isEmpty()){

            if(processQueue.element().getArrivalTime()<globalTimer.time)
                return true;
        }
        return false;
    }

    public static void runProcessInCpu(){
        Process process = readyQueue.poll();
        process.runProcess();
    }
}

