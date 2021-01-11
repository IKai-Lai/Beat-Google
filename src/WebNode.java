import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebNode {
	public WebNode parent;
	public ArrayList<WebNode> children;
	public WebPage webPage; // child element
	public double nodeScore;// main element This node's score += all its children¡¦s nodeScore

	public ArrayList<url> urlList;

	public String content;

	public WebNode(WebPage webPage) {
		this.webPage = webPage;
		this.children = new ArrayList<WebNode>();

		this.urlList = new ArrayList<url>();

	}

	public void setNodeScore(ArrayList<Keyword> keywords) throws IOException {
		// this method should be called in post-order mode
		query();
		if (getDepth() <= 2) {
			addChild();
		}
		// 1. compute webPage score
		webPage.setScore(keywords);
		// 2. set webPage score to nodeScore
		nodeScore = webPage.score;
		// 3. nodeScore += all children¡¦s nodeScore
		for (WebNode child : children) {
			try {
				child.setNodeScore(keywords);
				nodeScore = nodeScore + child.nodeScore;
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
	}

	public void addChild(WebNode child) {
		// add the WebNode to its children list
		this.children.add(child);
		child.parent = this;
	}

	public boolean isTheLastChild() {
		if (this.parent == null)
			return true;
		ArrayList<WebNode> siblings = this.parent.children;

		return this.equals(siblings.get(siblings.size() - 1));
	}

	public int getDepth() {
		int retVal = 1;
		WebNode currNode = this;
		while (currNode.parent != null) {
			retVal++;
			currNode = currNode.parent;
		}
		return retVal;
	}

	public String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(webPage.url);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in, "utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while ((line = bufReader.readLine()) != null) {
			retVal += line;

		}
		return retVal;
	}

	public HashMap<String, String> query() throws IOException

	{

		if (content == null)

		{

			content = fetchContent();

		}

		HashMap<String, String> retVal = new HashMap<String, String>();

		Document doc = Jsoup.parse(content);
		Elements lis = doc.select("a");

		for (Element li : lis) {
			try

			{
				String citeUrl = li.select("a").get(0).attr("href");
				String title = li.select("a").get(0).text();
				retVal.put(title, citeUrl);
				urlList.add(new url(citeUrl, title));

			} catch (IndexOutOfBoundsException e) {

//				e.printStackTrace();

			}

		}

		return retVal;

	}

	public void addChild() throws IOException {
		int i = 0;
		for (url u : urlList) {
			if (i > 5) {
				break;
			}
			i++;
			WebPage childPage = new WebPage(u.url, u.title);
			WebNode childNode = new WebNode(childPage);
			addChild(childNode);
		}
	}
}