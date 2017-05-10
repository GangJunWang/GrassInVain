package bluetooth.inuker.com.grassinvain.common.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by 1 on 2016/12/15.
 */
public class DateDialog {
    private static DateDialog dateDialog;
    private Context context;

    private DateDialog(Context context){
        this.context = context;
    }

    public static DateDialog getInstance(Context context){
        if(dateDialog == null){
            synchronized (DateDialog.class){
                if(dateDialog == null){
                    synchronized (DateDialog.class) {
                        dateDialog = new DateDialog(context);
                    }
                }
            }
        }
        return dateDialog;
    }

    public DateDialog show(){
        DatePickerDialog dateDlg = new DatePickerDialog(context, d, dateAndTime.get(Calendar.YEAR), dateAndTime.get(Calendar.MONTH), dateAndTime.get(Calendar.DAY_OF_MONTH));
        dateDlg.show();
        return dateDialog;
    }

    Calendar dateAndTime = Calendar.getInstance(Locale.CHINA);
    DateFormat fmtDate = new java.text.SimpleDateFormat("dd/MM/yyyy");

    //当点击DatePickerDialog控件的设置按钮时，调用该方法
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            //修改日历控件的年，月，日
            //这里的year,monthOfYear,dayOfMonth的值与DatePickerDialog控件设置的最新值一致
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            //将页面TextView的显示更新为最新时间
            if(onDateListener != null){
                onDateListener.select(fmtDate.format(dateAndTime.getTime()));
            }
        }
    };

    private OnDateListener onDateListener;

    public void setOnDateListener(OnDateListener onDateListener) {
        this.onDateListener = onDateListener;
    }

    public interface OnDateListener{
        void select(String result);
    }

}
