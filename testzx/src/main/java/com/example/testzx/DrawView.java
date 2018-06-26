package com.example.testzx;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2018/4/26.
 */

public class DrawView extends View {
    public int XPoint = 60; // 原点的X坐标
    public int YPoint ;// 原点的Y坐标260
    public int XScale ; // X的刻度长度55
    public int YScale ; // Y的刻度长度40
    public int XLength ; // X轴的长度380
    public int YLength ; // Y轴的长度240
    //  private int scaleLength = 10;//刻度线的长度 TTTTTT
    private int top = 10;//上边缘距离
    private int left = 40;//左边缘距离
    private int right = 10;//右边缘距离
    private int bottom = 40;//下边缘距离
    private String[] YLabel;//y轴的刻度值
    private String[] XLabel;//X轴的刻度值
    public String[][] DataStr; // 数据

    //  private Bitmap mBackGround;
    public DrawView(Context context) {
        super(context);
//      mBackGround  = ((BitmapDrawable) this.getResources().getDrawable(R.drawable.viewbackground)).getBitmap(); //获取背景图片
    }
    public void setDate(String[] YLabel,String[] XLabel,String[][] DataStr) {//如果只需要一条折线，最后这个参数给null就行了
        this.YLabel = YLabel;
        this.XLabel = XLabel;
        this.DataStr = DataStr;
        invalidate();
    }
    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i("Main", "Width = " + getWidth());//1280
        Log.i("Main", "Height = " + getHeight());//670  测量整个view的高度不包括状态栏

        Log.i("Main", "Width = " + getMeasuredWidth());//一个是测量整个view的高度  一个是测量view里内容的高度
        Log.i("Main", "Height = " + getMeasuredHeight());

        YLength = getHeight()-bottom-top;//整个Y轴的长度
        XLength = getWidth()-right-left;
        YPoint = getHeight()-bottom;
        XScale = (XLength/XLabel.length);//--x轴的刻度平均长度
        YScale = (YLength/YLabel.length);//Y--Y轴的刻度平均长度

        Paint paint = new Paint();
        paint.setStrokeWidth(2.5f);
       // paint.setColor(getResources().getColor(R.color.FFEFEFEF));
        paint.setTextSize(20);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);

//      canvas.drawBitmap(mBackGround, 0, 0, paint); //画背景图片
        //画竖线
        canvas.drawLine(XPoint,YPoint, XPoint,top, paint);
        canvas.drawLine(XPoint-5,top+10, XPoint,top,paint);
        canvas.drawLine(XPoint+5,top+10, XPoint,top,paint);
        //canvas.drawLine(XPoint,YPoint, XPoint,top, paint);
        for (int i = 1; i * YScale <= YLength; i++) {//画横刻度
            //canvas.drawLine(XPoint, YPoint - i * YScale, XLength, YPoint - i* YScale, paint);   格子线
            canvas.drawLine(XPoint, YPoint - i * YScale, XPoint+5, YPoint - i* YScale, paint);
            paint.setColor(Color.BLACK);
            canvas.drawText(YLabel[i-1], XPoint - 30,YPoint - i * YScale +7, paint);
           // paint.setColor(getResources().getColor(R.color.FFEFEFEF));
        }
        //画横线
        canvas.drawLine(XPoint, YPoint, XLength, YPoint, paint);
        canvas.drawLine(XLength-10, YPoint+5, XLength, YPoint,paint);
        canvas.drawLine(XLength-10, YPoint-5, XLength, YPoint,paint);
        //canvas.drawLine(XPoint, YPoint, XLength, YPoint, paint);
        for (int i = 1; i * XScale <= XLength; i++) {//画竖刻度
            //canvas.drawLine(XPoint + (i-1) * XScale, YPoint, XPoint + (i-1) * XScale,top, paint);  格子线
            canvas.drawLine(XPoint, YPoint - i * YScale, XPoint+5, YPoint - i* YScale, paint);
            paint.setColor(Color.BLACK);
            if(XLabel.length==7){
                canvas.drawText(XLabel[i-1], XPoint + (i-1) * XScale - 3,YPoint+30, paint);
                paint.setColor(getResources().getColor(R.color.FFEFEFEF));
            }
        }
        /*//画数据图
        paint.setColor(getResources().getColor(R.color.FFFF585C));
        paint.setStrokeWidth(3.5f);
        for (int i = 0; i<DataStr.length; i++) {
            canvas.drawCircle((float)XPoint+(i)*XScale,(float)calcuLations(DataStr[i]),8, paint);
            if (i+1<DataStr.length){
                canvas.drawLine((float)XPoint+(i)*XScale,(float)calcuLations(DataStr[i])
                        ,(float)XPoint+(i+1)*XScale,(float)calcuLations(DataStr[i+1]), paint);
            }
        }
        if (null!=DataStr1) {
            //画数据图第二条线(待封装扩展)
            paint.setColor(getResources().getColor(R.color.FF2098EE));
            for (int i = 0; i<DataStr1.length; i++) {
                canvas.drawCircle((float)XPoint+(i)*XScale,(float)calcuLations(DataStr1[i]),8, paint);
                if (i+1<DataStr1.length){
                    canvas.drawLine((float)XPoint+(i)*XScale,(float)calcuLations(DataStr1[i])
                            ,(float)XPoint+(i+1)*XScale,(float)calcuLations(DataStr1[i+1]), paint);
                }
            }
        }*/
        //画数据图
        drawData(paint,canvas,DataStr,new int[]{Color.RED,Color.BLUE,Color.YELLOW,Color.LTGRAY} );
    }

    private void drawData(Paint paint,Canvas canvas,String[][] dates,int[] color) {

        for (int i = 0; i < dates.length; i++) {
            paint.setColor(color[i]);
            for (int j = 0; j < dates[i].length; j++) {
                //canvas.drawCircle((float) XPoint + (j ) * XScale, (float) calcuLations(dates[i][j]), 8, paint);  折线原圈
                //paint.setColor(Color.BLACK);        折线点上的值
                //canvas.drawText("(" + dates[i][j] + ")", (float) XPoint + (j + 1) * XScale - 5, (float) calcuLations(dates[i][j]) - 20, paint);  折线点上的值
                paint.setColor(color[i]);
                if (j + 1 < dates[i].length) {
                    canvas.drawLine((float) XPoint + (j ) * XScale, (float) calcuLations(dates[i][j])
                            , (float) XPoint + (j + 1) * XScale, (float) calcuLations(dates[i][j + 1]), paint);
                }
            }
        }
    }


    private int calcuLations(String y0) //计算y轴坐标
    {
        double y;
        try {
            y = Double.parseDouble(y0);
        } catch (Exception e) {
            return -100;
        }
        return (int)(YPoint-YPoint*(y/Double.parseDouble(YLabel[YLabel.length-1])));
    }
}