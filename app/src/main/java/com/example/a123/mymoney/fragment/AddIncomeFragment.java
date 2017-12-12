package com.example.a123.mymoney.fragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.a123.mymoney.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KudzuLabyrinth on 2017/11/19.
 */

public class AddIncomeFragment extends Fragment {


    /**
     * 初始化類別選項物件
     */
    private TextView category, subCategory, date;

    private int mYear, mMonth, mDay;


    /**
     * 存儲類別的索引值
     */
    private static int categoryPosition = 0;


    /**
     * 存儲strings.xml的category_list物件
     */
    private String[] categoryResources;
    private String[] workIncomeResources;
    private String[] otherIncomeResources;



    /**
     * 將categoryResources轉成List儲存，方便使用者修改
     */
    private List<String> keyList;



    /**
     * value list存儲子類別物件
     */
    private List<List<String>> valueList;




    /**
     * 幫助設置子類別對話框要顯示的選項
     */
    private Map<String, List<String>> smallCategoryCreateHelper;




    /**
     * 提供給Activity呼叫
     */
    public AddIncomeFragment() {
    }




    /**
     * 將Fragment加入Activity前初始化內容防止程序崩潰
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        categoryResources = getResources().getStringArray(R.array.income_list);
        workIncomeResources = getResources().getStringArray(R.array.work_income);
        otherIncomeResources = getResources().getStringArray(R.array.other_income);


        /*
         * 將資源檔轉換成List方便增刪改查
         */
        keyList = new ArrayList<>(Arrays.asList(categoryResources));

        //子類別的List value

        List<String> workIncomeList = new ArrayList<>(Arrays.asList(workIncomeResources));
        List<String> otherIncomeList = new ArrayList<>(Arrays.asList(otherIncomeResources));

        // 將上列List資源存放在KeyList中

        valueList = new ArrayList<>();
        valueList.add(workIncomeList);
        valueList.add(otherIncomeList);


        smallCategoryCreateHelper = new HashMap<>();

        //  存放所有主類別和子類別資訊，把主類別設置為對應子類別value的key
        for (int i = 0; i < valueList.size(); i++) {
            smallCategoryCreateHelper.put(keyList.get(i), valueList.get(i));
        }
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_income_fragment, container, false);
        view.getRootView().setBackgroundColor(Color.rgb(230,230,204));
        category = view.findViewById(R.id.edtCategory);
        subCategory = view.findViewById(R.id.edtSubCategory);
        date = view.findViewById(R.id.txtDate);

        //初始化類別內容
        category.setTextColor(Color.BLACK);
        category.setTextSize(25.0f);
        category.setText(keyList.get(categoryPosition));

        //捕捉父類別索引決定子類別顯示內容
        subCategory.setTextColor(Color.BLUE);
        subCategory.setTextSize(25.0f);
        subCategory.setText(smallCategoryCreateHelper.get(keyList.get(categoryPosition)).get(0));

        date.setText(setDateFormat(mYear,mMonth,mDay));


        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogList = new AlertDialog.Builder(v.getContext());
                dialogList.setTitle(R.string.select_category);

                dialogList.setItems(categoryResources, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        category.setText(keyList.get(which));
                        categoryPosition = which;

                        subCategory.setText(smallCategoryCreateHelper.get(keyList.get(categoryPosition)).get(0));


                    }
                });
                dialogList.show();

            }
        });


        subCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogList = new AlertDialog.Builder(v.getContext());
                dialogList.setTitle(keyList.get(categoryPosition) + ":");

                //提供給setItems的array參數
                final String[] itemsArray = smallCategoryCreateHelper.get(keyList.get(categoryPosition)).toArray(new String[0]);
                dialogList.setItems(itemsArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        subCategory.setText(itemsArray[which]);
                    }
                });
                dialogList.show();
            }

        });


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new DatePickerDialog(getContext(),AlertDialog.THEME_DEVICE_DEFAULT_LIGHT ,new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String format = getString(R.string.set_date) + setDateFormat(year, month, day);
                        date.setText(format);
                    }
                }, mYear, mMonth, mDay).show();
            }
        });

        return view;
    }



    private String setDateFormat(int year, int month, int day) {
        return String.valueOf(year) + "/"
                + String.valueOf(month + 1) + "/"
                + String.valueOf(day);

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
