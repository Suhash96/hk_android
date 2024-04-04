package com.example.figma;

// JobsPagerAdapter.java
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class JobsPagerAdapter extends FragmentPagerAdapter {
    private String supervisorId;

    public JobsPagerAdapter(FragmentManager fm, String supervisorId) {
        super(fm);
        this.supervisorId = supervisorId;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new UpcomingJobsFragment();
            case 1:
                return new CompletedJobsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2; // Total number of tabs
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Upcoming"; // Title for the first tab
            case 1:
                return "Completed"; // Title for the second tab
            default:
                return null;
        }
    }
}
