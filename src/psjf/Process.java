package psjf;

public class Process {
    int id;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    GlobalTimer globalTimer;

    public Process(int id,int arrivalTime, int burstTime, GlobalTimer globalTimer){
        this.id=id;
        this.arrivalTime=arrivalTime;
        this.burstTime=burstTime;
        this.remainingTime=burstTime;
        this.globalTimer=globalTimer;
    }

    public void runProcess(){
        for(int i=0;i<burstTime;i++){
            System.out.printf("Executing Process ID: " + id);
            System.out.printf("----");
            System.out.println("Global Time: " + globalTimer.time);
            remainingTime--;
            globalTimer.time++;
        }

        System.out.println("********Process Id: "+id + " completed its job********");
    }
    public int getId() {
        return id;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public int getRemainingTime() {
        return remainingTime;
    }
    public int getBurstTime() {
        return burstTime;
    }
    public GlobalTimer getGlobalTimer() {
        return globalTimer;
    }
}
