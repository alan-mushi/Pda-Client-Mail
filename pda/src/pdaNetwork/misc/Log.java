package pdaNetwork.misc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * This method is used to write log for the different service on the server.
 * 
 * @author PDA Server development team.
 */
public class Log {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat ("[yyyy/MM/dd HH:mm:ss] ");

	/**
	 * This static method is used to write on a file, in a directory specified in the Configuration File, there will be different log file for each type of information.
	 * 
	 * @param serviceName Service name, it will also be the File name (serviceName.log)
	 * @param message The message you want to save.
	 */
	public static void writeLog (String serviceName, String message) {

		String path = ConfigConst.getLogPath ();

		try {
			BufferedWriter out = new BufferedWriter (new FileWriter (path + serviceName+".log", true));
			out.write (dateFormat.format (new Date ())+message+"\n");
			out.flush ();
			out.close ();
		} catch (IOException e) {
			e.printStackTrace ();
		}
	}
}