package io.github.lucariatias.harmonicmoon.job;

import java.util.HashMap;
import java.util.Map;

import static io.github.lucariatias.harmonicmoon.job.Job.*;

public class JobManager {

    private Map<String, Job> jobs = new HashMap<>();

    public JobManager() {
        addJob(FIRE_MAGE);
        addJob(WATER_MAGE);
        addJob(LIGHT_MAGE);
        addJob(DARK_MAGE);
        addJob(DRUID);
        addJob(RANGER);
        addJob(WARRIOR);
        addJob(MARTIAL_ARTIST);
        addJob(GUARDIAN);
        addJob(BARBARIAN);
        addJob(SPEARMAN);
        addJob(ASSASSIN);
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
