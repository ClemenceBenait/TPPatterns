/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simpledraw;

import javax.swing.JFileChooser;

/**
 *
 * @author cleme
 */
public class XMLPrinter implements ShapeVisitor {
	
	private final Drawing myDrawing;
  
    public XMLPrinter(Drawing d) {
    	myDrawing = d;
    }
    
    @Override
	public void visit(Circle c) {
		System.out.println(c);
	}

    @Override
	public void visit(Line l) {
		System.out.println(l);
	}
        
        @Override
	public void visit(PolyLine pl) {
		System.out.println(pl);
	}
}
