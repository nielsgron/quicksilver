package quicksilver.commons.scheduler;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RunnableJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
        Runnable runnable = (Runnable)data.get("Runnable.Instance");

        if ( runnable != null ) {
            runnable.run();
        }

    }

}
