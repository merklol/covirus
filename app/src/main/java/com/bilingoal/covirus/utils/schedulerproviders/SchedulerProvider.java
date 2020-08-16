package com.bilingoal.covirus.utils.schedulerproviders;

import io.reactivex.rxjava3.core.Scheduler;

public interface SchedulerProvider {
    Scheduler io();
    Scheduler ui();
}
