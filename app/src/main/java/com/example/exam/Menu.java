package com.example.exam;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by 魏于翔 on 2017/2/3.
 */
public class Menu extends AppCompatActivity{
    private DrawerLayout mDrawerLayout;
    private ImageButton mid_exam;
    private ImageButton usl_exam;
    private ImageButton rank;
    private ImageButton game;
    private TextView user_name;
    private TextView user_id;
    View headerView;
    public String name;
    public String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        id = intent.getStringExtra("id");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
        initCr();

    }

    //按下返回键后，直接返回手机的主屏幕，而不退出应用
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void initView() {


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.inflateHeaderView(R.layout.nav_header);
        headerView = navView.getHeaderView(0);
        user_name = (TextView) headerView.findViewById(R.id.user_name);
        user_id = (TextView) headerView.findViewById(R.id.user_id);
        user_name.setText(name);
        user_id.setText(id);
        mid_exam = (ImageButton) findViewById(R.id.mid);
        usl_exam = (ImageButton) findViewById(R.id.usual);
        rank = (ImageButton) findViewById(R.id.rank);
        game = (ImageButton) findViewById(R.id.game);
        ActionBar actionBar = getSupportActionBar();
        setDrawerLeftEdgeSize(Menu.this,mDrawerLayout,1);
        if (actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
        navView.setCheckedItem(R.id.nav_history);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.nav_history:
                        Intent intent = new Intent(Menu.this, com.example.exam.HistoryView.class);
                        intent.putExtra("id",id);
                        startActivityForResult(intent, 123);
                        break;

                    case R.id.nav_reDate:
                        Intent intent1 = new Intent(Menu.this,ReDate.class);
                        intent1.putExtra("id",id);
                        startActivity(intent1);
                        break;

                    case R.id.nav_rePassword:
                        Intent intent2 = new Intent(Menu.this,RePassword.class);
                        intent2.putExtra("id",id);
                        startActivity(intent2);
                        break;
                    case R.id.nav_exit:
                        finish();
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    private void initCr() {
        mid_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Menu.this, com.example.exam.Mid_exam.class);
                intent1.putExtra("id",id);
                startActivity(intent1);
            }
        });

        usl_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(Menu.this, com.example.exam.Usl_exam.class);
                intent3.putExtra("id",id);
                startActivity(intent3);
            }
        });

        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(Menu.this, com.example.exam.Rank.class);
                intent4.putExtra("id",id);
                startActivity(intent4);
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Menu.this, com.example.exam.MainActivity.class);
                intent2.putExtra("id",id);
                startActivity(intent2);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }

        return true;
    }


     //设置全屏侧滑效果
    private void setDrawerLeftEdgeSize (Activity activity, DrawerLayout drawerLayout, float displayWidthPercentage) {
        if (activity == null || drawerLayout == null) return;
        try {
            // 找到 ViewDragHelper 并设置 Accessible 为true
            Field leftDraggerField =
                    drawerLayout.getClass().getDeclaredField("mLeftDragger");//Right
            leftDraggerField.setAccessible(true);
            ViewDragHelper leftDragger = (ViewDragHelper) leftDraggerField.get(drawerLayout);

            // 找到 edgeSizeField 并设置 Accessible 为true
            Field edgeSizeField = leftDragger.getClass().getDeclaredField("mEdgeSize");
            edgeSizeField.setAccessible(true);
            int edgeSize = edgeSizeField.getInt(leftDragger);

            // 设置新的边缘大小
            Point displaySize = new Point();
            activity.getWindowManager().getDefaultDisplay().getSize(displaySize);
            edgeSizeField.setInt(leftDragger, Math.max(edgeSize, (int) (displaySize.x *
                    displayWidthPercentage)));
        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }
    }

}
