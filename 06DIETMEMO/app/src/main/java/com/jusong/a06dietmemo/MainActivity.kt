package com.jusong.a06dietmemo

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.Calendar
import java.util.GregorianCalendar

class MainActivity : AppCompatActivity() {

    val dataModelList = mutableListOf<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.getReference("myMemo")

        val listView = findViewById<ListView>(R.id.mainLV)

        val adapter_list = ListViewAdapter(dataModelList)

        listView.adapter = adapter_list

        myRef.child(Firebase.auth.currentUser!!.uid).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                dataModelList.clear()

                for (dataModel in snapshot.children) {
                    Log.d("Data", dataModel.toString())
                    //snapshot에 여러개의 저장 항목들이 들어있기 때문에 반복문을 통해 한개씩 접근
                    dataModelList.add(dataModel.getValue(DataModel::class.java)!!)
                }
                adapter_list.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        val writeButton = findViewById<ImageView>(R.id.writeBtn)
        writeButton.setOnClickListener {

            val mDialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("운동 메모 다이얼로그")

            val mAlertDialog = mBuilder.show()

            val dateSelectBtn = mAlertDialog.findViewById<Button>(R.id.dateSelectBtn)

            var dateText = ""

            dateSelectBtn?.setOnClickListener {

                val today = GregorianCalendar()
                val year : Int = today.get(Calendar.YEAR)
                val month : Int = today.get(Calendar.MONTH)
                val date : Int = today.get(Calendar.DATE)
                val dlg = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, date: Int) {
                        Log.d("MAIN", "${year}. ${month+1}. ${date}") //${year} = year.toString()
                        dateSelectBtn.setText("${year}. ${month+1}. ${date}")

                        dateText = "${year}. ${month+1}. ${date}"
                    }
                }, year, month, date)
                dlg.show()
            }

            val saveBtn = mAlertDialog.findViewById<Button>(R.id.saveBtn)
            saveBtn?.setOnClickListener {

                val healMemo = mAlertDialog.findViewById<EditText>(R.id.healthMemo)?.text.toString()

                // Write a message to the database
                val database = Firebase.database
                val myRef = database.getReference("myMemo").child(Firebase.auth.currentUser!!.uid)

                val model = DataModel(dateText, healMemo)
                myRef
                    .push()
                    .setValue(model)

                mAlertDialog.dismiss()

            }
        }
    }
}