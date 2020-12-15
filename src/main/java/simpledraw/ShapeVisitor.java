/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simpledraw;

/**
 *
 * @author cleme
 */
public interface ShapeVisitor {
    public void visit(Circle c);
	public void visit(Line l);
    public void visit(PolyLine pl);
}
