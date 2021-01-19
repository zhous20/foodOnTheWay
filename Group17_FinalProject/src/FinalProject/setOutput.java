package FinalProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**A give output method
 * 
 * @author Group17
 * @see setOutput
 * @see FinalProject
 * @see FinalProject.setOutput
 * @see #gettOutput(String)
 * @see #getOutput()
 * @see #main
 */
public class setOutput {
	public static List<String> output = new ArrayList<String>();
	/**
     * This is a function to read input.
     * @param  food the name of the input that be entered by customer or tester.
     */
	public static void gettOutput(String food) throws NullPointerException {
		List<Data> datas = CSVSort.readCSV("dataset/sortedCSV.csv");
		BST<String, Integer> st = new BST<String, Integer>();
		String in = food;

		for (int p = 0; p < 29980; p++) {
			st.put(datas.get(p).COMMODITY.toLowerCase(), p);
		}
		int i = 0;
		int j1 = 0;
		if (st.get(in) != null) {
			i = st.get(in);
			j1 = st.get(in);
		} else {
			throw new NullPointerException("There is no this elements.Please enter another.");
		}
		output.add(datas.get(i).Ref_Date + "\t" + datas.get(i).COMMODITY + "\t" + datas.get(i).Value);
		while (i > 0 && datas.get(i).COMMODITY.equals(datas.get(i - 1).COMMODITY)) {
			output.add(datas.get(i - 1).Ref_Date + "\t" + datas.get(i - 1).COMMODITY + "\t" + datas.get(i - 1).Value);
			i--;
		}
		while (j1 < datas.size() && (datas.get(j1).COMMODITY).equals(datas.get(j1 + 1).COMMODITY)) {
			output.add(
					datas.get(j1 + 1).Ref_Date + "\t" + datas.get(j1 + 1).COMMODITY + "\t" + datas.get(j1 + 1).Value);
			j1++;
		}

	}
	/**
     * This is a function to return the arraylist.
     * @return the output arraylist which produced by gettOutput.
     */
	public static List<String> getOutput() {

		return output;
	}
	/**
     * This is a main function to generate output with function above.
     * @category main function
     */
	public static void main(String[] args) throws IOException {
		CSVSort.gen();
		List<String> a = setOutput.getOutput();
		for (int i = 0; i < a.size(); i++)
			System.out.println(a.get(i));
	}

}
