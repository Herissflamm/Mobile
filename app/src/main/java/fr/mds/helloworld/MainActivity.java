package fr.mds.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import javax.xml.datatype.Duration;

import fr.mds.helloworld.data.database.AppDatabase;
import fr.mds.helloworld.data.models.Student;
import fr.mds.helloworld.utils.DatabaseInitializer;

public class MainActivity extends AppCompatActivity {
    private StudentViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseInitializer.populateDatabaseAsync(AppDatabase.getInstance(getApplicationContext()));

        mViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        mViewModel.setDao(AppDatabase.getInstance(getApplicationContext()).getStudentDao());
    }

    public void listStudents() {
        LiveData<List<Student>> students = mViewModel.getAllStudents();

        students.observe(this, students1 -> {
            for (Student student : students1) {
                ((TextView)findViewById(R.id.tv)).setText(student.getFirstName());
            }
        });
    }
}