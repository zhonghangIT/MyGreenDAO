package com.education.mygreendao;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.dao.internal.DaoConfig;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.button_insert)
    Button buttonInsert;
    @InjectView(R.id.button_delete)
    Button buttonDelete;
    @InjectView(R.id.button_update)
    Button buttonUpdate;
    @InjectView(R.id.button_select)
    Button buttonSelect;

    private StudentDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "TEST.DB", null);
//        MySqliteHelper helper = new MySqliteHelper(this, "TEST.DB", null, 1);
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(database);
        DaoSession session = master.newSession();
        dao = session.getStudentDao();
        ButterKnife.inject(this);
    }

    @OnClick({R.id.button_insert, R.id.button_delete, R.id.button_update, R.id.button_select})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_insert:
                Student student = new Student();
                student.setName("zhangsan");
                student.setAge("20");
                student.setSex("男");

                dao.insert(student);
                break;
            case R.id.button_delete:

                break;
            case R.id.button_update:
                break;
            case R.id.button_select:
                List<Student> studentList = dao.loadAll();
                for (Student student1 : studentList) {
                    Log.d("-----", "-----" + student1.getName() + student1.getId());
                }
                break;
        }
    }
}
