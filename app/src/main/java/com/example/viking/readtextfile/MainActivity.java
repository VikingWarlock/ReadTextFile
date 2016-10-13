package com.example.viking.readtextfile;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView status;
    Button chooseBtn;
    TextView content;
    private final int REQUESTCODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindElement();
    }

    private void bindElement(){
        status=(TextView)findViewById(R.id.statusLabel);
        chooseBtn=(Button)findViewById(R.id.chooseBtn);
        content=(TextView)findViewById(R.id.contentView);
        if (chooseBtn!=null){
            chooseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    chooseFile();
                }
            });
        }
    }

    private void chooseFile(){
        //        Intent intent=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //从SD卡获取图片比较简单,获取其他类型的文件,感觉需要手动写界面了
        //借鉴之前项目中的SDCardHelper
        //配合着ListView做一个选择文件的界面
        //考试估计会固定路径
        Intent intent=new Intent(this,FileManageView.class);
        startActivityForResult(intent, REQUESTCODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2) {
            if (requestCode == REQUESTCODE) {
                String path=data.getStringExtra("uri");
                String contentText=TextReader.ReadTextForPath(path);
                content.setText(contentText);
            }
        }
    }

}
