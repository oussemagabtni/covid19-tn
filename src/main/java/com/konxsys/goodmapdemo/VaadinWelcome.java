package com.konxsys.goodmapdemo;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;

public class VaadinWelcome extends Composite<Div> implements HasComponents {

	public VaadinWelcome() {
		Image myImage = new Image("hero-reindeer.svg", "alt text");
		add(myImage);
		add(new Label("Demo"));
	}

}
