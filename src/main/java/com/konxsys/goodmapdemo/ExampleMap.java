package com.konxsys.goodmapdemo;

import java.util.Iterator;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;


@Tag("example-map")
//@JavaScript("good-map.js")
@HtmlImport("ExampleMap.html")
public class ExampleMap extends PolymerTemplate<ExampleMapModel> {

	public ExampleMap() {
		getElement().setAttribute("style", "width: 100%;");
	}
	
	@EventHandler
    private void handleReady() {
		Iterator<Location> it = MainView.locations.iterator();
		while (it.hasNext()) {
			Location location = (Location) it.next();
			showMarker(location.getLongitude(), location.getLatitude(), location.toString());
		}
	}
	
	public void setZoom(int zoom) {
		getElement().setProperty("zoom", zoom);
	}
	
	public int getZoom() {
		return getElement().getProperty("zoom",0);
		
	}
	
	public void setCenter(double latitude, double longitude) {
		getElement().setProperty("center",String.format("{\"lat\":%.6f, \"lng\":%.6f}",latitude, longitude));
		
	}
	
	public void showMarker(double longitude, double latitude,String texte) {
		getElement().callFunction("showMarker", longitude,latitude,texte);
	}
}