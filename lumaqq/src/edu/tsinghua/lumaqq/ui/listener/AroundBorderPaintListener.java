/*
* LumaQQ - Java QQ Client
*
* Copyright (C) 2004 luma <stubma@163.com>
*
* This program is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
*/
package edu.tsinghua.lumaqq.ui.listener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;


/**
 * 用来在控件的周围画一圈边框，边框比控件只大一个象素
 * 
 * @author luma
 */
public class AroundBorderPaintListener implements PaintListener {
    private Class[] types;
    private Color color;
    
    public AroundBorderPaintListener(Class[] types) {
        this(types, Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
    }
    
    public AroundBorderPaintListener(Class[] types, Color color) {
        this.types = types;
        this.color = color;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.swt.events.PaintListener#paintControl(org.eclipse.swt.events.PaintEvent)
     */
    public void paintControl(PaintEvent e) {
        Composite parent = (Composite)e.getSource();
        Control[] children = parent.getChildren();
        for(int i = 0; i < children.length; i++) {
            if(children[i].isVisible() && accept(children[i].getClass()))
                BorderPainter.drawAroundBorder(children[i], e.gc, color);
        }
    }
    
    /**
     * 检查是否是需要画边框的控件类型
     * 
     * @param type
     * 		控件的Class
     * @return
     * 		true表示需要
     */
    private boolean accept(Class<? extends Control> type) {
        for(int i = 0; i < types.length; i++) {
            if(type == types[i])
                return true;            
        }
        return false;
    }
}
