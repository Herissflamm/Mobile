package fr.mds.helloworld.utils;

import android.os.AsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncRunner {
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    public void runTask(Runnable task) {
        executor.submit(task);
    }
}
