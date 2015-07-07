package br.com.ilhasoft.ilhaandroid.task;

import android.app.Activity;
import android.os.AsyncTask;

/**
 * Created by johndalton on 03/12/14.
 */
public abstract class Job<PreviousParam, ProgressParam, NextParam> {

    private PreviousParam previousParam;
    private OnProgressUpdated<ProgressParam> onProgressUpdated;
    private Activity activity;

    public Job() {}

    public Job(PreviousParam previousParam) {
        this.previousParam = previousParam;
    }

    private Job<NextParam, ?, ?> nextJob;
    private OnJobFinished<NextParam> onJobFinished;

    public void execute() {
        jobTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, previousParam);
    }

    public abstract NextParam onRun(PreviousParam previousParam);

    private void onAfterRun(NextParam nextParam) {
        if(nextJob != null) {
            nextJob.setPreviousParam(nextParam);
            nextJob.execute();
        }
    }

    public void publishProgress(final ProgressParam progress) {
        if(onProgressUpdated != null && activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    onProgressUpdated.onProgressUpdated(progress);
                }
            });
        }
    }

    public void setNextJob(Job<NextParam, ?, ?> nextJob) {
        this.nextJob = nextJob;
    }

    private AsyncTask<PreviousParam, Void, NextParam> jobTask = new AsyncTask<PreviousParam, Void, NextParam>() {
        @Override
        protected NextParam doInBackground(PreviousParam... params) {
            return onRun(params.length > 0 ? params[0] : null);
        }

        @Override
        protected void onPostExecute(NextParam nextParam) {
            super.onPostExecute(nextParam);
            if (onJobFinished != null) {
                onJobFinished.onJobFinished(nextParam);
            }

            onAfterRun(nextParam);
        }
    };

    public interface OnProgressUpdated<ProgressParam> {
        public void onProgressUpdated(ProgressParam progress);
    }

    protected boolean isCancelled() {
        return jobTask.isCancelled();
    }

    public void setOnJobFinished(OnJobFinished<NextParam> onJobFinished) {
        this.onJobFinished = onJobFinished;
    }

    private void setPreviousParam(PreviousParam previousParam) {
        this.previousParam = previousParam;
    }

    public void addOnProgressUpdated(Activity activity, OnProgressUpdated<ProgressParam> onProgressUpdated) {
        this.activity = activity;
        this.onProgressUpdated = onProgressUpdated;
    }
}