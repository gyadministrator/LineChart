package com.android.custom.linechart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class MainActivity extends AppCompatActivity {
    public LineChartView lineChart;
    String[] date = {"0时", "6时", "12时", "18时", "24时"};//X轴的标注
    int[] score = {50, 42, 90, 33, 10};//图表的数据点
    int[] score1 = {60, 32, 70, 40, 15};//图表的数据点
    int[] score2 = {80, 100, 40, 60, 75};//图表的数据点
    public List<PointValue> mPointValues = new ArrayList<>();
    public List<PointValue> mPointValues1 = new ArrayList<>();
    public List<PointValue> mPointValues2 = new ArrayList<>();
    public List<AxisValue> mAxisXValues = new ArrayList<>();
    private int maxPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        maxPoint=Math.max(getMax(score), getMax(score1));
        getAxisXLabels();//获取x轴的标注
        getAxisPoints();//获取坐标点
        initLineChart();//初始化
    }

    private void initView() {
        lineChart = findViewById(R.id.lineChart);
    }

    /**
     * 设置X轴的显示
     */
    private void getAxisXLabels() {
        for (int i = 0; i < date.length; i++) {
            mAxisXValues.add(new AxisValue(i).setLabel(date[i]));
        }
    }

    private int getMax(int[] values) {
        int sum = values[0];//假设第一个元素是最大值
        //for循环遍历数组中元素，每次循环跟数组索引为0的元素比较大小
        for (int value : values) {
            if (sum < value) {//数组中的元素跟sum比较，比sum大就把它赋值给sum作为新的比较值
                sum = value;
            }
        }
        return sum;
    }

    /**
     * 图表的每个点的显示
     */
    private void getAxisPoints() {
        for (int i = 0; i < score.length; i++) {
            mPointValues.add(new PointValue(i, score[i]));
            mPointValues1.add(new PointValue(i, score1[i]));
            mPointValues2.add(new PointValue(i, score2[i]));
        }
    }

    private void initLineChart() {

        Line line = new Line(mPointValues).setColor(Color.parseColor("#FFCD41"));  //折线的颜色（橙色）
        List<Line> lines = new ArrayList<>();
        line.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line.setFilled(false);//是否填充曲线的面积
        line.setHasLabels(true);//曲线的数据坐标是否加上备注
        //line.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）

        Line line1 = new Line(mPointValues1).setColor(Color.parseColor("#FF0000"));  //折线的颜色（橙色）
        line1.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line1.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line1.setFilled(false);//是否填充曲线的面积
        line1.setHasLabels(true);//曲线的数据坐标是否加上备注
        //line1.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line1.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line1.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）

        Line line2 = new Line(mPointValues2).setColor(Color.parseColor("#FFCCCC"));  //折线的颜色（橙色）
        line2.setShape(ValueShape.CIRCLE);//折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
        line2.setCubic(false);//曲线是否平滑，即是曲线还是折线
        line2.setFilled(false);//是否填充曲线的面积
        line2.setHasLabels(true);//曲线的数据坐标是否加上备注
        //line2.setHasLabelsOnlyForSelected(true);//点击数据坐标提示数据（设置了这个line.setHasLabels(true);就无效）
        line2.setHasLines(true);//是否用线显示。如果为false 则没有曲线只有点显示
        line2.setHasPoints(true);//是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）

        lines.add(line);
        lines.add(line1);
        lines.add(line2);

        LineChartData data = new LineChartData();
        data.setLines(lines);
        //设置数据的初始值，即所有的数据从baseValue开始计算，默认值为0。
        data.setBaseValue(Float.NEGATIVE_INFINITY);

        //坐标轴
        Axis axisX = new Axis(); //X轴
        axisX.setHasTiltedLabels(false);  //X坐标轴字体是斜的显示还是直的，true是斜的显示
        axisX.setTextColor(Color.GRAY);  //设置字体颜色
        //axisX.setName("date");  //表格名称
        axisX.setTextSize(10);//设置字体大小
        axisX.setMaxLabelChars(8); //最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
        axisX.setValues(mAxisXValues);  //填充X轴的坐标名称
        data.setAxisXBottom(axisX); //x 轴在底部
        //data.setAxisXTop(axisX);  //x 轴在顶部
        //axisX.setHasLines(true); //x 轴分割线
        // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
        Axis axisY = new Axis();  //Y轴
        axisY.setName("");//y轴标注
        axisY.setTextSize(10);//设置字体大小
        data.setAxisYLeft(axisY);  //Y轴设置在左边
        //data.setAxisYRight(axisY);  //y轴设置在右边
        //设置行为属性，支持缩放、滑动以及平移
        lineChart.setInteractive(true);
        lineChart.setZoomType(ZoomType.HORIZONTAL);
        lineChart.setMaxZoom((float) 1);//最大方法比例
        lineChart.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        lineChart.setLineChartData(data);
        lineChart.setVisibility(View.VISIBLE);
        /**注：下面的7，10只是代表一个数字去类比而已
         * 当时是为了解决X轴固定数据个数。见（http://forum.xda-developers.com/tools/programming/library-hellocharts-charting-library-t2904456/page2）;
         */
        Viewport v = new Viewport(lineChart.getMaximumViewport());
        //Y轴最大值为 加上20、防止显示不全
        v.top = maxPoint + 20;
        //最小值 Y轴最低点就为0
        v.bottom = 0;//最小值
        //设置最大化的viewport （chartdata）后再调用
        //这2个属性的设置一定要在lineChart.setMaximumViewport(v);这个方法之后,
        // 不然显示的坐标数据是不能左右滑动查看更多数据的
        lineChart.setMaximumViewport(v);
        //左边起始位置 轴
        v.left = 0;
        //如果数据点超过20，显示20个、否则，显示数据本身大小{自己根据需求设置}
        if (date.length > 20) {
            // Y轴显示多少数据
            v.right = 20;
        } else {
            v.right = date.length;
        }
        lineChart.setCurrentViewport(v);
    }
}
