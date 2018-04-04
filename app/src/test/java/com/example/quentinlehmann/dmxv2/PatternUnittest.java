package com.example.quentinlehmann.dmxv2;

import junit.framework.Assert;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by quentin.lehmann on 03/04/2018.
 */

public class PatternUnittest {
    @Test
    public void PaternTest(){

        Pattern pattern;
        Matcher matcher;
        pattern= Pattern.compile("[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}");
        matcher = pattern.matcher("120.130.h.0");
        Assert.assertEquals( false,  matcher.find());
    }
}
