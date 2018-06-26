package com.example.testzx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @SuppressWarnings("deprecation")
    private void init() {
        LinearLayout layout = (LinearLayout) findViewById(R .id.root);
        final DrawView drawView = new DrawView(this);
        final String[] DataStr1 = new String[]{"245","110","236","125","80","120","36","45","223","10","136","110","136","145","50","245","110","236","125","80","120","36","45","223","10","136","110","136","145","50","245","110","236","125","80","120","36","45","223","10","136","110","136","145","50","245","110","236","125","80","120","36","45","223","10","136","110","136","145","50"};  //数据
        final String[] DataStr2 = new String[]{"300","136","145","123","50","136","36"};  //数据
        final String[] DataStr3 = new String[]{"400","236","50","110","232","136","232"};  //数据
        final String[] DataStr = new String[]{"50","20","223","160","110","36","145"};  //数据
        final String[] YLabel = new String[]{"50","100","150","200","250","300","350","400"};
        final String[][] XLabel = {new String[]{"1", "2", "3", "4", "5", "6", "7"}};
        drawView.setDate(YLabel, XLabel[0], new String[][]{DataStr1,DataStr,DataStr2,DataStr3});
//      view.setBackgroundDrawable(getResources().getDrawable(R.drawable.viewbackground));//给整个View加背景
        layout.addView(drawView);

        findViewById(R.id.bangeyue_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XLabel[0] = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
                drawView.setDate(YLabel, XLabel[0], new String[][]{DataStr1,DataStr,DataStr2,DataStr3});
            }
        });

        findViewById(R.id.qitian_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XLabel[0] = new String[]{"1","2","3","4","5","6","7"};
                drawView.setDate(YLabel, XLabel[0], new String[][]{DataStr1,DataStr,DataStr2,DataStr3});
            }
        });

        findViewById(R.id.yi_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setDate(YLabel, XLabel[0], new String[][]{DataStr1});
            }
        });

        findViewById(R.id.si_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawView.setDate(YLabel, XLabel[0], new String[][]{DataStr1,DataStr,DataStr2,DataStr3});
            }
        });
    }
}
