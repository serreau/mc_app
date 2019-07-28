package sero.com.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;

import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;
import sero.com.data.entities.Job;
import sero.com.ui.R;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    public List<Job> jobs;

    @BindString(R.string.title1) String title1;
    @BindString(R.string.title2) String title2;

    public JobAdapter(Context context, List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public JobAdapter.JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        ButterKnife.bind(this, view);

        JobViewHolder jobviewholder = new JobViewHolder(view);
        view.setOnClickListener( v -> {
            Bundle b = new Bundle();
            b.putString("title", jobviewholder.getTitle());
            Navigation.findNavController(view).navigate(R.id.action_searchFragment_to_detailsFragment, b);
        });

        return jobviewholder;
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        holder.setTitle(title1+" "+jobs.get(position).getTitle1()+" "+title2+" "+jobs.get(position).getTitle2());
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

        public JobViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title_card);
        }

        public void setTitle(String text) {
            this.title.setText(text);
        }
        public String getTitle() {
            return this.title.getText().toString();
        }
    }
}