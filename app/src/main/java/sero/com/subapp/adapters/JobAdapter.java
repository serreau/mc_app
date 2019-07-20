package sero.com.subapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sero.com.entities.Job;
import sero.com.subapp.R;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    private final LayoutInflater mInflater;
    public List<Job> jobs;

    public JobAdapter(Context context, List<Job> jobs) {
        mInflater = LayoutInflater.from(context);
        this.jobs = jobs;
    }

    @Override
    public JobAdapter.JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new JobViewHolder(v);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        holder.setText(jobs.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public void setJobs(List<Job> jobs){
        this.jobs = jobs;
        notifyDataSetChanged();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public JobViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.item_recycler_home);
        }

        public void setText(String text) {
            this.textView.setText(text);
        }
    }
}