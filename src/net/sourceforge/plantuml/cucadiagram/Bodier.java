/* ========================================================================
 * PlantUML : a free UML diagram generator
 * ========================================================================
 *
 * (C) Copyright 2009-2023, Arnaud Roques
 *
 * Project Info:  http://plantuml.com
 * 
 * If you like this project or if you find it useful, you can support us at:
 * 
 * http://plantuml.com/patreon (only 1$ per month!)
 * http://plantuml.com/paypal
 * 
 * This file is part of PlantUML.
 *
 * PlantUML is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PlantUML distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public
 * License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 *
 * Original Author:  Arnaud Roques
 * 
 *
 */
package net.sourceforge.plantuml.cucadiagram;

import java.util.List;

import net.sourceforge.plantuml.ISkinParam;
import net.sourceforge.plantuml.baraye.Entity;
import net.sourceforge.plantuml.graphic.TextBlock;
import net.sourceforge.plantuml.klimt.color.NoSuchColorException;
import net.sourceforge.plantuml.klimt.font.FontConfiguration;
import net.sourceforge.plantuml.style.Style;

public interface Bodier {

	public void setLeaf(Entity leaf);

	public Display getFieldsToDisplay();

	public Display getMethodsToDisplay();

	public boolean addFieldOrMethod(String s) throws NoSuchColorException;

	public TextBlock getBody(ISkinParam skinParam, boolean showMethods, boolean showFields, Stereotype stereotype,
			Style style, FontConfiguration fontConfiguration);

	public List<CharSequence> getRawBody();

	public void muteClassToObject();

	public boolean hasUrl();
}
