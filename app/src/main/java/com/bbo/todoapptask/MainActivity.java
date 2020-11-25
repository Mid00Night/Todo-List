package com.bbo.todoapptask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView todoList;
    TodoAdapter adapter;
    ArrayList<Todo> todos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoList = findViewById(R.id.todo_List);
        todos = new ArrayList<>();
        todos.add(new Todo("waking up at 8", "task 1", false));
        todos.add(new Todo("Eating breakfast", "task 2", false));
        todos.add(new Todo("going to university", "task 3", false));
        todos.add(new Todo("attending my classes", "task 4", true));
        todos.add(new Todo("eating launch", "task 5", false));
        todos.add(new Todo("back from university", "task 6", false));
        todos.add(new Todo("playing on PC", "task 7", true));
        todos.add(new Todo("doing my tasks", "task 8", false));
        todos.add(new Todo("eating my dinner ", "task 9", false));
        todos.add(new Todo("pray then sleep", "task 10", false));
        adapter = new TodoAdapter(this, todos);
        todoList.setAdapter(adapter);

        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                intent.putExtra("title", todos.get(position).getTitle());
                intent.putExtra("desc", todos.get(position).getDesc());
                intent.putExtra("isCompleted", todos.get(position).getCompleted());
                startActivity(intent);
            }
        });

        todoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle(getString(R.string.deleteitem));
                alertDialog.setCancelable(true);
                alertDialog.setMessage(getString(R.string.message));
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.delete), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                       todos.remove(position);
                       adapter.notifyDataSetChanged();

                    }

                });
                alertDialog.show();
                return true;
            }
        });


    }
}