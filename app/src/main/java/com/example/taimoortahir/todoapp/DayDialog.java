package com.example.taimoortahir.todoapp;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by TaimoorTahir on 23/09/2017.
 */

public class DayDialog extends Dialog implements DayAdapter.OnBack {

    RecyclerView dayRecycler;
    DayAdapter dAdapter;
    private OnBack ob;
    String MyPreferences = "MyPrefs";
    SharedPreferences sharedpreferences;
    DayModel model = new DayModel();
    TextView titleTextView;
    TextView descriptionText;

    public DayDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.activity_day_dialog);
        titleTextView = (TextView) findViewById(R.id.title_textview);
        descriptionText = (TextView) findViewById(R.id.descrip_textview);
    }

    public DayDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    protected DayDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setDayList(ArrayList<DayModel> list){
        setAdapter(list);
    }

    public void setDayList(ArrayList<DayModel> list, OnBack ob){

        onOptionSelected(ob);
        setAdapter(list);
    }

    public void onOptionSelected(OnBack ob){
        this.ob = ob;
    }


    public void setDefaultDay(String s){
        model.setweekDay(s);
    }

    public void setTitle(String title){
        titleTextView.setText(title);
    }

    public void setDescription(String desc){
        descriptionText.setText(desc);
    }

    public void setBackgroundcolor(int color){
        titleTextView.setBackgroundColor(color);
    }

    private void setAdapter(ArrayList<DayModel> list){
        dayRecycler = (RecyclerView) findViewById(R.id.recycler_day);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        dayRecycler.setLayoutManager(mLayoutManager);
        dAdapter = new DayAdapter(getContext(), list, this, model.getweekDay());
        dayRecycler.setAdapter(dAdapter);
        dAdapter.notifyDataSetChanged();
    }

    @Override
    public void DayClickListener(String s){
        ob.DayItemClickListener(s, this);
//        sharedpreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
//        String temp = sharedpreferences.getString("Day", null);
//        Toast.makeText(getContext(), "saved value is" + temp + " ", Toast.LENGTH_SHORT).show();
    }

    public interface OnBack{
        public void DayItemClickListener(String s, DayDialog ref);
    }

}
