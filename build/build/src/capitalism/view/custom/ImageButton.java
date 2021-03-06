/*
 *  capitalism.view.editoreman 2017-2019
 *  
 *  This file is part of the Capitalism Simulation, abbreviated to CapSim
 *  in thEditorManagerf this project
 *
 *  Capsim is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either project 3 of the License, or
 *  (at your option) any later project.
*
*   Capsim is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*   along with Capsim.  If not, see <http://www.gnu.org/licenses/>.
*/
package capitalism.view.custom;

import capitalism.view.command.DisplayCommand;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Provides a clickable image that invokes a command.
 * Also provides a toggle facility, since many commands in this App are basically state switchers.
 * The image and its tooltip can have two variants (on and off) or can be the same.
 */

public class ImageButton extends Button {

	private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 5, 5, 5, 5;";
	private final String STYLE_PRESSED = "-fx-background-color: transparent; -fx-padding: 6 4 4 6;";
	private Tooltip onTip;
	private Tooltip offTip;
	private ImageView onView;
	private ImageView offView;
	private String onURL;
	private String offURL;
	private boolean state;

	/**
	 * Create an image that launches an action when clicked once.
	 * Normally, a toggle with two states.
	 * Styled so that images and toolTips are swapped when the state swaps
	 * 
	 * @param onImageURL
	 *            the image to show in the 'on' state
	 * @param offImageURL
	 *            the image to show in the 'off' state
	 * @param command
	 *            the {link capitalism.view.command} to execute
	 * @param offTipText
	 *            the tooltip to display in the off state
	 * @param onTipText
	 *            the tooltip to display in the on state
	 */
	public ImageButton(String onImageURL, String offImageURL, DisplayCommand command, String offTipText, String onTipText) {
		state = true;
		onURL = onImageURL;
		offURL = offImageURL;
		onView = new ImageView(onURL);
		onView.setFitHeight(20);
		onView.setFitWidth(20);
		if (offImageURL == null)
			offURL = onImageURL;// null URL quietly suppressed
		offView = new ImageView(offURL);
		offView.setFitHeight(20);
		offView.setFitWidth(20);
		setGraphic(onView);
		setPrefWidth(60);
		setMaxWidth(60);
		setMinWidth(60);
		onTip = new Tooltip(onTipText);
		offTip = new Tooltip(offTipText);
		ImageButton thisButton = this;
		setTooltip(onTip);

		setStyle(STYLE_NORMAL);

		setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				setStyle(STYLE_PRESSED);
				command.execute(thisButton);
			}
		});

		setOnMouseReleased(new EventHandler<MouseEvent>() {
			@Override public void handle(MouseEvent event) {
				setStyle(STYLE_NORMAL);
			}
		});

	}

	public void setImageWidth(int width) {
		onView.setFitWidth(width);
		offView.setFitWidth(width);
	}

	public void setImageHeight(int height) {
		onView.setFitHeight(height);
		offView.setFitHeight(height);
	}

	public void setOnState() {
		state = true;
		setTooltip(onTip);
		setGraphic(onView);
	}

	public void setOffState() {
		state = false;
		setTooltip(offTip);
		setGraphic(offView);
	}

	public void switchStates() {
		if (state) {
			setOffState();
		} else {
			setOnState();
		}
	}
	
	public boolean getState() {
		return state;
	}
}
