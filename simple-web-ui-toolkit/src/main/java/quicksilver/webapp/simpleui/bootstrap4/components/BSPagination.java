/*
 * Copyright 2018-2019 Niels Gron and Contributors All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package quicksilver.webapp.simpleui.bootstrap4.components;

import quicksilver.webapp.simpleui.HtmlStream;

/*
    Example :

    W3Schools :
    Bootstrap Docs :
 */

public class BSPagination extends BSComponent {

    private final Link[] links;
    private Link activeLink;

    public BSPagination(Link... links) {
	this.links = links;
    }

    protected void defineAttributes() {

    }

    @Override
    public void render(HtmlStream s) {
	s.writeln("<nav>");
	s.writeln("<ul class=\"pagination\">");
	for (Link link : links) {
		s.write("<li class=\"page-item");
		if(link.isDisabled()) {
			s.write(" disabled");
		} else if (activeLink == link) {
			s.write(" active\" aria-current=\"page");
		}
		s.write("\">");
		//XXX: When disabled, the link should become a <span>
		link.render(s);
		s.writeln("</li>");
	}
	s.writeln("</ul>");
	s.writeln("</nav>");
    }

	public void setActiveLink(Link l) {
		this.activeLink = l;
	}

	public static class Link extends BSHyperlink {

		private final boolean disabled;

		public Link(String text, String url, boolean disabled) {
			super(text, url);
			insertTagAttribute(0, "class", "page-link");
			this.disabled = disabled;
			if (disabled) {
				addTagAttribute("tabindex", "-1");
				addTagAttribute("aria-disabled", "true");
			}
		}

		public Link(String text, String url) {
			this(text, url, false);
		}

		public boolean isDisabled() {
			return disabled;
		}
	}
}
