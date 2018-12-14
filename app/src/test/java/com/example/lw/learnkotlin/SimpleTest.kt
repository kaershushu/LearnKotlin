package com.example.lw.learnkotlin

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.DateFormat

/**
 * Created on 2018/12/14.
 * @author Alan
 */
class SimpleTest {
    @Test fun unitTestWorks(){
        assert(true)
    }

    @Test fun testLongToDateString(){
        assertEquals("Oct 19, 2015", 1445275635000L.toDateString(DateFormat.WEEK_OF_YEAR_FIELD))
    }

    @Test fun testDateStringFullFormat(){
        assertEquals("Oct 19, 2015", 1445275635000L.toDateString())
    }
}