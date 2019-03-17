package quicksilver.webapp.simpleui.bootstrap4.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import quicksilver.webapp.simpleui.HtmlStreamStringBuffer;
import quicksilver.webapp.simpleui.html.components.HTMLLabel;

public class BSFormTest {

	@Test
	public void testExample1() {
		String expected = "<form>\n" + 
				"<div class=\"form-group\">\n" + 
				"<label for=\"emailInput\">\nEmail address</label>\n" + 
				"<input class=\"form-control\" type=\"email\" placeholder=\"Enter email\" aria-describedby=\"emailHelp\" id=\"emailInput\">\n" + 
				"<small id=\"emailHelp\" class=\"form-text text-muted\">\n" +
				"We'll never share your email with anyone else.</small>\n" + 
				"</div>\n" + 
				"<div class=\"form-group\">\n" + 
				"<label for=\"passwordInput\">\nPassword</label>\n" + 
				"<input class=\"form-control\" type=\"password\" placeholder=\"Password\" id=\"passwordInput\">\n" + 
				"</div>\n" + 
				"<div class=\"form-group form-check\">\n" + 
				"<input class=\"form-check-input\" type=\"checkbox\" id=\"exampleCheck1\">\n" + 
				"<label class=\"form-check-label\" for=\"exampleCheck1\">\nCheck</label>\n" + 
				"</div>\n" + 
				"<button class=\"btn btn-primary\" type=\"submit\">\nSubmit\n</button>\n" + 
				"</form>\n";

		BSForm form = new BSForm(null, true);
		form.addAsGroup(new HTMLLabel("Email address", "emailInput"),
				new BSTextInput("email", "Enter email", null, "emailHelp", "emailInput"),
				new BSInputDescription("We'll never share your email with anyone else.", "emailHelp"));
		form.addAsGroup(new HTMLLabel("Password", "passwordInput"),
				new BSTextInput("password", "Password", null, null, "passwordInput")
				);
		form.addAsCheckGroup(new BSCheckInput("checkbox", null, null, null, "exampleCheck1"),
				new BSCheckLabel("Check", "exampleCheck1")
				);
		form.add(new BSFormButton("Submit"));

		HtmlStreamStringBuffer sb = new HtmlStreamStringBuffer();
		form.render(sb);

		assertEquals(expected, sb.getText());
	}

}
