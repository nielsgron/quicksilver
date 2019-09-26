package quicksilver.commons.scheduler;

import java.util.ArrayList;
import java.util.Date;

public interface JobRunnable extends Runnable {

    public String getJobName();

    public String getJobDescription();

    public int getInterval();

    public ArrayList<String> getLogs();

}
