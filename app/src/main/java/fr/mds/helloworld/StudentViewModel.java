package fr.mds.helloworld;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.mds.helloworld.data.dao.StudentDao;
import fr.mds.helloworld.data.models.Student;
import fr.mds.helloworld.utils.AsyncRunner;

public class StudentViewModel extends AndroidViewModel {
    private StudentDao mDao;

    public void setDao(StudentDao dao) {
        mDao = dao;
    }

    public StudentViewModel(@NonNull Application application) {
        super(application);
    }

    public void createStudent(String firstName, String lastName) {
        Student newStudent = new Student(firstName, lastName);
        AsyncRunner runner = new AsyncRunner();
        runner.runTask(() -> {
            mDao.insertStudent(newStudent);
        });
    }

    public LiveData<List<Student>> getAllStudents() {
        return mDao.getAllStudents();
    }
}
