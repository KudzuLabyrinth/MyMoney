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

import java.util.Arrays;
import java.util.List;

/**
 * Created by KudzuLabyrinth on 2017/11/19.
 */

public class AddIncomeFragment extends Fragment {


    /**
     * 初始化類別選項物件
     */
    private TextView category, smallCategory;


    /**
     * 存儲strings.xml的category_list物件
     */
    private String[] categoryResources;


    /**
     * 將categoryResources轉成List儲存，方便使用者修改
     */
    private List<String> categoryList;


    /**
     * 存儲類別的索引值
     */
    private static int position = 0;


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
        categoryList = Arrays.asList(categoryResources);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_income_fragment, container, false);
        category = view.findViewById(R.id.category);
        smallCategory = view.findViewById(R.id.spn_small_category);

        //初始化類別內容
        category.setTextColor(Color.BLACK);
        category.setTextSize(25.0f);
        category.setText(categoryList.get(position));

        //捕捉父類別索引決定子類別顯示內容
        smallCategory.setTextColor(Color.BLUE);
        smallCategory.setTextSize(25.0f);
        smallCategory.setText(String.valueOf(position));


        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogList = new AlertDialog.Builder(v.getContext());
                dialogList.setTitle(R.string.select_category);

                dialogList.setItems(categoryResources, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        category.setText(categoryList.get(which));
                        position = which;

                        smallCategory.setText(String.valueOf(position));


                    }
                });
                dialogList.show();

            }
        });


        smallCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogList = new AlertDialog.Builder(v.getContext());
                dialogList.setTitle(R.string.select_small_category);
                dialogList.setItems(null, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

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
