package com.bilingoal.virustracker.utils.schedulerproviders;

import io.reactivex.rxjava3.core.Scheduler;

public interface SchedulerProvider {
    Scheduler io();
    Scheduler ui();
}
