package FinalProject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
/**The back ground Data type
* 
* @author Croup17
* @see Data
* @see FinalProject
* @see Data
* @see FinalProject.Data
* @see #compareTo(Data)
* @see #toString()
*/
class Data implements Comparable<Data> {
	public String COMMODITY;
	public String Ref_Date;
	public String GEO;
	public String FOOD;
	public String COMsMODITY;
	public String Vector;
	public String Coordinate;
	public String Value;

	public Data(String Ref_Date, String GEO, String FOOD, String COMMODITY, String Vector, String Coordinate,
			String Value) {
		this.Ref_Date = Ref_Date;
		this.GEO = GEO;
		this.FOOD = FOOD;
		this.COMMODITY = COMMODITY;
		this.Vector = Vector;
		this.Coordinate = Coordinate;
		this.Value = Value;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (Ref_Date.contains(",")) {
			sb.append('\"');
			sb.append(Ref_Date);
			sb.append('\"');
			sb.append(",");
		} else {
			sb.append(Ref_Date);
			sb.append(",");
		}
		if (GEO.contains(",")) {
			sb.append('\"');
			sb.append(GEO);
			sb.append('\"');
			sb.append(",");
		} else {
			sb.append(GEO);
			sb.append(",");
		}
		if (FOOD.contains(",")) {
			sb.append('\"');
			sb.append(FOOD);
			sb.append('\"');
			sb.append(",");
		} else {
			sb.append(FOOD);
			sb.append(",");
		}
		if (COMMODITY.contains(",")) {
			sb.append('\"');
			sb.append(COMMODITY);
			sb.append('\"');
			sb.append(",");
		} else {
			sb.append(COMMODITY);
			sb.append(",");
		}
		if (Vector.contains(",")) {
			sb.append('\"');
			sb.append(Vector);
			sb.append('\"');
			sb.append(",");
		} else {
			sb.append(Vector);
			sb.append(",");
		}
		if (Coordinate.contains(",")) {
			sb.append('\"');
			sb.append(Coordinate);
			sb.append('\"');
			sb.append(",");
		} else {
			sb.append(Coordinate);
			sb.append(",");
		}
		if (Value.contains(",")) {
			sb.append('\"');
			sb.append(Value);
			sb.append('\"');
		} else {
			sb.append(Value);
		}
		return sb.toString();
	}

	public int compareTo(Data data) {
		// return COMMODITY.compareTo(data.COMMODITY);
		if (COMMODITY.compareTo(data.COMMODITY) == 0) {
			return Ref_Date.compareTo(data.Ref_Date);
		} else {
			return COMMODITY.compareTo(data.COMMODITY);
		}
	}
};
/**Input and Sort method to generate dataset
* 
* @author Croup17
* @see CSVSort
* @see FinalProject
* @see CSVSort
* @see FinalProject.CSVSort
* @see #CSVSort(String, String, String, String, String, String, String)
* @see #split(String)
* @see #readCSV(String)
* @see #sortCSV(List)
* @see #printCSV(String, List)
* @see #gen()
*/
public class CSVSort {
	public static String[] split(String line) {
		List<String> list = new ArrayList<String>();
		int begin = 0, end = -1;
		while (true) {
			begin = end + 1;
			if (line.charAt(begin) == '\"') {
				end = line.indexOf("\"", begin + 1);
			} else {
				end = line.indexOf(",", begin);
			}
			if (end < 0) {
				end = line.length();
			}
			if (line.charAt(begin) == '\"') {
				String p = line.substring(begin + 1, end);
				end = end + 1;
				list.add(p);
			} else {
				String p = line.substring(begin, end);
				list.add(p);
			}

			// String p = line.substring(begin,end);
			// list.add(p);
			if (end >= line.length() - 1) {
				break;
			}
		}
		return list.toArray(new String[0]);
	}

	public static List<Data> readCSV(String filename) {
		BufferedReader br = null;
		List<Data> datas = new ArrayList<Data>();
		try {
			br = new BufferedReader(new FileReader(filename));
			br.readLine();
			String s = null;
			while ((s = br.readLine()) != null) {
				String[] items = split(s);
				String Ref_Date = items[0];
				String GEO = items[1];
				String FOOD = items[2];
				String COMMODITY = items[3];
				String Vector = items[4];
				String Coordinate = items[5];
				// System.out.println(items[6]);
				String Value = items[6];
				Data data = new Data(Ref_Date, GEO, FOOD, COMMODITY, Vector, Coordinate, Value);
				datas.add(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return datas;
	}

	public static void sortCSV(List<Data> datas) {
		Collections.sort(datas);
	}

	public static void printCSV(String filename, List<Data> datas) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File(filename));
			pw.println("Ref_Date,GEO,FOOD,COMMODITY,Vector,Coordinate,Value");
			for (int i = 0; i < datas.size(); ++i) {
				pw.println(datas.get(i).toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}
	}

	public static void gen() throws FileNotFoundException{

		List<Data> datas = readCSV("dataset/00020011-eng.csv");
		sortCSV(datas);
		printCSV("dataset/sortedCSV.csv", datas);
		//System.out.println("generated");
		}
}