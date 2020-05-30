package elements.creators;

import elements.Element;
import elements.ListElement;

public class ListElementCreator {
	public static Element createElement(String value) {
		return new ListElement(value);
	}
}
