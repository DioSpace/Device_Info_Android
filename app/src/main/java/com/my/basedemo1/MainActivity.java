package com.my.basedemo1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.my.basedemo1.model.JsonRootBean;
import com.my.basedemo1.model.List_info;
import com.my.basedemo1.util.GetJsonDataUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView show_board;
    JsonRootBean jsonRootBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show_board = findViewById(R.id.show_board_id);

        String json_str = GetJsonDataUtil.getJson(this, "list.json");
        Gson gson = new Gson();
        jsonRootBean = gson.fromJson(json_str, JsonRootBean.class);

        //1.获取ListView对象
        ListView listView = findViewById(R.id.lv_main);
        //2.准备数据
        String[] func_name = new String[jsonRootBean.getList_info().size()];
        for (int i = 0; i < jsonRootBean.getList_info().size(); i++) {
            func_name[i] = jsonRootBean.getList_info().get(i).getButton_name();
        }
        //3.准备适配器Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, //context 上下文
                android.R.layout.simple_list_item_1, //行布局:系统自带的布局
                func_name//数据源
        ) {
            @Override
            public boolean isEnabled(int position) {
                return true;//允许点击
            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                //重载该方法，在这个方法中，将每个Item的Gravity设置为CENTER
                TextView textView = (TextView) super.getView(position, convertView, parent);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        };
        //4.将适配器关联到ListView
        listView.setAdapter(adapter);
        /*
         * 顶部和底部添加分割线,美观
         * */
        listView.addHeaderView(new ViewStub(this));//顶部分割线
        listView.addFooterView(new ViewStub(this));//底部分割线


        //设置listview点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("position : " + position + " id : " + id);
                List<List_info> list_info = jsonRootBean.getList_info();
                List_info info = list_info.get(position - 1);
                show_board.setText(info.getFunc_name());
                //获取Class对象
                try {
                    Class c = Class.forName("com.my.basedemo1.Handle");
                    Object obj = c.newInstance();
                    Method method = c.getMethod(info.getFunc_name(), null);
                    method.invoke(obj);
                } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
