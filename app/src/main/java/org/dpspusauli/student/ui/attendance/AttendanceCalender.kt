package org.dpspusauli.student.ui.attendance

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener
import kotlinx.android.synthetic.main.parent_activity_calender_attendance.*
import org.dpspusauli.R
import org.dpspusauli.student.model.AttendanceData
import org.dpspusauli.student.mvvm.AttendanceViewModel
import org.dpspusauli.utils.convertMongoDate
import org.dpspusauli.utils.toast
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class AttendanceCalender : AppCompatActivity(), OnRangeSelectedListener {
    private val viewModel: AttendanceViewModel by viewModel()
    private var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    private var materialCalendarView: MaterialCalendarView? = null
    private var list: MutableList<ModelAttendance>? = mutableListOf()
    private var dateList: MutableList<String>? = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parent_activity_calender_attendance)

        setupViewModel()
        hideShowProgress(true)
        val studentId=intent.extras?.getString("student_id")?:""
     //   viewModel.getAttendance(studentId)
        viewModel.getAttendance("6171ba9cf3f0f8a101e7047d")

        materialCalendarView = findViewById<View>(R.id.calendarView) as MaterialCalendarView
        materialCalendarView!!.selectionMode = MaterialCalendarView.SELECTION_MODE_NONE
        materialCalendarView!!.setOnRangeSelectedListener(this)


        att_btn_click_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setupViewModel() {
        viewModel.dataAttendance.observe(this, Observer {
            hideShowProgress(false)
            dataParse(it)
        })
        viewModel.errorMsg.observe(this, Observer {
            hideShowProgress(false)
            toast(it)
           // onBackPressed()
        })
    }

    private fun dataParse(it: List<AttendanceData>) {
        var presentCount=0
        var absentCount=0
        var leaveCount=0
        var holidayCount=0

        for (i in  it) {
            if(i.type=="Present"){
                presentCount+=1
            }
            if(i.type=="Absent"){
                absentCount+=1
            }
            if(i.type=="Leave"){
                leaveCount+=1
            }
            if(i.type=="Holiday"){
                holidayCount+=1
            }
            val dateString = i.createdAt.convertMongoDate()
            list!!.add(ModelAttendance(dateString,i.type))
            dateList!!.add(dateString)
            materialCalendarView!!.selectRange(CalendarDay.from(sdf.parse(dateString)), CalendarDay.from(sdf.parse(dateString)))
        }
        tv_present.text="Present  No of Days: $presentCount"
        tv_absent.text="Absent  No of Days: $absentCount"
        tv_holiday.text="Holiday  No of Days: $holidayCount"
        tv_leave.text="Leave  No of Days: $leaveCount"
    }



    override fun onRangeSelected(widget: MaterialCalendarView, dates: List<CalendarDay>) {
        for (i in dates.indices) {
            //    widget.setDateSelected(dates.get(i), true);
            Log.d("Date OnRange", dates[i].date.toString())
            val indexOfDate = dateList!!.indexOf(sdf.format(dates[i].date))
            Log.d("DateIndex", indexOfDate.toString())
            if (indexOfDate >= 0) {
                when (list!![indexOfDate].status) {
                    "Present" -> {
                        widget.setDateSelected(dates[i], false)
                        widget.addDecorator(BookingDecorator(this, list!![indexOfDate].status, dates))
                    }
                    "Absent" -> {
                        widget.setDateSelected(dates[i], false)
                        widget.addDecorator(BookingDecorator(this, list!![indexOfDate].status, dates))
                    }
                    "Leave" -> {
                        widget.setDateSelected(dates[i], false)
                        widget.addDecorator(BookingDecorator(this, list!![indexOfDate].status, dates))
                    }
                    "Holiday" -> {
                        widget.setDateSelected(dates[i], false)
                        widget.addDecorator(BookingDecorator(this, list!![indexOfDate].status, dates))
                    }
                    else -> {
                        widget.setDateSelected(dates[i], false)
                        widget.addDecorator(BookingDecorator(this, list!![indexOfDate].status, dates))
                    }
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
data class ModelAttendance(val atten_date: String, var status: String)




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
