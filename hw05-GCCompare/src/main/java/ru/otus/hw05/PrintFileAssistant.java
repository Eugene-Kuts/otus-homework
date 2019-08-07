package ru.otus.hw05;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import com.sun.management.GarbageCollectionNotificationInfo;

public class PrintFileAssistant {
	private static String path = ".\\hw05-GCCompare\\data\\";
	private static PrintFileAssistant fs;
	private PrintWriter out;
	private long START_TIME = System.currentTimeMillis();

	private PrintFileAssistant() {
		String fileName = getVirtualMachineGCArgument();
		try {
			out = new PrintWriter(path +fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static PrintFileAssistant getInstance() {
		if (fs == null)
			fs = new PrintFileAssistant();
		return fs;
	}

	public void illustrateGCAction(GarbageCollectionNotificationInfo info) {
		long duration = info.getGcInfo().getDuration();
		String gcAction = info.getGcAction();
		// Вывод данных для статистики в файл
		long elapsedTime = (System.currentTimeMillis() - START_TIME);
		out.println("GC Action:" + gcAction + ", Duration(mls):" + duration + ", Cause:" + info.getGcCause()
				+ ", Elapsed time since start (mls):" + elapsedTime);
		// Вывод данных в консоль
		System.out.println("GC Action: " + gcAction + ": - " + info.getGcName() + ", cause: "
				+ info.getGcCause() + " , duration: " + duration + " milliseconds");
	}

	static String getVirtualMachineGCArgument() {
		String gcArgument = "undefinedGC";
		RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
		List<String> arguments = runtimeMxBean.getInputArguments();
		for (String arg : arguments) {
			if (arg.startsWith("-XX:+Use")) {
				gcArgument = arg.substring(5);
			}
		}
		return gcArgument;
	}

	public void close() {
		out.close();
	}
}
