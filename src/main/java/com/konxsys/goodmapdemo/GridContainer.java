package com.konxsys.goodmapdemo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.SelectionMode;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class GridContainer extends HorizontalLayout implements HasComponents {
	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";

	/**
	 * Global instance of the scopes required by this quickstart. If modifying these
	 * scopes, delete your previously saved tokens/ folder.
	 */
	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
	private static final String CREDENTIALS_FILE_PATH = "credentials.json";
	private MapContainer mapContainer;

	public GridContainer(MapContainer mapContainer) {
		this.mapContainer =mapContainer;
		getElement().setAttribute("style", "width: 100%;");
		Grid<Location> grid = new Grid<Location>();
		
		buildColumns(grid);
		grid.setSelectionMode(SelectionMode.NONE);
		grid.setItems(MainView.locations);
		buildFilters(grid);

//		try {
//			grid.setItems(getLocations());
//		} catch (IOException | GeneralSecurityException e) {
//			e.printStackTrace();
//		}
		add(grid);
	}

	private void buildFilters(Grid<Location> grid) {

		
		
		
	}

//	public static List<Location> getDummyLocations() {
//		List<Location> locations = new ArrayList<Location>();
//		Location location = new Location();
//		location.setNom("Polyclinique Taoufik");
//		location.setAdresse("El Manar 2");
//		location.setDelegation("Tunis");
//		location.setTotalLitReanimationAvecMachine(50);
//		location.setTotalLitReanimationAvecMachineDispo(25);
//
//		location.setTotalLitReanimationSansMachine(20);
////		location.setLongitude(36.849347);
////		location.setLatitude(10.199507);
//		locations.add(location);
//		return locations;
//	}

	Label getLabel(String text){
		Label label = new Label(text);
		label.getElement().getStyle().set("font-weight", "bold");
		return label;
	}
	private void buildColumns(Grid<Location> grid) {

		grid.addColumn(Location::getNom).setHeader(getLabel("Nom établissement")).setSortable(true).setWidth("350px");
	
		
		grid.addColumn(Location::getTotalLit).setHeader("Nombre de lits totals").setSortable(true).setWidth("250px");
		
		grid.addColumn(new ComponentRenderer<Span, Location>(location -> {
			  Span sp = new Span();
			  sp.setText(String.valueOf(location.getTotalLitDispo()));
			  if(location.getTotalLitDispo()>0) {
			  sp.getElement().getStyle().set("color", "green");
			  }else {
				  sp.getElement().getStyle().set("color", "red");

			  }
			  return sp;
			})).setHeader("Nombre de lits disponibles\"").setSortable(true).setWidth("250px");
		
		
		grid.addColumn(new ComponentRenderer<Span, Location>(location -> {
			  Span sp = new Span();
			  sp.setText(String.valueOf(location.getTotalLitReanimationAvecMachineDispo()));
			  if(location.getTotalLitReanimationAvecMachineDispo()>0) {
			  sp.getElement().getStyle().set("color", "green");
			  }else {
				  sp.getElement().getStyle().set("color", "red");

			  }
			  return sp;
			})).setHeader("Nombre de lits réanimation avec machine disponibles ").setSortable(true).setWidth("350px");
		
		
		grid.addColumn(new ComponentRenderer<Span, Location>(location -> {
			  Span sp = new Span();
			  sp.setText(String.valueOf(location.getTotalLitReanimationSansMachineDispo()));
			  if(location.getTotalLitReanimationSansMachineDispo()>0) {
			  sp.getElement().getStyle().set("color", "green");
			  }else {
				  sp.getElement().getStyle().set("color", "red");

			  }
			  return sp;
			})).setHeader("Nombre de lits réanimation sans machine disponibles").setSortable(true).setWidth("350px");
		
		
		grid.addColumn(new ComponentRenderer<Span, Location>(location -> {
			  Span sp = new Span();
			  sp.setText(String.valueOf(location.getTotalLitSimpleDispo()));
			  if(location.getTotalLitSimpleDispo()>0) {
			  sp.getElement().getStyle().set("color", "green");
			  }else {
				  sp.getElement().getStyle().set("color", "red");

			  }
			  return sp;
			})).setHeader("Nombre de lits simples disponibles").setSortable(true).setWidth("250px");

//		grid.addColumn(Location::getTotalLitReanimationAvecMachineDispo).setHeader("Nombre de lits réanimation avec machine disponibles ").setSortable(true);
//		grid.addColumn(Location::getTotalLitReanimationSansMachineDispo).setHeader("Nombre de lits réanimation sans machine disponibles").setSortable(true);
//		grid.addColumn(Location::getTotalLitSimpleDispo).setHeader("Nombre de lits simples disponibles").setSortable(true);
		grid.addColumn(Location::getAdresse).setHeader("Adresse").setSortable(true);
		grid.addColumn(Location::getDelegation).setHeader("Delegation").setSortable(true);


	}

	public  static List<Location> getLocations() throws IOException, GeneralSecurityException {
		List<Location> locations = new ArrayList<Location>();
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1VgI9ibxkpLyqaSrfGy7MYFswrlv6Vhtq53mJYYtukUo";
		final String range = "Etablissement";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
				.setApplicationName(APPLICATION_NAME).build();

		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {
			int i = 0;
			for (List row : values) {
				if (i > 0) {
					Location location = new Location();
					location.setNom((String) row.get(1));
					location.setAdresse((String) row.get(4));
					location.setTotalLitReanimationAvecMachine(Integer.parseInt((String) row.get(10)));
					location.setTotalLitReanimationSansMachine(Integer.parseInt((String) row.get(11)));
					location.setTotalLitSimple(Integer.parseInt((String) row.get(12)));
					
					location.setTotalLitReanimationAvecMachineDispo(Integer.parseInt((String) row.get(13)));
					location.setTotalLitReanimationSansMachineDispo(Integer.parseInt((String) row.get(14)));
					location.setTotalLitSimpleDispo(Integer.parseInt((String) row.get(15)));
					try {
					location.setLatitude(Double.parseDouble((String) row.get(16)));
					location.setLongitude(Double.parseDouble((String) row.get(17)));
					}catch (IndexOutOfBoundsException e) {
						// TODO: handle exception
					}
					locations.add(location);
					
				}
				i++;
			}
		}
		return locations;
	}

	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		// Load client secrets.
		InputStream in = GridContainer.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
						.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
						.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}
}
