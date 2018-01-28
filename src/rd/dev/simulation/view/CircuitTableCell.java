/*
 *  Copyright (C) Alan Freeman 2017-2019
 *  
 *  This file is part of the Capitalism Simulation, abbreviated to CapSim
 *  in the remainder of this project
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

package rd.dev.simulation.view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.scene.control.TableCell;
import javafx.scene.paint.Color;
import rd.dev.simulation.custom.TabbedTableViewer;
import rd.dev.simulation.model.Circuit;

public class CircuitTableCell extends TableCell<Circuit, String> {
	static final Logger logger = LogManager.getLogger("CircuitTableCell");

	Circuit.Selector selector;

	public CircuitTableCell(Circuit.Selector selector) {
		this.selector = selector;
	}

	@Override protected void updateItem(String item, boolean empty) {
		int i = getIndex();
		super.updateItem(item, empty);
		if (item == null) {// this happens,it seems, when the tableRow is used for the column header
			return;
		}
		Circuit circuit = getTableView().getItems().get(i);
		if (circuit == null) {
			logger.debug(" Null Circuit");
			return;
		}
		setText(item);
		setTextFill(circuit.changed(selector, TabbedTableViewer.displayAttribute) ? Color.RED : Color.BLACK);
		if (ViewManager.displayHints) {
			switch (selector) {
			case OUTPUT:
				setStyle("-fx-background-color: rgba(220,220,220,0.3)");
				break;
			case MAXIMUMOUTPUT:
				setStyle("-fx-background-color: rgba(220,220,220,0.3)");
				break;
			case PRODUCTIVESTOCKS:
			case SALESSTOCK:
			case MONEYSTOCK:
				switch (TabbedTableViewer.displayAttribute) {
				case PRICE:
					setStyle("-fx-background-color: rgba(255,240,204,0.3)");
					break;
				case VALUE:
					setStyle("-fx-background-color: rgb(255,225,225,0.3)");
					break;
				case QUANTITY:
					setStyle("-fx-background-color: rgba(220,220,220,0.3)");
					break;
				}
				break;
			case INITIALCAPITAL:
			case CURRENTCAPITAL:
			case PROFIT:
				setStyle("-fx-background-color: rgba(255,240,204,0.3)");
				break;
			default:
				break;
			}
		}
	}
}