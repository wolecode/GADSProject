package com.example.practiceproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.practiceproject.fragmentAdapter.FragmentAdapter;
import com.example.practiceproject.learners_iq.IQLeaders;
import com.example.practiceproject.learners.LearningLeaders;
import com.example.practiceproject.retrofitservice.ApiService;
import com.example.practiceproject.retrofitservice.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderBoard extends AppCompatActivity {

     public ViewPager2 viewPager;
     public TabLayout tabLayout;
     public TabLayout.Tab learningLeadersTab;
     public TabLayout.Tab IQLeadersTab;
     public List<LearningLeaders> mLearningLeaders;
     public List<IQLeaders> mIqLeaders;
     public MaterialButton submit;
     FragmentAdapter fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LeaderBoard.this, Submit.class);
                startActivityForResult(intent,0);
            }
        });
        loadLearningAPI();
    }


    public void loadLearningAPI() {
        ApiService service= RetrofitService.getApiService(ApiService.class);

        /**
          Getting list of Learning Leaders
        * */
        Call<List<LearningLeaders>> call=service.getLearningLeaders();
        call.enqueue(new Callback<List<LearningLeaders>>() {
            @Override
            public void onResponse(Call<List<LearningLeaders>> call, Response<List<LearningLeaders>> response) {
                mLearningLeaders= response.body();
                loadIQAPI();

            }

            @Override
            public void onFailure(Call<List<LearningLeaders>> call, Throwable t) {

            }
        });

    }

    private void loadIQAPI() {
        ApiService service= RetrofitService.getApiService(ApiService.class);

        /**
         Getting list of IQ Leaders
         */
        Call<List<IQLeaders>> call1=service.getIQLeaders();
        call1.enqueue(new Callback<List<IQLeaders>>() {
            @Override
            public void onResponse(Call<List<IQLeaders>> call, Response<List<IQLeaders>> response) {
                mIqLeaders=response.body();
                createViewPagerAndFragment();

            }

            @Override
            public void onFailure(Call<List<IQLeaders>> call, Throwable t) {

            }
        });
    }

    private void createViewPagerAndFragment() {
        fragment =new FragmentAdapter(LeaderBoard.this);
        viewPager=findViewById(R.id.viewPager);
        viewPager.setAdapter(fragment);

        tabLayout=findViewById(R.id.tabLayout);
        learningLeadersTab=tabLayout.newTab();
        IQLeadersTab=tabLayout.newTab();


        tabLayout.addTab(learningLeadersTab);
        tabLayout.addTab(IQLeadersTab);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position==0){
                    tab.setText("Learning Leaders");
                }
                else{
                    tab.setText("Skill IQ Leaders");
                }
            }
        }).attach();
    }

}