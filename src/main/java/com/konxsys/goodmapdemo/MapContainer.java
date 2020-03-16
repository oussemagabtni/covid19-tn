package com.konxsys.goodmapdemo;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class MapContainer extends HorizontalLayout implements HasComponents {

	private ExampleMap exampleMap;

	public MapContainer() {
		setWidth("100%");
		exampleMap = new ExampleMap();
		//exampleMap.setCenter(36.8065, 10.1815);
		exampleMap.setZoom(13);
		add(exampleMap);
	}

	public ExampleMap getMap() {
		return exampleMap;
	}
}
