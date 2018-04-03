package com.konxsys.goodmapdemo;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;


@Tag("example-map")
//@JavaScript("good-map.js")
@HtmlImport("ExampleMap.html")
public class ExampleMap extends PolymerTemplate<ExampleMapModel> {

	public ExampleMap() {
	}
	
	@EventHandler
    private void handleReady() {
//        System.out.println("Received a handle ready event");
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
}