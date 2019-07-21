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
    public List<Job> jobs;

    public JobAdapter(Context context, List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public JobAdapter.JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        return new JobViewHolder(v);
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        holder.setTitle(jobs.get(position).getTitle());
        holder.setDescription(jobs.get(position).getDescription());
        holder.setDate(jobs.get(position).getStart());
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
        public TextView title;
        public TextView description;
        public TextView date;

        public JobViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title_card);
            description = v.findViewById(R.id.description_card);
            date = v.findViewById(R.id.date_card);
        }

        public void setTitle(String text) {
            this.title.setText(text);
        }
        public void setDescription(String text) {
            this.description.setText(text);
        }
        public void setDate(String text) {
            this.date.setText(text);
        }
    }
}