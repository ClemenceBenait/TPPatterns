package simpledraw;

import java.util.EventObject;

public class DrawingEvent extends EventObject {
	private static final long serialVersionUID = 1L;

	public DrawingEvent(Object object) {
        super(object);
    }
}
