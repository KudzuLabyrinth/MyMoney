package com.example.a123.mymoney.fragment;

import android.app.AlertDialog;
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
import android.widget.TextView;

import com.example.a123.mymoney.R;

import java.util.ArrayList;
import java.util.Arrays;
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
    private TextView category, subCategory;


    /**
     * 存儲類別的索引值
     */
    private static int categoryPosition = 0;


    /**
     * 存儲strings.xml的category_list物件
     */
    private String[] categoryResources;
    private String[] dietingResources;
    private String[] clothingResources;
    private String[] housingResources;
    private String[] transportationResources;
    private String[] educationResources;
    private String[] entertainmentResources;


    /**
     * 將categoryResources轉成List儲存，方便使用者修改
     */
    private List<String> keyList;


    List<List<String>> valueList;
    /**
     * 橫向的value list物件
     */


    /**
     * 幫助設置子類別對話框要顯示的選項
     */
    private Map<String, List<String>> smallCategoryCreateHelper;


    /**
     * 提供給Acticvity呼叫
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


        categoryResources = getResources().getStringArray(R.array.category_list);
        dietingResources = getResources().getStringArray(R.array.dieting_list);
        clothingResources = getResources().getStringArray(R.array.clothing_list);
        housingResources = getResources().getStringArray(R.array.housing_list);
        transportationResources = getResources().getStringArray(R.array.transportation_list);
        educationResources = getResources().getStringArray(R.array.education_list);
        entertainmentResources = getResources().getStringArray(R.array.entertainment_list);

        /*
         * 將資源檔轉換成List方便增刪改查
         */
        keyList = new ArrayList<>(Arrays.asList(categoryResources));

        //子類別的List value

        List<String> dietingList = new ArrayList<>(Arrays.asList(dietingResources));
        List<String> clothingList = new ArrayList<>(Arrays.asList(clothingResources));
        List<String> housingList = new ArrayList<>(Arrays.asList(housingResources));
        List<String> transportationList = new ArrayList<>(Arrays.asList(transportationResources));
        List<String> educationList = new ArrayList<>(Arrays.asList(educationResources));
        List<String> entertainmentList = new ArrayList<>(Arrays.asList(entertainmentResources));


        // 將上列List資源存放在KeyList中

        valueList = new ArrayList<>();
        valueList.add(dietingList);
        valueList.add(clothingList);
        valueList.add(housingList);
        valueList.add(transportationList);
        valueList.add(educationList);
        valueList.add(entertainmentList);

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
        category = view.findViewById(R.id.category);
        subCategory = view.findViewById(R.id.spn_small_category);

        //初始化類別內容
        category.setTextColor(Color.BLACK);
        category.setTextSize(25.0f);
        category.setText(keyList.get(categoryPosition));

        //捕捉父類別索引決定子類別顯示內容
        subCategory.setTextColor(Color.BLUE);
        subCategory.setTextSize(25.0f);
        subCategory.setText(smallCategoryCreateHelper.get(keyList.get(categoryPosition)).get(0));


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

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
