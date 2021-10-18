package org.dpspusauli

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.dpspusauli.student.ui.home.StudentDashboardActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnStudent.setOnClickListener {
           startActivity(Intent(this, StudentDashboardActivity::class.java))
        }

    }
}