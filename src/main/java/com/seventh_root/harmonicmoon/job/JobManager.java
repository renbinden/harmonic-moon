package com.seventh_root.harmonicmoon.job;

import java.util.HashMap;
import java.util.Map;

public class JobManager {

    private Map<String, Job> jobs = new HashMap<>();

    public JobManager() {
        addJob(Job.FIRE_MAGE);
        addJob(Job.WATER_MAGE);
        addJob(Job.LIGHT_MAGE);
        addJob(Job.DARK_MAGE);
        addJob(Job.DRUID);
        addJob(Job.RANGER);
        addJob(Job.WARRIOR);
        addJob(Job.MARTIAL_ARTIST);
        addJob(Job.GUARDIAN);
        addJob(Job.BARBARIAN);
        addJob(Job.SPEARMAN);
        addJob(Job.ASSASSIN);
    }

    public Job getJob(String name) {
        return jobs.get(name.toLowerCase().replace(" ", "").replace("_", ""));
    }

    public void addJob(Job job) {
        jobs.put(job.getName().toLowerCase().replace(" ", "").replace("_", ""), job);
    }

    public void removeJob(Job job) {
        jobs.remove(job.getName().toLowerCase().replace(" ", "").replace("_", ""));
    }

}
