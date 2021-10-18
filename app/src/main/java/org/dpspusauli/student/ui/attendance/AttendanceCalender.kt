package org.dpspusauli.student.ui.attendance

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener
import kotlinx.android.synthetic.main.parent_activity_calender_attendance.*
import org.dpspusauli.R
import org.dpspusauli.student.model.AttendanceData
import org.dpspusauli.student.mvvm.AttendanceViewModel
import org.dpspusauli.utils.toast
import org.json.JSONException
import org.json.JSONObject
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AttendanceCalender : AppCompatActivity(), OnRangeSelectedListener {
    private val viewModel: AttendanceViewModel by viewModel()
    internal var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    private var materialCalendarView: MaterialCalendarView? = null
    private var list: MutableList<Model_Attendance>? = mutableListOf()
    private var dateList: MutableList<String>? = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parent_activity_calender_attendance)

        setupViewModel()
        hideShowProgress(true)
        val studentId=intent.extras?.getString("student_id")?:""
        viewModel.getAttendance(studentId)

        materialCalendarView = findViewById<View>(R.id.calendarView) as MaterialCalendarView
        materialCalendarView!!.selectionMode = MaterialCalendarView.SELECTION_MODE_NONE
        materialCalendarView!!.setOnRangeSelectedListener(this)


        att_btn_click_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupViewModel() {
        viewModel.data.observe(this, Observer {
            hideShowProgress(false)
            dataParse(it)
        })
        viewModel.errorMsg.observe(this, Observer {
            hideShowProgress(false)
            toast(it)
            onBackPressed()
        })
    }

    private fun dataParse(it: AttendanceData) {
        
        for (i in  it.attlist!!) {
            val dateString = i.attenDate
            list!!.add(Model_Attendance(dateString,i.attType))
            dateList!!.add(dateString!!)
            materialCalendarView!!.selectRange(CalendarDay.from(sdf.parse(dateString)), CalendarDay.from(sdf.parse(dateString)))
        }
    }



    override fun onRangeSelected(widget: MaterialCalendarView, dates: List<CalendarDay>) {
        for (i in dates.indices) {
            //    widget.setDateSelected(dates.get(i), true);
            Log.d("Date OnRange", dates[i].date.toString())
            val indexOfDate = dateList!!.indexOf(sdf.format(dates[i].date))
            Log.d("DateIndex", indexOfDate.toString())
            if (indexOfDate >= 0) {
                if (list!![indexOfDate].status == "1") {
                    widget.setDateSelected(dates[i], false)
                    widget.addDecorator(BookingDecorator(this, list!![indexOfDate].status, dates))
                } else if (list!![indexOfDate].status == "3") {
                    widget.setDateSelected(dates[i], false)
                    widget.addDecorator(BookingDecorator(this, list!![indexOfDate].status, dates))
                } else if (list!![indexOfDate].status == "4") {
                    widget.setDateSelected(dates[i], false)
                    widget.addDecorator(BookingDecorator(this, list!![indexOfDate].status, dates))
                } else {
                    widget.setDateSelected(dates[i], false)
                    widget.addDecorator(BookingDecorator(this, list!![indexOfDate].status, dates))
                }
            } else {
                widget.setDateSelected(dates[i], false)
            }

        }
    }
    private fun hideShowProgress(flag: Boolean) {
        if (flag) progress_circular.visibility = View.VISIBLE else progress_circular.visibility = View.GONE
    }
}




/*

{
"success": true,
"attlist": [
{
  "status": "1",
  "atten_date": "2018-01-10"
},
{
  "status": "2",
  "atten_date": "2018-01-12"
},
{
  "status": "1",
  "atten_date": "2018-01-20"
},
{
  "status": "1",
  "atten_date": "2018-01-13"
},
{
  "status": "3",
  "atten_date": "2018-01-14"
},
{
  "status": "3",
  "atten_date": "2018-01-26"
},
{
  "status": "4",
  "atten_date": "2018-01-22"
},
{
  "status": "4",
  "atten_date": "2018-03-2"
}
]
}


*/
