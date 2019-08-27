package sero.com.ui.adapter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.navigation.Navigation;

import java.util.List;
import java.util.ListIterator;

import butterknife.ButterKnife;
import sero.com.data.entities.Job;
import sero.com.data.entities.User;
import sero.com.ui.R;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> {
    public List<Job> jobs;
    public List<User> users;


    public JobAdapter(List<Job> jobs, List<User> users) {
        this.jobs = jobs;
        this.users = users;
    }

    @Override
    public JobAdapter.JobViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, parent, false);
        ButterKnife.bind(this, view);

        JobViewHolder jobviewholder = new JobViewHolder(view);
        view.setOnClickListener( v -> {
            Bundle b = new Bundle();
            b.putLong("id", jobviewholder.getJob().getId());
            Navigation.findNavController(view).navigate(R.id.action_kanbanViewPagerFragment_to_detailViewPagerFragment, b);
        });

        return jobviewholder;
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        Job job = jobs.get(position);
        holder.setJob(job);
        holder.setUser(getUserByJob(job));
        holder.setName();
    }

    private User getUserByJob(Job job) {
        ListIterator<User> it = users.listIterator();
        while (it.hasNext()){
            User user = it.next();
            if(job.getOwner().equals(user.getPhone()))
                return user;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public void setJobs(List<Job> jobs){
        this.jobs = jobs;
        notifyDataSetChanged();
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        private Job job;
        private User user;

        public JobViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name_card);
        }

        public void setJob(Job job) {
            this.job = job;
        }

        public Job getJob() {
            return this.job;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public void setName(){
            if(user == null || job == null  ) {
                name.setText("Informations manquantes");
                return;
            }
            name.setText(user.getFirstname()+" veut "+job.getName());
        }
    }


}