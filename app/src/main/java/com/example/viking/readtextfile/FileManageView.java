package com.example.viking.readtextfile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FileManageView extends AppCompatActivity {

    ListView listview;
    Button backBtn;
    String currentPath;
    List<String> contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_manage_view);
        listview=(ListView)findViewById(R.id.listView);
        backBtn=(Button)findViewById(R.id.backBtn);
        if (backBtn!=null){
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentPath=SDHelper.storagePath();
                    contents=SDHelper.getFileList(currentPath);
                    listview.setAdapter(new ArrayAdapter<String>(FileManageView.this,android.R.layout.simple_list_item_1,getData()));
                }
            });
        }
        currentPath=SDHelper.storagePath();
        contents=SDHelper.getFileList(currentPath);
        bindListViewData();
    }



    private void bindListViewData(){
        listview.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getData()));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String path=contents.get(i);
                if (SDHelper.isFileDirectory(path)){
                    currentPath=path;
                    contents=SDHelper.getFileList(currentPath);
                    listview.setAdapter(new ArrayAdapter<String>(FileManageView.this,android.R.layout.simple_list_item_1,getData()));
                }else {
                    sendResult(path);
                }
            }
        });
    }

    private List<String> getData(){
        List<String> datas=new ArrayList<String>();
        if (contents!=null){
            for (String path :contents){
                String[] parts=path.split("/");
                datas.add(parts[parts.length-1]);
            }
        }else
        {
            return null;
        }
        return datas;
    }


    private void sendResult(String path){
        Intent intent=new Intent();
        intent.setClass(FileManageView.this,MainActivity.class);
        intent.putExtra("uri",path);
        setResult(2,intent);
        finish();
    }

}
