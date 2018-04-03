package com.konxsys.goodmapdemo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@Theme(Lumo.class)
public class MainView extends VerticalLayout {
	
	public MainView() {
		add(new VaadinWelcome()); 
		add(new Label("Using good-map component with Vaadin Flow"));
		
		ExampleMap exampleMap = new ExampleMap();
			
		ComboBox<String> comboBox = new ComboBox<>("Locations");
		comboBox.setItems("Brasil", "Croatia");

		comboBox.addValueChangeListener(event -> {
		    if (event.getSource().isEmpty()) {
		    	exampleMap.setCenter(30, -100);
		    	exampleMap.setZoom(1);
		    } else {
		    	if (event.getValue().equals("Croatia")) {
		    		exampleMap.setCenter(43.9786553, 15.3827974);
		    		exampleMap.setZoom(16);
		    	} else {
		    		exampleMap.setCenter(-21.805659,-49.088377);
		    		exampleMap.setZoom(17);
		    	}
		    }
		});
		
		add(comboBox);
		add(exampleMap);
		
		// no check on min/max zoom values
		// zoom value changes using +/- buttons are ignored
		Button zoomInButton = new Button("Zoom in");
		zoomInButton.addClickListener( e -> {
			exampleMap.setZoom(exampleMap.getZoom()+1);
		});
		Button zoomOutButton = new Button("Zoom out");
		zoomOutButton.addClickListener( e -> {
			exampleMap.setZoom(exampleMap.getZoom()-1);
		});
		add(new HorizontalLayout(zoomInButton, zoomOutButton));
	}

}
