package com.example.new_orvba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class mechanic_form extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, emailEditText, phoneEditText, addressEditText;
    private CheckBox tyreServicesCheckBox, engineServicesCheckBox, oilLeakageCheckBox,
            carTowingCheckBox, manualRepairingCheckBox, otherCheckBox;
    private Button nextButton;

    // Add a reference to the Realtime Database
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mechanic_form);

        // Initialize the views
        firstNameEditText = findViewById(R.id.FirstName);
        lastNameEditText = findViewById(R.id.LastName);
        emailEditText = findViewById(R.id.Email);
        phoneEditText = findViewById(R.id.Phone);
        addressEditText = findViewById(R.id.Address);

        tyreServicesCheckBox = findViewById(R.id.checkBox12);
        engineServicesCheckBox = findViewById(R.id.checkBox13);
        oilLeakageCheckBox = findViewById(R.id.checkBox14);
        carTowingCheckBox = findViewById(R.id.checkBox15);
        manualRepairingCheckBox = findViewById(R.id.checkBox16);
        otherCheckBox = findViewById(R.id.checkBox17);

        nextButton = findViewById(R.id.neext);

        // Initialize the Realtime Database reference
        mDatabase = FirebaseDatabase.getInstance().getReference().child("mechanics");

        // Set click listener for the Next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve data from EditText and CheckBox
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String address = addressEditText.getText().toString();

                boolean tyreServices = tyreServicesCheckBox.isChecked();
                boolean engineServices = engineServicesCheckBox.isChecked();
                boolean oilLeakage = oilLeakageCheckBox.isChecked();
                boolean carTowing = carTowingCheckBox.isChecked();
                boolean manualRepairing = manualRepairingCheckBox.isChecked();
                boolean other = otherCheckBox.isChecked();

                // Create a unique key for the mechanic
                String mechanicId = mDatabase.push().getKey();

                // Create a Mechanic object with the provided data
                Mechanic mechanic = new Mechanic(mechanicId, firstName, lastName, email, phone, address,
                        tyreServices, engineServices, oilLeakage, carTowing, manualRepairing, other);

                // Store mechanic data in the Realtime Database under the "mechanics" node
                mDatabase.child(mechanicId).setValue(mechanic);

                // You can use the data as needed, for example, pass it to the next activity
                Intent intent = new Intent(mechanic_form.this, activity_mechanicsside.class);
                startActivity(intent);
            }
        });
    }
}
