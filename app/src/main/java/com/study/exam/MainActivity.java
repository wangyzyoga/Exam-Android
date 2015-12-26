package com.study.exam;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String DB_PATH = "data/data/com.study.exam/databases/";
        String DB_NAME = "question.db";

        if ((new File(DB_PATH + DB_NAME).exists()) == false) {
            File dir = new File(DB_PATH);
            if (!dir.exists()) {
                dir.mkdir();
            }
        }

        try {
            InputStream is = getBaseContext().getAssets().open(DB_NAME);
            OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomDialog.Builder builder = new CustomDialog.Builder(MainActivity.this);
                builder.setTitle("温馨提示");
                builder.setMessage("*  共有5道考题，满分10分\n" + "*  答题时间为10分钟\n" + "是否确定开始？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        Intent intent = new Intent(MainActivity.this, ExamActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("取消",
                        new android.content.DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.create().show();
            }
        });
    }
}
