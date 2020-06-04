package elements.creators;

import elements.Element;
import elements.StackElement;

public class StackElementCreator {
	public static Element createElement(String value) {
		return new StackElement(value);
	}
}
