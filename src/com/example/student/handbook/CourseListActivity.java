package com.example.student.handbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class CourseListActivity extends FragmentActivity
        implements CourseListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);

        if (findViewById(R.id.course_detail_container) != null) {
            mTwoPane = true;
            ((CourseListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.course_list))
                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(CourseDetailFragment.ARG_ITEM_ID, id);
            CourseDetailFragment fragment = new CourseDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.course_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, CourseDetailActivity.class);
            detailIntent.putExtra(CourseDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
