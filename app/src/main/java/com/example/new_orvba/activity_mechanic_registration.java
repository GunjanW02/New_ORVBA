package com.example.new_orvba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class activity_mechanic_registration extends AppCompatActivity {

    private EditText firstNameEditText, lastNameEditText, emailEditText, phoneEditText, addressEditText;
    private CheckBox tyreServicesCheckBox, engineServicesCheckBox, oilLeakageCheckBox,
            carTowingCheckBox, manualRepairingCheckBox, otherCheckBox;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic_registration);

        // Initialize UI elements
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

        // Set click listener for the Next button
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call a method to store data in Firebase
                storeMechanicRegistrationDataInFirebase();

                // Redirect to MechanicLocationActivity
                Intent intent = new Intent(activity_mechanic_registration.this, Mloc.class);
                startActivity(intent);
            }
        });
    }

    private void storeMechanicRegistrationDataInFirebase() {
        // Get user input from EditText fields
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName = lastNameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        String address = addressEditText.getText().toString().trim();

        // Get the state of CheckBoxes
        boolean tyreServices = tyreServicesCheckBox.isChecked();
        boolean engineServices = engineServicesCheckBox.isChecked();
        boolean oilLeakage = oilLeakageCheckBox.isChecked();
        boolean carTowing = carTowingCheckBox.isChecked();
        boolean manualRepairing = manualRepairingCheckBox.isChecked();
        boolean other = otherCheckBox.isChecked();

        // Create a reference to the Firebase Realtime Database
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("mechanics");

        // Generate a unique key for each mechanic registration
        String mechanicId = databaseReference.push().getKey();

        // Create a MechanicData object
        Mechanic mechanicData = new Mechanic(
                mechanicId,
                firstName,
                lastName,
                email,
                phone,
                address,
                tyreServices,
                engineServices,
                oilLeakage,
                carTowing,
                manualRepairing,
                other
        );

        // Save the mechanic data to the database
        if (mechanicId != null) {
            databaseReference.child(mechanicId).setValue(mechanicData);
        }
    }
}
