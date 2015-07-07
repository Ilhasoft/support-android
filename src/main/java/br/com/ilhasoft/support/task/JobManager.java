package br.com.ilhasoft.ilhaandroid.task;

/**
 * Created by johndalton on 03/12/14.
 */
public class JobManager {

    public void executeJobsInQueue(Job... jobs) {
        for(int i = 0; i < jobs.length; i++) {
            if(i < jobs.length-1) {
                Job job = jobs[i];
                job.setNextJob(jobs[i+1]);
            }
        }

        Job job = jobs[0];
        job.execute();
    }

    public void executeJobsInPool(Job... jobs) {
        for(Job job : jobs) {
            job.execute();
        }
    }

}
