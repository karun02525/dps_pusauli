package org.dpspusauli.student.ui.attendance;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import org.dpspusauli.R;

import java.util.Collection;
import java.util.HashSet;


class BookingDecorator implements DayViewDecorator {
   private Context context;
   private String status;
   private HashSet<CalendarDay> mCalendarDayCollection;
   BookingDecorator(Context context, String status, Collection<CalendarDay> dates) {
       this.context = context;
       this.status = status;
       mCalendarDayCollection = new HashSet<>(dates);
   }

   @Override
   public boolean shouldDecorate(CalendarDay day) {
       return mCalendarDayCollection.contains(day);
   }

   @Override
   public void decorate(DayViewFacade view) {
    //   view.addSpan(new ForegroundColorSpan(mColor));
       //view.addSpan(new BackgroundColorSpan(Color.BLUE));
       switch (status) {
           case "present":
               view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_dot_green));
               break;
           case "absent":
               view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_dot_red));
               break;
           case "leave":
               view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_dot_blue));
               break;
           case "holiday":
               view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_dot_violet_red));
               break;
           default:
               view.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.ic_dot_yellow));
               break;
       }

   }
}
