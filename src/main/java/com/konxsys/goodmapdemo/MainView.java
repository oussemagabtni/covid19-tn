package com.konxsys.goodmapdemo;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

@Route("")
@Theme(Lumo.class)
public class MainView extends VerticalLayout {

	public static List<Location> locations = new ArrayList<Location>();

	public MainView() {
		initLocations();
		setWidth("100%");
		add(new VaadinWelcome());
		MapContainer mapContainer = new MapContainer();
		add(mapContainer);
		add(new GridContainer(mapContainer));
		
		
	}
	
	public void initLocations(){
		try {
			locations = GridContainer.getLocations();
		} catch (IOException | GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initDummyLocations() {
		//locations =GridContainer.getDummyLocations();
	}

}
