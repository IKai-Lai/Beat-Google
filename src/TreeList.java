import java.util.ArrayList;

public class TreeList {
	public ArrayList<WebTree> lst;

	public TreeList() {
		this.lst = new ArrayList<WebTree>();
	}

	public void add(WebTree tree) {
		lst.add(tree);
	}

	// quick sort
	public void sort() {
		quickSort(0, lst.size() - 1);
	}

	private void quickSort(int leftbound, int rightbound) {
		// 1. implement quickSort algorithm
		if (leftbound < rightbound) {
			int i = leftbound - 1;
			for (int j = leftbound; j < rightbound; j++) {
				if (lst.get(j).root.nodeScore > lst.get(rightbound).root.nodeScore) {
					i++;
					swap(i, j);
				}
			}
			swap(i + 1, rightbound);
			int x = i + 1;
			quickSort(leftbound, x - 1);
			quickSort(x + 1, rightbound);
		}
	}

	private void swap(int aIndex, int bIndex) {
		WebTree temp = lst.get(aIndex);
		lst.set(aIndex, lst.get(bIndex));
		lst.set(bIndex, temp);
	}

	public void output() {
		// TODO: write output and remove all element logic here...
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < lst.size(); i++) {
			WebTree k = lst.get(i);
			if (i > 0)
				sb.append(" ");
			sb.append(k.toString());
		}

		System.out.println(sb.toString());
	}
}