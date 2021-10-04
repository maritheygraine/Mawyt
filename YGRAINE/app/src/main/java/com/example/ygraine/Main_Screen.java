package com.example.ygraine;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main_Screen extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    DbHelper dbHelper;
    ArrayAdapter<String> mAdapter;
    ListView lstTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__screen);

        dbHelper = new DbHelper(this);
        lstTask = (ListView) findViewById(R.id.lstTask);


        loadTaskList();
    }

    private void loadTaskList() {
        ArrayList<String> taskList = dbHelper.getTaskList();
        if(mAdapter == null){
            mAdapter = new ArrayAdapter<String>(this, R.layout.row, R.id.task_title, taskList);
            lstTask.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }

    public void deleteTask(View view){
        View parent = (View)view.getParent();
        TextView taskTextView = (TextView)parent.findViewById(R.id.task_title);
        Log.e("String", (String) taskTextView.getText());
        String task = String.valueOf(taskTextView.getText());
        dbHelper.deleteTask(task);
        loadTaskList();
    }

    public void showPopupMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    public void showPopupOptionsMenu(View v) {
        PopupMenu popup2 = new PopupMenu(this, v);
        popup2.setOnMenuItemClickListener(this);
        popup2.inflate(R.menu.options_menu);
        popup2.show();
    }

    public void showAddMenu(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                //---DONE
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(getApplicationContext(), profile.class);
                startActivity(intent1);
                return true;
            case R.id.item2:
                //---DONE
                Toast.makeText(this, "Add New Account", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getApplicationContext(), Register.class);
                startActivity(intent2);
                return true;
            case R.id.item3:
                //---DONE
                Toast.makeText(this, "Logout Successful", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(getApplicationContext(), Login.class);
                startActivity(intent3);
                return true;
            case R.id.item4:
                //---DONE
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                Intent SETTINGS = new Intent(getApplicationContext(), Settings.class);
                startActivity(SETTINGS);
                return true;
            case R.id.item5:
                //---DONE
                Toast.makeText(this, "Help", Toast.LENGTH_SHORT).show();
                Intent intent5 = new Intent(getApplicationContext(), Help.class);
                startActivity(intent5);
                return true;
            case R.id.item6:
                //---DONE
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                Intent intent6 = new Intent(getApplicationContext(), about.class);
                startActivity(intent6);
                return true;
            case R.id.action_add_task:
                //---DONE
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add New Task")
                        .setMessage("What to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                dbHelper.insertNewTask(task);
                                loadTaskList();
                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                dialog.show();
            default:
                return false;
        }
    }


}
