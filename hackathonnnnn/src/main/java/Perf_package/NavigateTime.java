package Perf_package;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

public class NavigateTime {
	static ArrayList<HashMap<String, String>> renderedList = new ArrayList<>();
	static ArrayList<Double> timeTaken = new ArrayList<Double>();
	static ArrayList<String> requestUrl = new ArrayList<String>();
	static ArrayList<Integer> transferSize = new ArrayList<Integer>();

	static double ETETime = 0.0;
	static long totalSize = 0;
	static int noOfRequests = 0;
	static Long networkTime = 0L, totalpageloadtime = 0L, pageRenderTime = 0L, DOMContentLoaded = 0L;

	public static void theMain(String action, ArrayList requestsList, String timingList, int iterator, int action_num)
			throws InterruptedException {

		timeTaken = new ArrayList<Double>();
		requestUrl = new ArrayList<String>();
		transferSize = new ArrayList<Integer>();

		if (renderTheData(requestsList)) {
			getNoOfRequests();
			getTimeTakenAndSizeForAllRequests(action);
			getTotalSize();
			getETETime(action);
			getNetworkDuration(timingList);
			if (iterator == 0) {// only for iteration 1 print to excell
				exportToexcel(action);
				export_hotspot_results(action);
			}
			if (iterator == 1) {// only for iteration 2 i.e withcache print to excell
				exportToexcelwithCache(action);
				export_hotspot_resultswitCache(action);
			}
			if (timingList == null) {
				exportToSummaryExcel(action, false);
			} else
				exportToSummaryExcel(action, true);
		}

	}

	private static void export_hotspot_resultswitCache(String action) {
		export_Result_to_Excel.export_hotspot_results_withCache(action);
	}

	private static void exportToexcelwithCache(String action) {
		export_Result_to_Excel.export_Detailed_Results_withCache(action, requestUrl, timeTaken);
	}

	public static void exportToexcel(String action) {
		export_Result_to_Excel.export_Detailed_Results(action, requestUrl, timeTaken);
	}

	public static void exportToSummaryExcel(String action, boolean flag) {
		export_Result_to_Excel.export_Summary_results(action, flag);
	}

	public static void export_hotspot_results(String action) {

		export_Result_to_Excel.export_hotspot_results(action);
	}

	static void getNetworkDuration(String timingList) {
		if (timingList != null) {
			HashMap<String, Long> hm = new HashMap<String, Long>();
			timingList = timingList.substring(1, timingList.length() - 1);
			String[] timingListList = timingList.split(",");
			for (int i = 0; i < timingListList.length; i++) {
				String[] timingDetail = timingListList[i].split("=");
				if (timingDetail[0] != null) {
					timingDetail[0] = timingDetail[0].trim();
				}
				if (timingDetail.length > 1 && timingDetail[1] != null) {
					timingDetail[1] = timingDetail[1].trim();
					if (timingDetail[0].contains("navigationStart") || timingDetail[0].contains("responseEnd")
							|| timingDetail[0].contains("requestStart") || timingDetail[0].contains("loadEventEnd")
							|| timingDetail[0].contains("domComplete") || timingDetail[0].contains("domLoading")
							|| timingDetail[0].contains("domContentLoadedEventEnd")) {
						hm.put(timingDetail[0], Long.parseLong(timingDetail[1]));
					}
				}
			}
			networkTime = hm.get("requestStart") - hm.get("navigationStart");
			totalpageloadtime = hm.get("loadEventEnd") - hm.get("navigationStart");
			pageRenderTime = hm.get("domComplete") - hm.get("domLoading");
			DOMContentLoaded = hm.get("domContentLoadedEventEnd") - hm.get("navigationStart");
			System.out.println("Time to First Byte " + networkTime + "total page load Time is " + totalpageloadtime
					+ "page Rendering Time " + pageRenderTime + "DOMcontentLoaded time is " + DOMContentLoaded);
		}
	}

	// each request in ArrayList with values as hashmap
	public static boolean renderTheData(ArrayList requestsList) {

		renderedList = new ArrayList();
		if (requestsList != null) {
			for (var i = 0; i < requestsList.size(); i++) {
				HashMap<String, String> hm = new HashMap<>();
				var ele = requestsList.get(i);
				String requestStr = ele.toString();
				requestStr = requestStr.substring(1, requestStr.length() - 1);
				String[] requestStrList = requestStr.split(",");
				boolean add_hm = true;
				for (int j = 0; j < requestStrList.length; j++) {

					String[] requestDetail = requestStrList[j].split("=");
					if (requestDetail[0] != null) {
						requestDetail[0] = requestDetail[0].trim();
					}
					if (requestDetail.length > 1 && requestDetail[1] != null) {
						if (requestDetail[0].contains("startTime") || requestDetail[0].contains("name")
								|| requestDetail[0].contains("duration") || requestDetail[0].contains("transferSize")
								|| requestDetail[0].contains("responseEnd")) {
							requestDetail[1] = requestDetail[1].trim();

							hm.put(requestDetail[0], requestDetail[1]);
						}
					}
				}
				if (add_hm) {
					renderedList.add(hm);
				}

			}
			System.out.println("from render data method" + renderedList);
			return true;
		} else {
			return false;
		}
	}

	public static void getNoOfRequests() {
		noOfRequests = renderedList.size();
		System.out.println("Number of requests: " + noOfRequests);
	}

	public static double getTotalSize() {
		totalSize = 0;
		for (int i = 0; i < transferSize.size(); i++) {
			totalSize += transferSize.get(i);
		}
		return totalSize;
	}

	public static void getTimeTakenAndSizeForAllRequests(String action) {
		int max = 0, index = 0;
		Integer size = 0;
		for (int i = 0; i < renderedList.size(); i++) {

			HashMap<String, String> hm = renderedList.get(i);
			String reqName = hm.get("name");
			size = Integer.parseInt(hm.get("transferSize"));
			Double timeTakenReq = Double.parseDouble(hm.get("duration"));

			BigDecimal bd = new BigDecimal(timeTakenReq).setScale(2, RoundingMode.HALF_UP);
			double timetaken = bd.doubleValue();
			timeTaken.add(timetaken);
			requestUrl.add(action + "_" + reqName);
			transferSize.add(size);
			if (size > max) {
				max = size;
				index = i;
			}
		}
		System.out.println("Request with largest File is" + (requestUrl.get(index)) + "of size " + size);
	}

	public static double getETETime(String action) {
		ETETime = 0;
		if (renderedList.size() > 0) {
			HashMap<String, String> hm = renderedList.get(0);
			Double responseStart = Double.parseDouble(hm.get("startTime"));
			Double maxresponseEnd = 0.0;
			for (int i = 0; i < renderedList.size(); i++) {
				HashMap<String, String> hmm = renderedList.get(i);
				Double responseEnd1 = Double.parseDouble(hmm.get("responseEnd"));

				if (responseEnd1 > maxresponseEnd) {
					maxresponseEnd = responseEnd1;
				}
			}

			ETETime = maxresponseEnd - responseStart;
			BigDecimal bd = new BigDecimal(ETETime).setScale(2, RoundingMode.HALF_UP);
			ETETime = bd.doubleValue();
		}
		System.out.println("ETETime : " + ETETime);
		return ETETime;
	}

}
