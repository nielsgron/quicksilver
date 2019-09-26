package quicksilver.commons.scheduler;

import org.quartz.*;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.simpl.RAMJobStore;
import org.quartz.simpl.SimpleThreadPool;
import org.quartz.spi.JobStore;
import quicksilver.commons.config.ConfigScheduler;

import java.util.HashMap;
import java.util.Map;

public class SchedulerService {

    private static SchedulerService defaultService;
    private static HashMap<String, SchedulerService> services = new HashMap<String, SchedulerService>();

    private Scheduler scheduler;

    public SchedulerService(String schedulerName, int threadCount) {

        try {
            SimpleThreadPool threadPool = new SimpleThreadPool(threadCount, Thread.NORM_PRIORITY);
            JobStore jobStore = new RAMJobStore();
            threadPool.setInstanceName(schedulerName + "-SchedulerThreadPool");

            DirectSchedulerFactory.getInstance().createScheduler(schedulerName, schedulerName + "_ID", threadPool, jobStore);

            scheduler = DirectSchedulerFactory.getInstance().getScheduler(schedulerName);
            scheduler.start();

        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }


    public static void createDefaultInstance(ConfigScheduler config) {

        defaultService = new SchedulerService("DEFAULT", 25);

    }

    public static void createInstance(String name, ConfigScheduler config) {

        services.put(name, new SchedulerService(name, 25));

    }

    public static SchedulerService get() {
        return defaultService;
    }

    public static SchedulerService get(String name) {
        return services.get(name);
    }

    public void scheduleJob(String name, String description, JobRunnable runnable) {

        // Define the Job
        JobDetail job = JobBuilder.newJob(RunnableJob.class)
                .withIdentity(runnable.getJobName(), "main-group")
                .withDescription(runnable.getJobDescription())
                .build();

        job.getJobDataMap().put("Runnable.Instance", runnable);

        // Create the Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(runnable.getJobName() + "-trigger", "main-group")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(runnable.getInterval())
                        .repeatForever())
                .build();

        scheduleJob(job, trigger);
    }

    public void scheduleJob(JobDetail jobDetail, Trigger trigger) {

        // Schedule Job with Trigger
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void shutdown() {

        if ( defaultService != null ) {
            defaultService.shutdownService();
        }
        for ( Map.Entry<String, SchedulerService> entry : services.entrySet() ) {
            entry.getValue().shutdownService();
        }

    }

    public void shutdownService() {
        if ( scheduler != null ) {
            try {
                scheduler.shutdown();
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }
    }

}
