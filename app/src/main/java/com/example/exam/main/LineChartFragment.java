package com.example.exam.main;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exam.R;
import com.example.exam.db.History;
import com.idtk.smallchart.chart.LineChart;
import com.idtk.smallchart.data.LineData;
import com.idtk.smallchart.interfaces.iData.ILineData;

import java.util.ArrayList;
import java.util.List;


import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by wyxiang on 2017/2/25.
 */

public class LineChartFragment extends BaseFragment {

    private LineData mLineData = new LineData();
    private ArrayList<ILineData> mDataList = new ArrayList<>();
    private ArrayList<PointF> mPointArrayList = new ArrayList<>();
    private String id;
    private float[][] date = new float[100][100];
    private int x;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bmob.initialize(this.getContext(),"1eecd58732bf214e4b72f39a9ec07541");

        id = getFragmentManager().findFragmentByTag("Tag2").getArguments().getString("id");

        BmobQuery<History> query = new BmobQuery<History>();

        query.addWhereEqualTo("user_id",id);

        query.findObjects(new FindListener<History>() {

            @Override
            public void done(List<History> list, BmobException e) {

                    if (list.size()==0)
                    {
                        date[0][0] = 0;
                        date[0][1] = 1;
                        date[1][0] = 1;
                        date[1][1] = 1;
                        x = 2;
                    }
                    else
                    {
                        date[0][0] = 0;
                        date[0][1] = 1;
                        for (int i = 1;i<list.size()+1;i++)
                        {
                            date[i][0] = i;
                            date[i][1] = list.get(i-1).getScore();
                        }
                        x = list.size()+1;
                    }

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_linechart,container,false);


        final LineChart lineChart = (LineChart) view.findViewById(R.id.lineChart);

        for (int i = 0; i < x; i++) {
            mPointArrayList.add(new PointF((float) date[i][0], (float) date[i][1]));
        }
        mLineData.setValue(mPointArrayList);
        mLineData.setColor(Color.MAGENTA);
        mLineData.setPaintWidth(pxTodp(3));
        mLineData.setTextSize(pxTodp(10));
        mDataList.add(mLineData);
        lineChart.setDataList(mDataList);

        return view;
    }

}
