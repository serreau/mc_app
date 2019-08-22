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
            b.putString("name", jobviewholder.getName());
            Navigation.findNavController(view).navigate(R.id.action_kanbanViewPager_to_detailsFragment, b);
        });

        return jobviewholder;
    }

    @Override
    public void onBindViewHolder(JobViewHolder holder, int position) {
        Job job = jobs.get(position);
        String jobname = job.getName();
        String userfirstname = getUserFirstname(job);


        holder.setName(userfirstname+" veut "+jobname);
    }

    private String getUserFirstname(Job job) {
        ListIterator<User> it = users.listIterator();
        while (it.hasNext()){
            User user = it.next();
            if(job.getOwner().equals(user.getPhone()))
                return user.getFirstname();
        }
        return "";
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

        public JobViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name_card);
        }

        public void setName(String text) {
            this.name.setText(text);
        }
        public String getName() {
            return this.name.getText().toString();
        }
    }


}