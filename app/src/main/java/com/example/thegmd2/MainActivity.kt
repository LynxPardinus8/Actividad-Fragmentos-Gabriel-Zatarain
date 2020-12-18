package com.example.thegmd2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.thegmd2.adapters.RecyclerViewAdapter
import com.example.thegmd2.databinding.ActivityMainBinding
import com.example.thegmd2.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val spinner = binding.spinner

        ArrayAdapter.createFromResource(
            this,
            R.array.subjects,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = adapter
        }
        spinner.onItemSelectedListener = this


        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onStart() {
        super.onStart()

        val jsonFileString = getJsonDataFromAsset(this, "data.json")
        val gson = Gson()
        val jsonObj = JSONObject(jsonFileString!!)
        val students = jsonObj.getJSONArray("students")
        val listStudentType = object : TypeToken<List<Student>>() {}.type
        val studentList: List<Student> = gson.fromJson(students.toString(), listStudentType)


        rvAdapter = RecyclerViewAdapter(studentList)
        binding.recyclerView.adapter = rvAdapter
        rvAdapter.notifyDataSetChanged()


        for (i in 0 until students.length()) {
            val studentObj = students.getJSONObject(i)


            val stds: Student = Student(
                studentObj.getInt("codigo",),
                studentObj.getString("nombre"),
                studentObj.getString("apellido")
            )


            Log.d("json", studentObj.toString())
        }


    }


    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        val selection = parent?.getItemAtPosition(pos)

        when (selection) {
            "BBDD" -> binding.textViewTeacherName.text = "Manuel Perez"
            "programacion" -> binding.textViewTeacherName.text = "Antonio Mendoza"
        }


    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //TODO("Not yet implemented")
    }
}