package com.example.nested;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.nested.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding mBinding;
    private ArrayList<EmpAttend> mEmpAttend = new ArrayList<>();
    private MainAdapter mAdapter;
    private int totalDate;
    private int totalEmp;
    int originY = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        totalEmp = 20;
        for(int idx = 0 ; idx < totalEmp ; idx ++){

            mEmpAttend.add(new EmpAttend("09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00"
                    , "09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00"
                    , "09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00"
                    , "09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00"
                    , "09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00", "09:00\n18:00"
                    , "09:00\n18:00", "09:00\n18:00"));
        }

        mAdapter = new MainAdapter(mEmpAttend);
        mBinding.attendList.setAdapter(mAdapter);

        totalDate = 31;

        LinearLayout.LayoutParams paramDate = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramDate.width = (int)dpToPx(this,(float)70);
        paramDate.height = (int)dpToPx(this,(float)40);
        paramDate.weight = 1;
        for(int idx = 1 ; idx <= totalDate ; idx ++){
            TextView tvHeaderDate = new TextView(this);
            String headerText= (idx) + "일";
            tvHeaderDate.setText(headerText);
            tvHeaderDate.setTextColor(Color.BLACK);
            tvHeaderDate.setTypeface(null, Typeface.BOLD);
            tvHeaderDate.setGravity(Gravity.CENTER);
            tvHeaderDate.setLayoutParams(paramDate);
            mBinding.headerLayout.addView(tvHeaderDate);

        }

        LinearLayout.LayoutParams paramEmp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        paramEmp.width = (int)dpToPx(this,(float)90);
        paramEmp.height = (int)dpToPx(this,(float)70);
        paramEmp.weight = 1;
        for(int idx = 1 ; idx <= totalEmp ; idx ++){
            TextView tvEmpNm = new TextView(this);
            String headerText=  "사원명" + (idx);
            tvEmpNm.setText(headerText);
            tvEmpNm.setTextColor(Color.BLACK);
            tvEmpNm.setTypeface(null, Typeface.BOLD);
            tvEmpNm.setGravity(Gravity.CENTER);
            tvEmpNm.setLayoutParams(paramEmp);
            mBinding.empNmLayout.addView(tvEmpNm);

        }
        onRecyclerviewScrollListener();
        onScrollViewScrollListener();

        mBinding.svEmpNm.setOnTouchListener( new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void onRecyclerviewScrollListener() {
        mBinding.attendList.clearOnScrollListeners();
        RecyclerView.OnScrollListener onScrollListener = new RecyclerViewScrollListener() {
            @Override
            public void onScrolled(int dy) {
//                Log.e(MainActivity.class.getSimpleName(), "y = " + dy);
                mBinding.svEmpNm.scrollTo(0, dy);
            }
        };
        mBinding.attendList.addOnScrollListener(onScrollListener);
    }

    private void onScrollViewScrollListener() {
        mBinding.svEmpNm.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                int sY = mBinding.svEmpNm.getScrollY();
                Log.e(MainActivity.class.getSimpleName(), "sY = " + sY + " / originY = " + originY);
                originY = sY - originY;
                Log.e(MainActivity.class.getSimpleName(), "sY - originY = " + originY);

//                mBinding.attendList.scrollTo(0, sY);
                mBinding.attendList.scrollBy(0, 0);
            }
        });
    }



    //dp를 px로 변환 (dp를 입력받아 px을 리턴)
    public float dpToPx(Context context, float dp) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, dm);
    }
}
