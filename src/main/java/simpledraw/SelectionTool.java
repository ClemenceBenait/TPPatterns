package simpledraw;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * The tool to select, move and delete Shapes in the Drawing
 * @author Rémi Bastide
 * @version 1.0
 */

public class SelectionTool
	extends DrawingTool {
	private Point myLastPoint;

	public SelectionTool(DrawingPanel panel) {
		super(panel);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_DELETE) {
				myDrawing.clearSelection();
		}
	}

	public void mousePressed(MouseEvent e) {
		Shape selectedShape = myPanel.getDrawing().pickShapeAt(e.getPoint());
		myLastPoint = e.getPoint();
		if (selectedShape != null) {
			myPanel.selectShape(selectedShape);
            myPanel.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		} else {
			myPanel.clearSelection();
		}
	}

	public void mouseReleased(MouseEvent e) {
		mouseMoved(e);
	}

	public void mouseMoved(MouseEvent e) {
		Shape pickedShape = myPanel.myDrawing.pickShapeAt(e.getPoint());
		if (pickedShape != null) {
			myPanel.setCursor(Cursor.getPredefinedCursor(Cursor.
				HAND_CURSOR));
		} else {
			myPanel.setCursor(Cursor.getDefaultCursor());
		}
	}

	public void mouseDragged(MouseEvent e) {
		Shape selectedShape = myPanel.myDrawing.pickShapeAt(e.getPoint());
		if (selectedShape != null) {
			selectedShape.translateBy(
				e.getX() - myLastPoint.x,
				e.getY() - myLastPoint.y
				);
			myLastPoint = e.getPoint();
        	myPanel.repaint();
		}
	}

	void draw(Graphics2D g) {
	}

}
