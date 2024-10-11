package com.example.contactdatabaseapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    EditText editTextName, editTextEmail;
    TextView textViewDOB;
    Button btnSave, btnView, btnChooseAvatar;

    int imageID;
    ImageView chooseAvatar;

    int[] images = {R.drawable.profileone, R.drawable.profiletwo, R.drawable.profilethree, R.drawable.profilefour ,R.drawable.profilefive,
            R.drawable.profilesix ,R.drawable.profileseven, R.drawable.profileeight, R.drawable.profilenine };

    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        textViewDOB = findViewById(R.id.textViewDOB);

        chooseAvatar = findViewById(R.id.chooseAvatar);


        btnSave = findViewById(R.id.buttonSave);
        btnView = findViewById(R.id.buttonView);
        btnChooseAvatar = findViewById(R.id.buttonChooseAvatar);


        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        textViewDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDateDialog();
            }
        });

        btnChooseAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAvatars();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewDetails();
            }
        });

    }

    private void displayAvatars() {
        Dialog avatarDialog = new Dialog(this);
        avatarDialog.setContentView(R.layout.avatar_layout);

        // Get the GridView from the dialog layout
        GridView avatarGridView = avatarDialog.findViewById(R.id.avatarGridView);

        ImageAdapter adapter = new ImageAdapter(this, images);

        avatarGridView.setAdapter(adapter);
        // Set an item click listener for the GridView
        avatarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle avatar selection
                int selectedAvatarResId = images[position];

                chooseAvatar.setImageResource(selectedAvatarResId);
                imageID = selectedAvatarResId;

                // Dismiss the dialog
                avatarDialog.dismiss();
            }
        });

        // Show the dialog
        avatarDialog.show();
    }
    /// custom adapter///
    public class ImageAdapter extends BaseAdapter {
        private Context context;
        private int[] images;

        public ImageAdapter(Context context, int[] images) {
            this.context = context;
            this.images = images;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return images[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;

            if (convertView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new AbsListView.LayoutParams(140, 120));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView) convertView;
            }

            imageView.setImageResource(images[position]);

            return imageView;
        }
    }
    ////end of custom adapter


    private void openDateDialog(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,dateSet(), year, month, day);
        datePickerDialog.show();

    }

    private DatePickerDialog.OnDateSetListener dateSet(){
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayofMonth) {
                textViewDOB.setText(months[month]+" "+dayofMonth+", "+year);

            }
        };
    }//end of DateSet

    private void saveDetails() {

        if(imageID == 0){
            imageID = 2131165411;
        }
        Contact contact = new Contact(editTextName.getText().toString(),
                textViewDOB.getText().toString(),
                editTextEmail.getText().toString(),
                imageID);

        long id = databaseHelper.saveContact(contact);

        Toast.makeText(this, "Saved: "+id, Toast.LENGTH_LONG).show();

        editTextName.setText("");
        textViewDOB.setText("Choose Date of Birth");
        editTextEmail.setText("");
    }

    private void viewDetails(){
        Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
        startActivity(intent);
    }


}