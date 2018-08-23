package quicksilver.commons.job;

import java.util.ArrayList;
import java.util.Date;

public abstract class JobAbstract extends Thread {

    private String SLEEPING = "Sleeping";
    private String RUNNING = "Running";

    private ArrayList<String> logs = new ArrayList<String>();
    private Date startDate;
    private String status = SLEEPING;

    public JobAbstract() {
        startDate = new Date(System.currentTimeMillis());
    }

    public String getJobName() {
        return "Default Job";
    }

    public String getJobDescription() {
        return "Default Job which just loops";
    }

    public String getJobStatus() {
        return status;
    }

    public Date getStartDateTime() {
        return startDate;
    }

    public long getSleepInterval() {
        return 10 * 1000; // 10 Seconds
    }

    public ArrayList<String> getLogs() {
        return logs;
    }

    protected void doOperation() {
        // To be implemented
        // Do something ...
    }

    public void run() {

        while ( true ) {

            if ( isInterrupted() ) {
                break;
            }

            try {
                status = SLEEPING;
                Thread.sleep(getSleepInterval());
            } catch ( Exception e ) {
                e.printStackTrace();
            } finally {
                status = RUNNING;
            }

            if ( isInterrupted() ) {
                break;
            }

            doOperation();

        }

    }

}
