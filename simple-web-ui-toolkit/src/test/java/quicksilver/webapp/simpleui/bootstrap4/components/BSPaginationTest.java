package quicksilver.webapp.simpleui.bootstrap4.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import quicksilver.webapp.simpleui.HtmlStreamStringBuffer;

public class BSPaginationTest {

	@Test
	public void testPagination() {
		String expected = "<nav>\n"+
			"<ul class=\"pagination\">\n"+
    			"<li class=\"page-item disabled\"><a class=\"page-link\" href=\"#\" tabindex=\"-1\" aria-disabled=\"true\">\nPrevious</a>\n</li>\n"+
    			"<li class=\"page-item\"><a class=\"page-link\" href=\"#\">\n1</a>\n</li>\n"+
    			"<li class=\"page-item active\" aria-current=\"page\"><a class=\"page-link\" href=\"#\">\n2</a>\n</li>\n"+
    			"<li class=\"page-item\"><a class=\"page-link\" href=\"#\">\n3</a>\n</li>\n"+
    			"<li class=\"page-item\"><a class=\"page-link\" href=\"#\">\nNext</a>\n</li>\n"+
  			"</ul>\n"+
			"</nav>\n";

		BSPagination.Link previous = new BSPagination.Link("Previous", "#", true);
		BSPagination.Link one = new BSPagination.Link("1", "#");
		BSPagination.Link two = new BSPagination.Link("2", "#");
		BSPagination.Link three = new BSPagination.Link("3", "#");
		BSPagination.Link next = new BSPagination.Link("Next", "#");

		BSPagination p = new BSPagination(previous, one, two, three, next);
		p.setActiveLink(two);

	        HtmlStreamStringBuffer sb = new HtmlStreamStringBuffer();
                p.render(sb);

                assertEquals(expected, sb.getText());
	}
}
