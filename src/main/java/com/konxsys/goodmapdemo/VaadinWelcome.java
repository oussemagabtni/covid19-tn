package com.konxsys.goodmapdemo;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class VaadinWelcome extends HorizontalLayout implements HasComponents {

	public VaadinWelcome() {
		Image myImage = new Image("tunisie.jpg", "alt text");
		myImage.setHeight("50px");
		myImage.setWidth("50px");
		add(myImage);
		add(new Label("COVID Tunisia 2k20: Plateforme de  recensement des Lits disponibles dans  les Hopitaux cliniques sur tout le territoire Tunisien Accessible au service SAMU. Pour tout cas urgent appelez le 190"));
	}

}
