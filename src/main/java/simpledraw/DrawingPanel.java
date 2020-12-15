package simpledraw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

/**
 * A Panel that displays a Drawing, and maintains a current DrawingTool<BR>
 * Uses the "State" design pattern
 * @author Rémi Bastide
 * @version 1.0
 * @see simpledraw.Drawing
 * @see simpledraw.DrawingTool
 */

public class DrawingPanel extends JPanel {
	static final long serialVersionUID = 1L;
	DrawingTool myCurrentTool;
	Drawing myDrawing = new Drawing();
	private final Set<Shape> mySelectedShapes = new HashSet<Shape>();

	public DrawingPanel() {
		super();
		setBackground(java.awt.Color.white);
		myCurrentTool = new SelectionTool(this);
		activate(myCurrentTool);
	}

	void activateSelectionTool() {
		terminate(myCurrentTool);
		myCurrentTool = new SelectionTool(this);
		activate(myCurrentTool);
	}

	void activateCircleTool() {
		terminate(myCurrentTool);
		myCurrentTool = new CircleTool(this);
		activate(myCurrentTool);
		myDrawing.clearSelection();
		repaint();
	}

	void activateLineTool() {
		terminate(myCurrentTool);
		myCurrentTool = new LineTool(this);
                activate(myCurrentTool);
		myDrawing.clearSelection();
		repaint();
	}
	
	public boolean isSelected(Shape s) {
	   return mySelectedShapes.contains(s);
	}
	
	 public Drawing getDrawing() {
	        return this.myDrawing;
	    }
	
	 public void clearSelection() {
	        mySelectedShapes.clear();
	        repaint();
	    }

	 public void selectShape(Shape s) {
	        mySelectedShapes.add(s);
	        repaint();
	    }
	
	 public void addShape(Shape s) {
	        clearSelection();
	        myDrawing.addShape(s);
	        selectShape(s);
	    }
	 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		RenderingHints qualityHints = new
			RenderingHints(RenderingHints.KEY_ANTIALIASING,
				       RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(qualityHints);
		myDrawing.accept(
	            new ShapeVisitor() {
	                @Override
	                public void visit(Line l) {
	                        l.draw(g2);
	                }
	                @Override
	                public void visit(Circle c) {
	                        c.draw(g2);
	                }
	                @Override
	                public void visit(PolyLine p) {
	                        p.draw(g2);
	                }
	            });
	        myCurrentTool.draw((Graphics2D) g);
	}
	
        private void terminate(DrawingTool t) {
            removeKeyListener(t);
            removeMouseListener(t);
            removeMouseMotionListener(t);
        }
        
       private void activate(DrawingTool t) {
            addKeyListener(t);
            addMouseListener(t);
            addMouseMotionListener(t);
        }
     
}
