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
package net.sourceforge.plantuml.graphic;

import net.atmp.SpecialText;
import net.sourceforge.plantuml.awt.geom.XDimension2D;
import net.sourceforge.plantuml.klimt.Shadowable;
import net.sourceforge.plantuml.klimt.UPath;
import net.sourceforge.plantuml.klimt.URectangle;
import net.sourceforge.plantuml.klimt.UTranslate;
import net.sourceforge.plantuml.klimt.color.HColors;
import net.sourceforge.plantuml.klimt.font.StringBounder;
import net.sourceforge.plantuml.ugraphic.UGraphic;

public class BigFrame extends AbstractTextBlock {
	private final TextBlock title;
	private final double width;
	private final double height;
	private final SymbolContext symbolContext;

	public BigFrame(final TextBlock title, final double width, final double height, final SymbolContext symbolContext) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.symbolContext = symbolContext;
	}

	private double getYpos(XDimension2D dimTitle) {
		if (dimTitle.getWidth() == 0)
			return 12;

		return dimTitle.getHeight() + 3;
	}

	public void drawU(UGraphic ug) {
		final StringBounder stringBounder = ug.getStringBounder();
		final XDimension2D dim = calculateDimension(stringBounder);
		ug = symbolContext.apply(ug);
		final XDimension2D dimTitle = title.calculateDimension(stringBounder);
		final double widthFull = dim.getWidth();
		final Shadowable rectangle = new URectangle(widthFull, dim.getHeight()).rounded(symbolContext.getRoundCorner())
				.ignoreForCompressionOnX().ignoreForCompressionOnY();
		rectangle.setDeltaShadow(symbolContext.getDeltaShadow());

		ug.draw(rectangle);

		final double textWidth;
		final int cornersize;
		if (dimTitle.getWidth() == 0) {
			textWidth = widthFull / 3;
			cornersize = 7;
		} else {
			textWidth = dimTitle.getWidth() + 10;
			cornersize = 10;
		}
		final double textHeight = getYpos(dimTitle);

		final UPath line = new UPath();
		line.setIgnoreForCompressionOnX();
		line.moveTo(textWidth, 0);

		line.lineTo(textWidth, textHeight - cornersize);
		line.lineTo(textWidth - cornersize, textHeight);

		line.lineTo(0, textHeight);
		ug.apply(HColors.none().bg()).draw(line);
		final double widthTitle = title.calculateDimension(stringBounder).getWidth();

		// Temporary hack...
		if (widthFull - widthTitle < 25)
			title.drawU(ug.apply(new UTranslate(3, 1)));
		else
			ug.apply(new UTranslate(3, 1)).draw(new SpecialText(title));

	}

	@Override
	public XDimension2D calculateDimension(StringBounder stringBounder) {
		return new XDimension2D(width, height);
	}

}