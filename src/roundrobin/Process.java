package roundrobin;

import static roundrobin.RoundRobin.quantumTime;

public class Process {
    int id;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    int priority;
    GlobalTimer globalTimer;

    public Process(int id,int arrivalTime, int burstTime, int priority, GlobalTimer globalTimer){
        this.id=id;
        this.arrivalTime=arrivalTime;
        this.burstTime=burstTime;
        this.priority=priority;
        this.remainingTime=burstTime;
        this.globalTimer=globalTimer;
    }

    public void runProcess(){
        for (int i = 0; i < quantumTime; i++) {
            System.out.printf("Executing Process ID: " + id);
            System.out.printf("----");
            System.out.println("Global Time: " + globalTimer.time);
            remainingTime--;
            globalTimer.time++;
            if(remainingTime == 0){
                break;
            }
        }
        System.out.println("********Process Id: "+id + " completed its job********");
    }


    public int getId() {
        return id;
    }
    public int getArrivalTime() {
        return remainingTime;
    }

    public int getPriority(){
        return priority;
    }
    public int getRemainingTime(){
        return remainingTime;
    }
    public int getBurstTime() {
        return burstTime;
    }
    public GlobalTimer getGlobalTimer() {
        return globalTimer;
    }
}