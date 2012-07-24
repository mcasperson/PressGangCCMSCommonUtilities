package org.jboss.pressgangccms.utils.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.pressgangccms.utils.constants.CommonConstants;

public class NotificationUtilities
{
	public static void dumpMessageToStdOut(final String message)
	{
		final SimpleDateFormat formatter = new SimpleDateFormat(CommonConstants.FILTER_DISPLAY_DATE_FORMAT);					
		System.out.println("[" + formatter.format(new Date()) + "] " + message);
	}
}
