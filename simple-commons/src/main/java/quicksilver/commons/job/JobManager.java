package quicksilver.commons.job;

import java.util.ArrayList;

public class JobManager {

    private static JobManager INSTANCE = new JobManager();

    private ArrayList<JobAbstract> jobList = new ArrayList<JobAbstract>();

    public JobManager() {

    }

    public static JobManager getInstance() {
        return INSTANCE;
    }

    public ArrayList<JobAbstract> getJobList() {
        return jobList;
    }

    public void startTask(JobAbstract job) {

        job.start();
        jobList.add(job);

    }

}
