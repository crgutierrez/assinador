//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package br.com.atos.utils;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DataUtils {
    public DataUtils() {
    }

    public static Calendar getCalendarHojeFim() {
        Calendar calendar = getCalendarHojeInicio();
        calendar.add(6, 1);
        calendar.add(14, -1);
        return calendar;
    }

    public static Calendar getCalendarHojeInicio() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 0);
        return calendar;
    }

    public static Date getDateHojeInicio() {
        return getCalendarHojeInicio().getTime();
    }

    public static Date getDateHojeFim() {
        return getCalendarHojeFim().getTime();
    }

    public static String getData(Date data) {
        return (new SimpleDateFormat("dd/MM/yyyy")).format(data);
    }

    public static String getDataHorario(Date data) {
        return data != null?(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(data):"";
    }

    public static String getDataPorExtenso(Date data) {
        return data != null?DateFormat.getDateInstance(1, new Locale("pt", "BR")).format(data).toString():"";
    }

    public static String getDataHorarioPorExtenso(Date data) {
        return data != null?MessageFormat.format("{0} às {1}", new Object[]{DateFormat.getDateInstance(1, new Locale("pt", "BR")).format(data), DateFormat.getTimeInstance(3, new Locale("pt", "BR")).format(data)}):"";
    }

    public static Calendar getCalendarInicio(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return getCalendarInicio(calendar);
    }

    public static Calendar getCalendarInicio(Calendar calendar) {
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 0);
        return calendar;
    }

    public static Calendar getCalendarFim(Date data) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        return getCalendarFim(calendar);
    }

    public static Calendar getCalendarFim(Calendar calendar) {
        calendar = getCalendarInicio(calendar);
        calendar.add(6, 1);
        calendar.add(14, -1);
        return calendar;
    }

    public static Date getDateInicio(Date data) {
        return getCalendarInicio(data).getTime();
    }

    public static Date getDateFim(Date data) {
        return getCalendarFim(data).getTime();
    }

    public static boolean isMesmoDia(Date dtData, Date dtComparacao) {
        GregorianCalendar data = new GregorianCalendar();
        data.setTime(dtData);
        GregorianCalendar comparacao = new GregorianCalendar();
        comparacao.setTime(dtComparacao);
        return data.get(6) == comparacao.get(6) && data.get(1) == comparacao.get(1);
    }

    public static long diferencaEmMilissegundos(Date dataMaior, Date dataMenor) {
        return dataMaior.getTime() - dataMenor.getTime();
    }

    public static long diferencaEmSegundos(Date dataMaior, Date dataMenor) {
        return diferencaEmMilissegundos(dataMaior, dataMenor) / 1000L;
    }

    public static long diferencaEmMinutos(Date dataMaior, Date dataMenor) {
        return diferencaEmSegundos(dataMaior, dataMenor) / 60L;
    }

    public static long diferencaEmHoras(Date dataMaior, Date dataMenor) {
        return diferencaEmMinutos(dataMaior, dataMenor) / 60L;
    }

    public static long diferencaEmDias(Date dataMaior, Date dataMenor) {
        return diferencaEmHoras(dataMaior, dataMenor) / 24L;
    }

    public static int getUltimoDiaDoMesDaData(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        return cal.getActualMaximum(5);
    }

    public static boolean isMesCom31Dias(Date data) {
        return getUltimoDiaDoMesDaData(data) == 31;
    }

    public static boolean isMesCom30Dias(Date data) {
        return getUltimoDiaDoMesDaData(data) == 30;
    }

    public static boolean isMesCom29Dias(Date data) {
        return getUltimoDiaDoMesDaData(data) == 29;
    }

    public static boolean isMesCom28Dias(Date data) {
        return getUltimoDiaDoMesDaData(data) == 28;
    }

    public static boolean isDomingo(Calendar data) {
        return data.get(7) == 1;
    }

    public static boolean isFinalSemana(Calendar data) {
        return isDomingo(data) || isSabado(data);
    }

    public static boolean isSabado(Calendar data) {
        return data.get(7) == 7;
    }

    public static Date getDateInicio(Calendar cal) {
        return getCalendarInicio(cal).getTime();
    }

    public static String getHorario(Date data) {
        return (new SimpleDateFormat("HH:mm:ss")).format(data);
    }
}
