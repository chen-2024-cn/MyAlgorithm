package Other;


import java.time.*;
import java.time.temporal.ChronoUnit;


public class DateProblem {
    public static void main(String[] args) {
        String birthday="2005-08-30";
        String current="2025-04-12";
        LocalDate bir=LocalDate.parse(birthday);
        LocalDate cur=LocalDate.parse(current);
        Period diff=Period.between(bir,cur);
        System.out.println("年龄:"+diff.getYears());//获取年龄

        long days= ChronoUnit.DAYS.between(bir,cur);//获取两日期差值
        System.out.println("两日期相差："+days+"天");

        int dayOfWeek=cur.getDayOfWeek().getValue();//获取星期
        System.out.println(current+"是星期"+dayOfWeek);

        LocalDate newdays=cur.plusDays(20);//天数相加
        System.out.println(current+" 加20天后为："+newdays);
        System.out.println(current+" 减20天后为："+cur.minusDays(20));//天数相减
    }
}
