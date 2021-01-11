
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestProject() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if (request.getParameter("keyword") == null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		GoogleQuery google = new GoogleQuery(request.getParameter("keyword"));
		HashMap<String, String> query = google.query();

// 新增關鍵字

		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		keywords.add(new Keyword("NBA", 20));
		keywords.add(new Keyword("籃球", 10));
		keywords.add(new Keyword("basket ball", 10));
		keywords.add(new Keyword("規則", 9));
		keywords.add(new Keyword("交易", 8));
		keywords.add(new Keyword("魔術", 2));
		keywords.add(new Keyword("老鷹", 2));
		keywords.add(new Keyword("溜馬", 2));
		keywords.add(new Keyword("76人", 2));
		keywords.add(new Keyword("騎士", 2));
		keywords.add(new Keyword("籃網", 2));
		keywords.add(new Keyword("尼克", 2));
		keywords.add(new Keyword("塞爾蒂克", 2));
		keywords.add(new Keyword("公鹿", 2));
		keywords.add(new Keyword("熱火", 2));
		keywords.add(new Keyword("黄蜂", 2));
		keywords.add(new Keyword("公牛", 2));
		keywords.add(new Keyword("暴龍", 2));
		keywords.add(new Keyword("活塞", 2));
		keywords.add(new Keyword("巫師", 2));
		keywords.add(new Keyword("太陽", 2));
		keywords.add(new Keyword("快艇", 2));
		keywords.add(new Keyword("國王", 2));
		keywords.add(new Keyword("爵士", 2));
		keywords.add(new Keyword("馬刺", 2));
		keywords.add(new Keyword("拓荒者", 2));
		keywords.add(new Keyword("湖人", 2));
		keywords.add(new Keyword("灰狼", 2));
		keywords.add(new Keyword("勇士", 2));
		keywords.add(new Keyword("鵜鶘", 2));
		keywords.add(new Keyword("獨行俠", 2));
		keywords.add(new Keyword("灰熊", 2));
		keywords.add(new Keyword("雷霆", 2));
		keywords.add(new Keyword("金塊", 2));
		keywords.add(new Keyword("火箭", 2));
		keywords.add(new Keyword("score", 3));
		keywords.add(new Keyword("point", 3));
		keywords.add(new Keyword("得分", 3));
		keywords.add(new Keyword("三分球", 3));
		keywords.add(new Keyword("罰分", 3));
		keywords.add(new Keyword("命中率", 3));
		keywords.add(new Keyword("籃板", 3));
		keywords.add(new Keyword("助攻", 3));
		keywords.add(new Keyword("阻攻", 3));
		keywords.add(new Keyword("上場時間", 3));
		keywords.add(new Keyword("抄截", 3));
		keywords.add(new Keyword("球鞋", 3));
		keywords.add(new Keyword("球衣", 3));
		keywords.add(new Keyword("Lebron", 4));
		keywords.add(new Keyword("Curry", 4));
		keywords.add(new Keyword("Harden", 4));
		keywords.add(new Keyword("Lin", 4));
		keywords.add(new Keyword("Giannis", 4));
		keywords.add(new Keyword("Westbrook", 4));
		keywords.add(new Keyword("Durant", 4));
		keywords.add(new Keyword("Harden", 4));
		keywords.add(new Keyword("Pual", 4));
		keywords.add(new Keyword("Leonard", 4));
		keywords.add(new Keyword("Kobe", 4));
		keywords.add(new Keyword("Jordan", 4));
		keywords.add(new Keyword("賽程", 9));
		keywords.add(new Keyword("生涯", 0.5));
		keywords.add(new Keyword("薪資", 8));
		keywords.add(new Keyword("MVP", 8));

		TreeList treeList = new TreeList();
		for (int i = 0; i < google.urlList.size(); i++) {
			if (google.urlList.get(i).title.equals("")) {
				google.urlList.remove(i);
				i--;
			}
		}
		int i = 0;
		for (url u : google.urlList) {
			i++;
			if (i > 10) {
				break;
			}
			try {
				WebPage rootPage = new WebPage(u.url, u.title);
				WebTree tree = new WebTree(rootPage);
				treeList.add(tree);
				tree.setPostOrderScore(keywords);
			} catch (IOException e) {
				System.out.print("");
			}
		}
		treeList.sort();
		for (WebTree tree : treeList.lst) {
			tree.eularPrintTree();
		}
		String[][] s = new String[google.urlList.size()][2];
		request.setAttribute("query", s);
		int num = 0;
		for (WebTree tree : treeList.lst) {
			String key = tree.root.webPage.name;
			String value = tree.root.webPage.url;
			s[num][0] = key;
			s[num][1] = value;
			num++;
		}
		request.getRequestDispatcher("googleitem.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}