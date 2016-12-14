package com.andre.core;

import java.nio.charset.Charset;

public class CoreConstants {

	public static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");

	public static final String ID_GENERATOR_CLASS = "com.andre.core.repository.ClockSeqIdGenerator";

	public static final int USER_NAME_MAX_LENGTH = 100;
	public static final int USER_FIRST_LAST_NAME_MAX_LENGTH = 50;

}
