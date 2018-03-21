package example.com.listviewapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.listviewapp.model.Person;

public class MainActivity extends AppCompatActivity {

static List<Person> list = new ArrayList<>();
static int i =0;
    ListView listPerson;
    PersonAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         listPerson = findViewById(R.id.main_list_view);
      customAdapter = new PersonAdapter(this, R.layout.listitem, /*PersonKeeper.getInstance().getPersonList()*/ list);
        //       customAdapter.add(new Person("a","a","20/03/1999"));
        listPerson.setAdapter(customAdapter);


  //      Bundle extras = getIntent().getExtras();
  //      if (extras != null) {
 //           customAdapter.addAll(PersonKeeper.getInstance().getPersonList());

 //       }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addNewPerson_settings)
            startActivityForResult(new Intent(this, NewPersonActivity.class),0);
        return true;
    }
@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0)

    customAdapter.add(list.get(i-1));
                // do your task


                // do your task

        }


}

class PersonAdapter extends ArrayAdapter<Person>{

   public PersonAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater;
            layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(R.layout.listitem, null);
        }

        Button  deleteButton = view.findViewById(R.id.row_button);
       // deleteButton.setTag(position);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer index = (Integer) view.getTag();
                remove(getItem(position));
                notifyDataSetChanged();
            }
        });



        Person p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) view.findViewById(R.id.name_row_textView);
            TextView tt2 = (TextView) view.findViewById(R.id.surname_row_textView);
            TextView tt3 = (TextView) view.findViewById(R.id.birthDate_row_textView);

            if (tt1 != null) {
                tt1.setText(p.getName()/*"name here"*/);
            }

            if (tt2 != null) {
                tt2.setText(p.getSurname()/*"surname here"*/);
            }

            if (tt3 != null) {
                tt3.setText(p.getBirthDate()/*"birthdate here"*/);
            }
        }

        return view;
    }
}