package com.querydsl.sql;

import com.querydsl.core.types.Ops;

public class ClickHouseTemplates extends SQLTemplates {
    public ClickHouseTemplates() {
        this('\\', false);
    }

    public ClickHouseTemplates(boolean quote) {
        this('\\', quote);
    }

    public ClickHouseTemplates(char escape, boolean quote) {
        super(Keywords.CLICKHOUSE, "\"", escape, quote, false);
        setDummyTable(null);
        // select distinct (C_CITY, C_REGION) from customer
        setCountDistinctMultipleColumns(false);
        setSupportsUnquotedReservedWordsAsIdentifier(true);
        setForShareSupported(false);

        add(Ops.MOD, "{0} % {1}", Precedence.ARITH_HIGH);
        add(Ops.LIKE, "like({0},{1})");
        add(Ops.STARTS_WITH, "like({0},{1%})");
        add(Ops.ENDS_WITH, "like({0},{%1})");
        add(Ops.STRING_CONTAINS, "like({0},{%1%})");
        add(Ops.MathOps.RANDOM, "rand()");


        // Date / time
        add(Ops.DateTimeOps.DAY_OF_MONTH, "toDayOfMonth(cast({0} as Date))");
        add(Ops.DateTimeOps.DAY_OF_WEEK, "toDayOfWeek(cast({0} as Date))");
        add(Ops.DateTimeOps.DAY_OF_YEAR, "toDayOfYear(cast({0} as Date))");
        add(Ops.DateTimeOps.HOUR, "toHour(cast({0} as DateTime))");
        add(Ops.DateTimeOps.MINUTE, "toMinute(cast({0} as DateTime))");
        add(Ops.DateTimeOps.MONTH, "toMonth(cast({0} as Date))");
        add(Ops.DateTimeOps.SECOND, "toSecond(cast({0} as DateTime))");
        add(Ops.DateTimeOps.WEEK, "toISOWeek(cast({0} as Date))");

        add(Ops.DateTimeOps.YEAR, "toYear(cast({0} as Date))");
        add(Ops.DateTimeOps.YEAR_MONTH, "toYYYYMM(cast({0} as Date))");
        add(Ops.DateTimeOps.YEAR_WEEK, "toISOYear(cast({0} as Date)) * 100 + toISOWeek(cast({0} as Date))");

        add(Ops.DateTimeOps.ADD_YEARS, "addYears({0}, {1}))");
        add(Ops.DateTimeOps.ADD_MONTHS, "addMonths({0}, {1}))");
        add(Ops.DateTimeOps.ADD_WEEKS, "addWeeks({0}, {1}))");
        add(Ops.DateTimeOps.ADD_DAYS, "addDays({0}, {1}))");
        add(Ops.DateTimeOps.ADD_HOURS, "addHours({0}, {1}))");
        add(Ops.DateTimeOps.ADD_MINUTES, "addMinutes({0}, {1}))");
        add(Ops.DateTimeOps.ADD_SECONDS, "addSeconds({0}, {1}))");

        add(Ops.DateTimeOps.DIFF_YEARS, "dateDiff('year', {1}, {0})");
        add(Ops.DateTimeOps.DIFF_MONTHS, "dateDiff('month', {1}, {0})");
        add(Ops.DateTimeOps.DIFF_WEEKS, "dateDiff('week', {1}, {0})");
        add(Ops.DateTimeOps.DIFF_DAYS, "dateDiff('day', {1}, {0})");
        add(Ops.DateTimeOps.DIFF_HOURS, "dateDiff('hour', {1}, {0})");
        add(Ops.DateTimeOps.DIFF_MINUTES, "dateDiff('minute', {1}, {0})");
        add(Ops.DateTimeOps.DIFF_SECONDS, "dateDiff('second', {1}, {0})");
    }
}
